package br.com.meetime.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.meetime.api.domain.pipedrive.PipeLeadRequest;
import br.com.meetime.api.domain.pipedrive.PipeLeadResponse;
import br.com.meetime.api.domain.pipedrive.PipeOrganizationRequest;
import br.com.meetime.api.domain.pipedrive.PipeOrganizationResponse;
import br.com.meetime.api.domain.pipedrive.PipePersonRequest;
import br.com.meetime.api.domain.pipedrive.PipePersonResponse;

@FeignClient(value = "pipedrive",  url = "https://api.pipedrive.com/v1")
public interface PipedriveClient {
	
	@PostMapping(value="/persons", produces = "application/json")
	PipePersonResponse savePerson(PipePersonRequest pipePersonRequest, @RequestParam(name = "api_token") String token);
	
	@PostMapping(value="/organizations", produces = "application/json")
	PipeOrganizationResponse saveOrganization(PipeOrganizationRequest pipeOrganizationRequest, @RequestParam(name = "api_token") String token);
	
	@PostMapping(value="/leads", produces = "application/json")
	PipeLeadResponse saveLead(PipeLeadRequest pipeLeadRequest, @RequestParam(name = "api_token") String token);
}