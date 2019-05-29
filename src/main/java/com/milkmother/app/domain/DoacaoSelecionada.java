package com.milkmother.app.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
//Classe "enxerga" a doação e o beneficiario

@Entity
public class DoacaoSelecionada implements Serializable {
	private static final long serialVersionUID = 1L;

	//Id embutido em tipo auxiliar
	//Não será serializado a partir da doação
	@JsonIgnore
	@EmbeddedId
	private DoacaoSelecionadaPK id = new DoacaoSelecionadaPK();
	
	private Double valor;
	
	public DoacaoSelecionada() {
	}

	public DoacaoSelecionada(Doacao doacao, Beneficiario beneficiario, Double valor) {
		super();
		id.setDoacao(doacao);
		id.setBeneficiario(beneficiario);
		this.valor = valor;
	}
	
	//Para acessar direto a Doacao e Beneficiario fora de suas respectivas classes.
	@JsonIgnore
	public Doacao getDoacao() {
		return id.getDoacao();
	}
	
	public Beneficiario getBeneficiario() {
		return id.getBeneficiario();
	}
	
	public DoacaoSelecionadaPK getId() {
		return id;
	}

	public void setId(DoacaoSelecionadaPK id) {
		this.id = id;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		DoacaoSelecionada other = (DoacaoSelecionada) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
