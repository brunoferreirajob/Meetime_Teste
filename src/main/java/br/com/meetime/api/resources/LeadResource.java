package br.com.meetime.api.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.meetime.api.domain.Lead;
import br.com.meetime.api.domain.to.LeadTO;
import br.com.meetime.api.services.LeadService;

@RestController
@RequestMapping(value = "/api/v1/leads")
public class LeadResource {

	@Autowired
	private LeadService leadService;	

	@RequestMapping()
	public ResponseEntity<List<LeadTO>> getAllLeads() {
		List<LeadTO> leadDto = leadService.getAll().stream().map(LeadTO::new).collect(Collectors.toList());
		return ResponseEntity.ok().body(leadDto);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<LeadTO> getleadById(@PathVariable Integer id) {
		return ResponseEntity.ok(LeadTO.toTO(leadService.findById(id)));
	}
	
	@PostMapping
	public ResponseEntity<LeadTO> insertLead(@Valid @RequestBody LeadTO leadTo) {
		LeadTO response = LeadTO.toTO(leadService.insert(LeadTO.toModel(leadTo)));
		URI location = getUri(response.getId());
		return ResponseEntity.created(location).body(response);
	}

	@PutMapping(path = "/{id}/completions")
	public ResponseEntity<LeadTO> completion(@PathVariable Integer id, @RequestBody LeadTO leadTO) {
		Lead lead = leadService.completion(id, leadTO.getStatus().name());
		return ResponseEntity.ok(LeadTO.toTO(lead));
	}

	@PutMapping("/{id}")
	public ResponseEntity<LeadTO> updateLead(@PathVariable Integer id, @RequestBody LeadTO leadTo) {
		LeadTO leadDto = LeadTO.toTO(leadService.update(LeadTO.toModel(leadTo), id));
		return ResponseEntity.ok(leadDto);
	}

	private URI getUri(Integer id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
	}
}
