package br.com.meetime.api.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.meetime.api.domain.Lead;
import br.com.meetime.api.domain.enums.Status;
import br.com.meetime.api.repositories.LeadRepository;
import br.com.meetime.api.services.exceptions.LeadStatusException;
import br.com.meetime.api.services.exceptions.ObjectNotFoundException;

@Service
public class LeadService {

	@Autowired
	private LeadRepository leadRepository;
	@Autowired
	private PipedriveService pipedriveService;

	public List<Lead> getAll() {
		return leadRepository.findAll();
	}

	public Lead findById(Integer id) throws ObjectNotFoundException {
		Optional<Lead> lead = leadRepository.findById(id);
		return lead.orElseThrow(() -> new ObjectNotFoundException("Lead não encontrado! Id: " + id));
	}

	@Transactional
	public Lead insert(Lead lead) {
		if (lead == null) {
			throw new ObjectNotFoundException("Lead não encontrado!");
		} else {
			lead.setStatus(Status.OPEN);
			return leadRepository.save(lead);
		}
	}

	@Transactional
	public Lead update(Lead lead, Integer id) {
		if (lead == null || id == null) {
			throw new ObjectNotFoundException("Lead não encontrado! Id: " + id);
		}
		Optional<Lead> optionalLead = leadRepository.findById(id);
		if (optionalLead.isPresent()) {
			Lead leadDb = optionalLead.get();
			if (Status.OPEN == leadDb.getStatus())
				throw new LeadStatusException("Leads com status OPEN não deve ser modificado.");

			return leadRepository.save(update(lead, leadDb));

		} else {
			throw new ObjectNotFoundException("Lead não encontrado! Id: " + id);
		}
	}

	public Lead completion(Integer id, String status) {

		if (id == null)
			throw new ObjectNotFoundException("Lead não encontrado! Id: " + id);

		if (status == Status.OPEN.name())
			throw new LeadStatusException("Leads com status OPEN não deve ser enviado para o CRM.");

		Optional<Lead> optionalLead = leadRepository.findById(id);

		if (optionalLead.isPresent()) {
			Lead lead = optionalLead.get();
			if (status == Status.WON.name()) {
				String externalLeadId = pipedriveService.sendLead(lead);
				lead.setExternalLeadId(externalLeadId);
				return leadRepository.save(lead);
			} else {
				lead.setStatus(Status.LOST);
				return leadRepository.save(lead);
			}

		} else {
			throw new ObjectNotFoundException("Lead não encontrado! Id: " + id);
		}
	}

	private static Lead update(Lead newLead, Lead oldLead) {
		oldLead.setNome(newLead.getNome());
		oldLead.setAnotacoes(newLead.getAnotacoes());
		oldLead.setEmail(newLead.getEmail());
		oldLead.setEmpresa(newLead.getEmpresa());
		oldLead.setSite(newLead.getSite());
		oldLead.setStatus(newLead.getStatus());
		oldLead.setTelefone(newLead.getTelefone());
		oldLead.setUsuario(newLead.getUsuario());

		return oldLead;
	}
}
