/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.fixturefactory;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import mz.co.mozview.frameworks.core.fixtureFactory.TemplateLoader;
import mz.org.fgh.mentoring.core.carrer.model.Carrer;
import mz.org.fgh.mentoring.core.carrer.model.CarrerType;

/**
 * @author Stélio Moiane
 *
 */
public class CarrerTemplate implements TemplateLoader {

	public static final String VALID = "valid";

	@Override
	public void load() {

		Fixture.of(Carrer.class).addTemplate(VALID, new Rule() {
			{
				this.add("carrerType",
						this.random(CarrerType.ADMINISTRATIVE_ASSISTANT, CarrerType.HEALTH_ASSOCIATE_DEGREE_N1,
								CarrerType.HEALTH_ASSOCIATE_DEGREE_N2, CarrerType.HEALTH_TECHNICIAL_SPECIALIST,
								CarrerType.HEALTH_TECHNICIAN, CarrerType.HEALTH_TECHNICIAN_AUXILIARY));
				this.add("position", this.random("Enfermeiro de saúde materno-infantil", "Agente de farmácia",
						"Parteira elementar"));
			}
		});

	}
}
