/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.form.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import mz.co.mozview.frameworks.core.dao.GenericDAOImpl;
import mz.co.mozview.frameworks.core.dao.ParamBuilder;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.org.fgh.mentoring.core.form.model.Form;

/**
 * @author Eusebio Jose Maposse
 *
 */
@Repository(FormDAO.NAME)
public class FormDAOImpl extends GenericDAOImpl<Form, Long> implements FormDAO {

	@Override
	public Form fetchByFormId(final Long formId) {
		return this.findSingleByNamedQuery(FormDAO.QUERY_NAME.fetchByFormId,
		        new ParamBuilder().add("formId", formId).process());
	}

	@Override
	public List<Form> findBySelectedFilter(final String code, final String name, final String programmaticAreaCode, final LifeCycleStatus lifeCycleStatus, final String partnerUUID) {

		final CriteriaBuilder criteriaBuilder = this.getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<Form> createQuery = criteriaBuilder.createQuery(Form.class);
		final Root<Form> root = createQuery.from(Form.class);
		root.fetch("programmaticArea");

		createQuery.select(root);

		final List<Predicate> predicates = new ArrayList<>();

		if (code != null) {
			predicates.add(criteriaBuilder.like(root.get("code"), "%" + code + "%"));
		}

		if (name != null) {
			predicates.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
		}

		if (programmaticAreaCode != null) {
			predicates.add(criteriaBuilder.equal(root.get("programmaticArea").get("code"), programmaticAreaCode));
		}

		if (partnerUUID != null) {
			predicates.add(criteriaBuilder.equal(root.get("partner").get("uuid"), partnerUUID));
		}

		predicates.add(criteriaBuilder.equal(root.get("lifeCycleStatus"), lifeCycleStatus));

		createQuery.where(predicates.toArray(new Predicate[predicates.size()]));

		final TypedQuery<Form> query = this.getEntityManager().createQuery(createQuery);

		return query.getResultList();
	}

	@Override
	public List<Form> findSampleIndicators(final List<String> questionUuids, final LifeCycleStatus lifeCycleStatus) {

		final TypedQuery<Form> query = this.findByQuery(FormDAO.QUERY_NAME.findSampleIndicators,
		        new ParamBuilder().add("lifeCycleStatus", lifeCycleStatus).process());

		query.setParameter("questionUuids", questionUuids);

		return query.getResultList();
	}
}
