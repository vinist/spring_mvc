package com.algaworks.cobranca.enuns;

public enum StatusTituloEnum {
	PENDENTE(0, "Pendente"),
	RECEBIDO(1, "Recebido");
	
	private Integer value;
	private String label;
	
	private StatusTituloEnum(Integer value, String label) {
		this.value = value;
		this.label = label;
	}

	public Integer getValue() {
		return value;
	}

	public String getLabel() {
		return label;
	}
}
