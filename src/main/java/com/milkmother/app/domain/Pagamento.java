package com.milkmother.app.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.milkmother.app.enums.EstadoPagamento;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Pagamento implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;
	private Integer estado;
	
	//Garante que o Id é o mesmo que será gerado na Doação
	@OneToOne
	@JoinColumn(name="doacao_id")
	@MapsId
	private Doacao doacao;
	
	
	public Pagamento() {
	}

	public Pagamento(Integer id, EstadoPagamento estado, Doacao doacao) {
		super();
		this.id = id;
		this.estado = estado.getCod();
		this.doacao = doacao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EstadoPagamento getEstado() {
		return EstadoPagamento.toEnum(estado);
	}

	public void setEstado(EstadoPagamento estado) {
		this.estado = estado.getCod();
	}

	public Doacao getDoacao() {
		return doacao;
	}

	public void setDoacao(Doacao doacao) {
		this.doacao = doacao;
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
		Pagamento other = (Pagamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
