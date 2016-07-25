/*
 * MozView Technologies, Lda. 2010 - 2016
 */
package mz.org.fgh.mentoring.integ.resources;

import mz.co.mozview.frameworks.core.webservices.model.UserContext;

/**
 * @author Stélio Moiane
 *
 */
public abstract class AbstractResource {

	public UserContext getUserContetx() {
		return new UserContext();
	}
}
