/**
 *
 */
package mz.org.fgh.mentoring.integ.resources.partner;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.partner.model.Partner;

/**
 * @author Stélio Moiane
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PartnerDTO {

	private UserContext userContext;

	private Partner partner;

	public PartnerDTO() {
	}

	public UserContext getUserContext() {
		return this.userContext;
	}

	public Partner getPartner() {
		return this.partner;
	}
}
