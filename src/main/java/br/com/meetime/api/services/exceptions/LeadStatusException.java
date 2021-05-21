package br.com.meetime.api.services.exceptions;

public class LeadStatusException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public LeadStatusException(String message) {
		super(message);
	}

	public LeadStatusException(String message, Throwable cause) {
		super(message, cause);
	}

}
