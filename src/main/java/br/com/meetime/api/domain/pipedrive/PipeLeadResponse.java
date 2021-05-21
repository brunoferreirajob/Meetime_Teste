package br.com.meetime.api.domain.pipedrive;

import java.io.Serializable;

public class PipeLeadResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	private Data data;
	
	public PipeLeadResponse() {
		// TODO Auto-generated constructor stub
	}

	public PipeLeadResponse(Data data) {
		this.data = data;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}
	
	public class Data {
		
		private String id;
		
		public Data() {
			// TODO Auto-generated constructor stub
		}

		public Data(String id) {
			this.id = id;
		}

		public String getId() {
			return id;
		}
	}
}
