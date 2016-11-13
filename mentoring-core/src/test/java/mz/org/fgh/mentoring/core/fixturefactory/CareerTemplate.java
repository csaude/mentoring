/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.fixturefactory;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import mz.co.mozview.frameworks.core.fixtureFactory.TemplateLoader;
import mz.org.fgh.mentoring.core.career.model.Career;
import mz.org.fgh.mentoring.core.career.model.CareerType;

/**
 * @author Stélio Moiane
 *
 */
public class CareerTemplate implements TemplateLoader {

	public static final String VALID = "valid";

	@Override
	public void load() {

		Fixture.of(Career.class).addTemplate(VALID, new Rule() {
			{
				this.add("careerType",
						this.random(CareerType.ADMINISTRATIVE_ASSISTANT, CareerType.HEALTH_ASSOCIATE_DEGREE_N1,
								CareerType.HEALTH_ASSOCIATE_DEGREE_N2, CareerType.HEALTH_TECHNICIAL_SPECIALIST,
								CareerType.HEALTH_TECHNICIAN, CareerType.HEALTH_TECHNICIAN_AUXILIARY));
				this.add("position", this.random("Enfermeiro de saúde materno-infantil", "Agente de farmácia",
						"Parteira elementar"));
			}
		});

	}
}
