package br.com.meetime.api.domain.pipedrive;

import java.io.Serializable;

public class PipeOrganizationResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	private Data data;
	
	public PipeOrganizationResponse() {
		// TODO Auto-generated constructor stub
	}

	public PipeOrganizationResponse(Data data) {
		this.data = data;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

}
