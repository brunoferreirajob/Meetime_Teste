package br.com.meetime.api.services.exceptions;

public class IntegrationPipedriveException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private int status;

	public IntegrationPipedriveException(String message, int status) {
		super(message);
		this.status = status;
	}

	public IntegrationPipedriveException(String message, Throwable cause) {
		super(message, cause);
	}

	public int getStatus() {
		return status;
	}

}
