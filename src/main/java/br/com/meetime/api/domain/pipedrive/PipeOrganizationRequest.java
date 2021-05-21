package br.com.meetime.api.domain.pipedrive;

import java.io.Serializable;

public class PipeOrganizationRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;
	
	public PipeOrganizationRequest() {
		// TODO Auto-generated constructor stub
	}

	public PipeOrganizationRequest(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
