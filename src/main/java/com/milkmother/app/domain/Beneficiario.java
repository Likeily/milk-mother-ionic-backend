package com.milkmother.app.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.milkmother.app.enums.TipoBeneficiario;

@Entity
public class Beneficiario implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private Double valorParaDoacao;
	private String Cpf;
	private Integer tipo;
	
	@JsonBackReference
	@ManyToMany
	@JoinTable(name="BENEFICIARIO_CATEGORIA",
		joinColumns = @JoinColumn(name="BENEFICIARIO_ID"),
		inverseJoinColumns = @JoinColumn(name="CATEGORIA_ID")
	)
	private List<Categoria> categoria = new ArrayList<>();
	
	@JsonManagedReference
	@OneToMany(mappedBy="beneficiario")
	private List<Endereco> enderecos = new ArrayList<>();
	
	public Beneficiario() {
	}

	public Beneficiario(Integer id, String nome, Double valorParaDoacao, String cpf, TipoBeneficiario tipo) {
		super();
		this.id = id;
		this.nome = nome;
		this.valorParaDoacao = valorParaDoacao;
		Cpf = cpf;
		this.tipo = tipo.getCodi();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getValorParaDoacao() {
		return valorParaDoacao;
	}

	public void setValorParaDoacao(Double valorParaDoacao) {
		this.valorParaDoacao = valorParaDoacao;
	}

	public String getCpf() {
		return Cpf;
	}

	public void setCpf(String cpf) {
		Cpf = cpf;
	}

	public List<Categoria> getCategoria() {
		return categoria;
	}

	public void setCategoria(List<Categoria> categoria) {
		this.categoria = categoria;
	}
	public TipoBeneficiario getTipo() {
		return TipoBeneficiario.toEnum(tipo);
	}
	public void setTipo(TipoBeneficiario tipo) {
		this.tipo = tipo.getCodi();
	}
	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
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
		Beneficiario other = (Beneficiario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
