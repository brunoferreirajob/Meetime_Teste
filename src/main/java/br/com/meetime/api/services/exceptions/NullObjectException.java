package br.com.meetime.api.services.exceptions;

public class NullObjectException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NullObjectException(String message) {
		super(message);
	}

	public NullObjectException(String message, Throwable cause) {
		super(message, cause);
	}

}
