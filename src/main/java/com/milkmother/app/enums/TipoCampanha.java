package com.milkmother.app.enums;

public enum TipoCampanha {
	
	PROJETOSOCIAL (1, "Projetos Sociais"),
	AJUDAURGENTES (2, "Apoio Humanitário");
	
	private int codi;
	private String descricao;
	
	private TipoCampanha(int codi, String descricao) {
		this.codi = codi;
		this.descricao = descricao;
	}
	
	public int getCodi() {
		return codi;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static TipoCampanha toEnum(Integer codi) {
		
		if(codi == null) {
			return null;
		}
		for (TipoCampanha x : TipoCampanha.values()) {
			if (codi.equals(x.getCodi())) {
				return x;
				
			}
		}
		
		throw new IllegalArgumentException("ID Inválido" + codi);
	}
}
