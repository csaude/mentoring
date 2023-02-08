/**
 *
 */
package mz.org.fgh.mentoring.integ.resources.partner;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

import com.sun.jersey.api.JResponse;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.org.fgh.mentoring.core.partner.service.PartnerService;
import mz.org.fgh.mentoring.integ.resources.AbstractResource;

/**
 * @author Stélio Moiane
 *
 */
@Service(PartnerResource.NAME)
@Path("v2/partners")
public class PartnerResource extends AbstractResource {

	public static final String NAME = "mz.org.fgh.mentoring.integ.resources.partner.PartnerResource";

	@Inject
	private PartnerService partnerService;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public JResponse<PartnerDTO> createPartner(final PartnerDTO partnerDTO) throws BusinessException {

		this.partnerService.createPartner(partnerDTO.getUserContext(), partnerDTO.getPartner());

		return JResponse.ok(partnerDTO).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public JResponse<PartnerDTO> updatePartner(final PartnerDTO partnerDTO) throws BusinessException {

		this.partnerService.updatePartner(partnerDTO.getUserContext(), partnerDTO.getPartner());

		return JResponse.ok(partnerDTO).build();
	}
}
