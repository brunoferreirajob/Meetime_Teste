package br.com.meetime.api.domain.pipedrive;

import java.io.Serializable;

public class PipePersonResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	private Data data;
	
	public PipePersonResponse() {
		// TODO Auto-generated constructor stub
	}

	public PipePersonResponse(Data data) {
		this.data = data;
	}

	public Data getData() {
		return data;
	}
}
