package com.milkmother.app.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class DoacaoSelecionadaPK implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//Uma doação para 1 beneficiario
	@ManyToOne
	@JoinColumn(name="doacao_id")
	private Doacao doacao;
	
	@ManyToOne
	@JoinColumn(name="beneficiario_id")
	private Beneficiario beneficiario;
	
	public Doacao getDoacao() {
		return doacao;
	}
	public void setDoacao(Doacao doacao) {
		this.doacao = doacao;
	}
	public Beneficiario getBeneficiario() {
		return beneficiario;
	}
	public void setBeneficiario(Beneficiario beneficiario) {
		this.beneficiario = beneficiario;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((beneficiario == null) ? 0 : beneficiario.hashCode());
		result = prime * result + ((doacao == null) ? 0 : doacao.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DoacaoSelecionadaPK other = (DoacaoSelecionadaPK) obj;
		if (beneficiario == null) {
			if (other.beneficiario != null)
				return false;
		} else if (!beneficiario.equals(other.beneficiario))
			return false;
		if (doacao == null) {
			if (other.doacao != null)
				return false;
		} else if (!doacao.equals(other.doacao))
			return false;
		return true;
	}
	
}
