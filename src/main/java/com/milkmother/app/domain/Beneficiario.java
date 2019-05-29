package com.milkmother.app.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.milkmother.app.enums.TipoBeneficiario;

@Entity
public class Beneficiario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private Double valorParaDoacao;
	private String Cpf;
	private Integer tipo;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "BENEFICIARIO_CATEGORIA", 
			   joinColumns = @JoinColumn(name = "BENEFICIARIO_ID"), 
			   inverseJoinColumns = @JoinColumn(name = "CATEGORIA_ID"))
	private List<Categoria> categorias = new ArrayList<>();

	@OneToMany(mappedBy="id.beneficiario")
	private Set<DoacaoSelecionada> itens = new HashSet<>();
	
	@OneToMany(mappedBy = "beneficiario")
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
	
	//O beneficiario conhece suas doações então.
	//o get varre as doações identificando as doações do usuario.
	//Ignorar essa função para as doações não serem serializadas.
	@JsonIgnore
	public List<Doacao> getDoacaos(){
		List<Doacao> listar = new ArrayList<>();
		for (DoacaoSelecionada x : itens) {
			listar.add(x.getDoacao());
		}
		return listar;
	
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

	public String getCpf() {
		return Cpf;
	}

	public void setCpf(String cpf) {
		Cpf = cpf;
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

	public Double getValorParaDoacao() {
		return valorParaDoacao;
	}

	public void setValorParaDoacao(Double valorParaDoacao) {
		this.valorParaDoacao = valorParaDoacao;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public Set<DoacaoSelecionada> getItens() {
		return itens;
	}

	public void setItens(Set<DoacaoSelecionada> itens) {
		this.itens = itens;
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
