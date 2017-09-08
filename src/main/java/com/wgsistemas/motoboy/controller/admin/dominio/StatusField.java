package com.wgsistemas.motoboy.controller.admin.dominio;

public enum StatusField {
	TODOS(0), ACEITO(1), SOLICITADO(-1);

	private Integer value;

	private StatusField(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return this.value;
	}

	public static StatusField valueOF(Integer v) {
		for (StatusField status : values()) {
			if (status.getValue().equals(v)) {
				return status;
			}
		}
		return TODOS;
	}
}