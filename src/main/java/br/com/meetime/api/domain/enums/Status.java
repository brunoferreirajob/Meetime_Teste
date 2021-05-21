package br.com.meetime.api.domain.enums;

public enum Status {

	OPEN, WON, LOST;

	public static Status toEnum(String value) {
		if (value == null)
			return null;
		for (Status x : Status.values()) {
			if (value.equals(x.toString()))
				return x;
		}
		throw new IllegalArgumentException("Status do Lead inválido: " + value);
	}
}
