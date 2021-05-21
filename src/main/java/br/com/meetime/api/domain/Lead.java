package br.com.meetime.api.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import br.com.meetime.api.domain.enums.Status;

@Entity
public class Lead implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	
	@Column(unique=true)
	private String email;
	private String empresa; 
	private String site; 
	private String anotacoes; 
	private String externalLeadId; 

	@Enumerated(EnumType.STRING)
	private Status status; 
	
	@CollectionTable(name = "TELEFONE")
	private String telefone;

	@OneToOne(cascade=CascadeType.PERSIST) 
	@JoinColumn(name = "usuario_id")
	private Usuario usuario; 
	
	public Lead() {
		// TODO Auto-generated constructor stub
	}

	public Lead(Integer id, String nome, String email, String empresa, String site, String anotaçoes,
			Status status, String telefone, Usuario usuario) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.empresa = empresa;
		this.site = site;
		this.anotacoes = anotaçoes;
		this.status = status;
		this.telefone = telefone;
		this.usuario = usuario;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getAnotacoes() {
		return anotacoes;
	}

	public void setAnotacoes(String anotacoes) {
		this.anotacoes = anotacoes;
	}
	
	public String getExternalLeadId() {
		return externalLeadId;
	}

	public void setExternalLeadId(String externalLeadId) {
		this.externalLeadId = externalLeadId;
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

	public void setTelefone(String telefones) {
		this.telefone = telefones;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
		Lead other = (Lead) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}//class
