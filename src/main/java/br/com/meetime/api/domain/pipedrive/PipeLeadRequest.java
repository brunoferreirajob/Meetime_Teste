package br.com.meetime.api.domain.pipedrive;

import java.io.Serializable;

public class PipeLeadRequest implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer personId;
	private Integer organizationId;
	private String title;
	private String note;
	
	public PipeLeadRequest() {
		// TODO Auto-generated constructor stub
	}

	public PipeLeadRequest(Integer personId, Integer organizationId, String title, String note) {
		this.personId = personId;
		this.organizationId = organizationId;
		this.title = title;
		this.note = note;
	}

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
