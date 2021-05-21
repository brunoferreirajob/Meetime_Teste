package br.com.meetime.api.domain.to;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import br.com.meetime.api.domain.Lead;
import br.com.meetime.api.domain.Usuario;
import br.com.meetime.api.domain.enums.Status;

public class LeadTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;

	@Email(message = "O email precisa ser válido")
	private String email;

	@NotNull(message = "A empresa precisa ser especificada")
	private String empresa;
	private String site;
	private String anotacoes;
	private Status status;
	private String telefone;
	private UsuarioTO usuario;

	public LeadTO() {
		// TODO Auto-generated constructor stub
	}

	public LeadTO(Lead lead) {
		this.id = lead.getId();
		this.nome = lead.getNome();
		this.email = lead.getEmail();
		this.empresa = lead.getEmpresa();
		this.site = lead.getSite();
		this.anotacoes = lead.getAnotacoes();
		this.status = lead.getStatus();
		this.telefone = lead.getTelefone();
		this.usuario = new UsuarioTO(lead.getUsuario());
	}

	public LeadTO(Integer id, String nome, String email, String empresa, String site, String anotacoes, Status status,
			String telefone, UsuarioTO usuario) {

		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.empresa = empresa;
		this.site = site;
		this.anotacoes = anotacoes;
		this.status = status;
		this.telefone = telefone;
		this.usuario = usuario;
	}

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getEmpresa() {
		return empresa;
	}

	public String getSite() {
		return site;
	}

	public String getAnotacoes() {
		return anotacoes;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getTelefone() {
		return telefone;
	}

	public UsuarioTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioTO usuario) {
		this.usuario = usuario;
	}

	public static LeadTO toTO(Lead lead) {
		return new LeadTO(lead);
	}

	public static Lead toModel(LeadTO leadDto) {
		return new Lead(leadDto.getId(), leadDto.getNome(), leadDto.getEmail(), leadDto.getEmpresa(), leadDto.getSite(),
				leadDto.getAnotacoes(), leadDto.getStatus(), leadDto.getTelefone(), new Usuario(
						leadDto.getUsuario().getId(), leadDto.getUsuario().getNome(), leadDto.getUsuario().getEmail()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LeadTO other = (LeadTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
