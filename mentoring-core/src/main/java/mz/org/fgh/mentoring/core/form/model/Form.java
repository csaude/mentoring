package mz.org.fgh.mentoring.core.form.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import mz.co.mozview.frameworks.core.model.GenericEntity;

/**
 * @author Eusebio Jose Maposse
 *
 */
@Entity
@Table(name = "FORMS", uniqueConstraints = @UniqueConstraint(columnNames = { "CODE" }))
public class Form extends GenericEntity{

	private static final long serialVersionUID = 1L;

}
