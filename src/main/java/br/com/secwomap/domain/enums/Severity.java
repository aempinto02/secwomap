package br.com.secwomap.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Severity {

	LIGHT(1, "Leve"),
	MODERATE(2, "Moderada"),
	SERIOUS(3, "Grave");

	@Getter
	private Integer code;
	@Getter
	private String description;

	public static Severity toEnum(Integer code) {
		if (code == null) {
			return null;
		}

		for (Severity x : Severity.values()) {
			if (code.equals(x.getCode())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Id inválido: " + code);
	}
}