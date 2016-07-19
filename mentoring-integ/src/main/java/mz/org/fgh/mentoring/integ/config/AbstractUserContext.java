package mz.org.fgh.mentoring.integ.config;

import mz.co.mozview.frameworks.core.webservices.model.UnitWS;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;

public abstract class AbstractUserContext {

	public UserContext getUserContext() {
		final UserContext context = new UserContext();
		context.setId(1L);

		context.setUnit(new UnitWS("NCU000001", "102124774", "Office Alima",
				"Bairro Djuba, Q-2, Casa nr. 375, Matola-Rio", "+(258) 82 2546100 ou +(258) 84 0546824",
				"steliomo@gmail.com"));

		return context;
	}
}
