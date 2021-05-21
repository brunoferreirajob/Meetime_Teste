package br.com.meetime.api.domain.pipedrive;

import java.io.Serializable;
import java.util.List;

public class PipePersonRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;
	private List<String> email;
	
	public PipePersonRequest() {
		// TODO Auto-generated constructor stub
	}

	public PipePersonRequest(String name, List<String> email) {
		this.name = name;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getEmail() {
		return email;
	}

	public void setEmail(List<String> email) {
		this.email = email;
	}

}
