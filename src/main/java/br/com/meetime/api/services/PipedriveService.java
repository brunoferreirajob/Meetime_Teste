package br.com.meetime.api.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.meetime.api.client.PipedriveClient;
import br.com.meetime.api.domain.Lead;
import br.com.meetime.api.domain.pipedrive.PipeLeadRequest;
import br.com.meetime.api.domain.pipedrive.PipeLeadResponse;
import br.com.meetime.api.domain.pipedrive.PipeOrganizationRequest;
import br.com.meetime.api.domain.pipedrive.PipeOrganizationResponse;
import br.com.meetime.api.domain.pipedrive.PipePersonRequest;
import br.com.meetime.api.domain.pipedrive.PipePersonResponse;
import br.com.meetime.api.services.exceptions.IntegrationPipedriveException;
import feign.FeignException;

@Service
public class PipedriveService {

	@Autowired
	private PipedriveClient pipedriveClient;
	private static final String TOKEN = "ee718afcd781b9b4e5ada836a83a8433c4dd0557";

	public String sendLead(Lead lead) {

		PipeOrganizationRequest organization = new PipeOrganizationRequest(lead.getEmpresa());
		PipePersonRequest person = new PipePersonRequest(lead.getNome(), Arrays.asList(lead.getEmail()));
		PipeLeadResponse leadResponse = null;

		try {
			PipePersonResponse pipePersonResponse = pipedriveClient.savePerson(person, TOKEN);
			Integer personId = pipePersonResponse.getData().getId();

			PipeOrganizationResponse organizationResponse = pipedriveClient.saveOrganization(organization, TOKEN);
			Integer organizationId = organizationResponse.getData().getId();

			PipeLeadRequest pipeLeadRequest = new PipeLeadRequest(personId, organizationId, lead.getNome(), lead.getAnotacoes());
			leadResponse = pipedriveClient.saveLead(pipeLeadRequest, TOKEN);
		}catch (FeignException e) {
				throw new IntegrationPipedriveException(e.getMessage(), e.status());
		}
		
		return leadResponse.getData().getId();
	}
}
