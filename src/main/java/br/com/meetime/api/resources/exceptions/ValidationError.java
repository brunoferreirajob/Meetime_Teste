package br.com.meetime.api.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;

	private List<FieldMessage> Errors = new ArrayList<FieldMessage>();

	public ValidationError(Integer status, String message, Long timeStamp) {
		super(status, message, timeStamp);
	}

	public List<FieldMessage> getErrors() {
		return Errors;
	}

	public void addError(String fieldName, String message) {
		Errors.add(new FieldMessage(fieldName, message));
	}

}
