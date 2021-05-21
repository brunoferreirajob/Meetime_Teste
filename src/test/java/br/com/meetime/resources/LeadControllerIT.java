package br.com.meetime.resources;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.meetime.api.Application;
import br.com.meetime.api.domain.Lead;
import br.com.meetime.api.domain.enums.Status;
import br.com.meetime.api.domain.to.LeadTO;
import br.com.meetime.api.domain.to.UsuarioTO;
import br.com.meetime.api.repositories.LeadRepository;

@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class)
@TestInstance(Lifecycle.PER_CLASS)
public class LeadControllerIT {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private LeadRepository leadRepository;
	
	@BeforeEach
	public void config() {
		leadRepository.deleteAll();
	}

	@Test
	public void postLeads() throws Exception {
		LeadTO leadTo = buildLeadTO();
		mockMvc.perform(
				post("/api/v1/leads").contentType("application/json").content(objectMapper.writeValueAsString(leadTo)))
				.andExpect(status().isCreated()).andDo(print())
				.andExpect(jsonPath("nome", is("Lead lord")))
				.andExpect(jsonPath("email", is("carlos@email.com")))
				.andExpect(jsonPath("empresa", is("Empresa lord")))
				.andExpect(jsonPath("site", is("Site lead")))
				.andExpect(jsonPath("anotacoes", is("Ola todos!")))
				.andExpect(jsonPath("telefone", is("+5521345567")))
				.andExpect(jsonPath("usuario.nome", is("Carlos")))
				.andExpect(jsonPath("usuario.email", is("carlos@email.com")));
	}
	
	@Test
	public void putLeads() throws Exception {
		LeadTO post = buildLeadTO();
		LeadTO put = builPutdLeadTO();
		
		mockMvc.perform(
				post("/api/v1/leads").contentType("application/json").content(objectMapper.writeValueAsString(post)))
				.andExpect(status().isCreated()).andDo(print());
		
		Lead lead = leadRepository.findAll().get(0);
		lead.setStatus(Status.LOST);
		leadRepository.save(lead);
		
		mockMvc.perform(
				put("/api/v1/leads/{id}", lead.getId()).contentType("application/json").content(objectMapper.writeValueAsString(put)))
				.andExpect(status().isOk()).andDo(print())
				.andExpect(jsonPath("nome", is("Lead lord")))
				.andExpect(jsonPath("email", is("carlos@emailnovo.com")))
				.andExpect(jsonPath("empresa", is("Empresa lord")))
				.andExpect(jsonPath("site", is("Site lead")))
				.andExpect(jsonPath("anotacoes", is("Ola todos!")))
				.andExpect(jsonPath("telefone", is("+5521345567")))
				.andExpect(jsonPath("usuario.nome", is("Carlos")))
				.andExpect(jsonPath("usuario.email", is("carlos@emailnovo.com")));
	}
	
	@Test
	public void completionsLost() throws JsonProcessingException, Exception {
		
		LeadTO leadTo = buildLeadTO();
		mockMvc.perform(
				post("/api/v1/leads").contentType("application/json").content(objectMapper.writeValueAsString(leadTo)))
				.andExpect(status().isCreated()).andDo(print())
				.andExpect(jsonPath("nome", is("Lead lord")))
				.andExpect(jsonPath("email", is("carlos@email.com")))
				.andExpect(jsonPath("empresa", is("Empresa lord")))
				.andExpect(jsonPath("site", is("Site lead")))
				.andExpect(jsonPath("anotacoes", is("Ola todos!")))
				.andExpect(jsonPath("telefone", is("+5521345567")))
				.andExpect(jsonPath("usuario.nome", is("Carlos")))
				.andExpect(jsonPath("usuario.email", is("carlos@email.com")));
		
		Lead lead = leadRepository.findAll().get(0);
		leadTo.setStatus(Status.LOST);
	
		mockMvc.perform(
				put("/api/v1/leads/{id}/completions", lead.getId()).contentType("application/json").content(objectMapper.writeValueAsString(leadTo)))
				.andExpect(status().isOk()).andDo(print())
				.andExpect(jsonPath("status", is("LOST")));
	}

	private LeadTO buildLeadTO() {
		UsuarioTO usuarioTO = new UsuarioTO();
		usuarioTO.setNome("Carlos");
		usuarioTO.setEmail("carlos@email.com");
		return new LeadTO(null, "Lead lord", "carlos@email.com", "Empresa lord", "Site lead", "Ola todos!",
				Status.OPEN, "+5521345567", usuarioTO);
	}
	
	private LeadTO builPutdLeadTO() {
		UsuarioTO usuarioTO = new UsuarioTO();
		usuarioTO.setNome("Carlos");
		usuarioTO.setEmail("carlos@emailnovo.com");
		return new LeadTO(null, "Lead lord", "carlos@emailnovo.com", "Empresa lord", "Site lead", "Ola todos!",
				Status.OPEN, "+5521345567", usuarioTO);
	}
}
