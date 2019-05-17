package com.milkmother.app.enums;

public enum TipoBeneficiario {
	
	ORFAO (1, "Orfão"),
	ESTUDANTE (2, "Estudante"),
	DEFICIENTE(3, "Pessoa com Deficiência"),
	SAUDE(4, "Cuidados Especiais com Saúde");
	
	private int codi;
	private String descricao;
	
	private TipoBeneficiario(int codi, String descricao) {
		this.codi = codi;
		this.descricao = descricao;
	}
	
	public int getCodi() {
		return codi;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static TipoBeneficiario toEnum(Integer codi) {
		
		if(codi == null) {
			return null;
		}
		for (TipoBeneficiario x : TipoBeneficiario.values()) {
			if (codi.equals(x.getCodi())) {
				return x;
				
			}
		}
		
		throw new IllegalArgumentException("ID Inválido" + codi);
	}
}
