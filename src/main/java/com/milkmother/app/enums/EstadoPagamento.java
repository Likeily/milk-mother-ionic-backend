package com.milkmother.app.enums;

public enum EstadoPagamento {

	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
	private int cod;
	private String descricao;
	
	private EstadoPagamento(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String descricao() {
		return descricao;
	}
	
	public static EstadoPagamento toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		for(EstadoPagamento y : EstadoPagamento.values()) {
			if (cod.equals(y.getCod())) {
				return y;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
	
}
