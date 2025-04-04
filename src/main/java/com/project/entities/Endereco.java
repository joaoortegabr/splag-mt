package com.project.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "endereco", indexes = {
	    @Index(name = "idx_end_id", columnList = "end_id")
	})
public class Endereco implements Serializable {
	private static final long serialVersionUID = -7223701444959301215L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "end_id")
	private Long endId;
	@Column(name = "end_tipo_logradouro", length = 50)
	private String endTipoLogradouro;
	@Column(name = "end_logradouro", length = 200)
	private String endLogradouro;
	@Column(name = "end_numero")
	private Integer endNumero;
	@Column(name = "end_bairro", length = 100)
	private String endBairro;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cid_id", nullable = false)
	private Cidade cidId;
	@OneToMany(mappedBy = "endId", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UnidadeEndereco> unidadeEndereco;
	@OneToMany(mappedBy = "endId", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PessoaEndereco> pessoaEndereco;
	
	public Endereco() {
	}

	public Endereco(String endTipoLogradouro, String endLogradouro, Integer endNumero, String endBairro,
			Cidade cidId) {
		this.endTipoLogradouro = endTipoLogradouro;
		this.endLogradouro = endLogradouro;
		this.endNumero = endNumero;
		this.endBairro = endBairro;
		this.cidId = cidId;
	}

	public Long getEndId() {
		return endId;
	}

	public void setEndId(Long endId) {
		this.endId = endId;
	}

	public String getEndTipoLogradouro() {
		return endTipoLogradouro;
	}

	public void setEndTipoLogradouro(String endTipoLogradouro) {
		this.endTipoLogradouro = endTipoLogradouro;
	}

	public String getEndLogradouro() {
		return endLogradouro;
	}

	public void setEndLogradouro(String endLogradouro) {
		this.endLogradouro = endLogradouro;
	}

	public Integer getEndNumero() {
		return endNumero;
	}

	public void setEndNumero(Integer endNumero) {
		this.endNumero = endNumero;
	}

	public String getEndBairro() {
		return endBairro;
	}

	public void setEndBairro(String endBairro) {
		this.endBairro = endBairro;
	}

	public Cidade getCidId() {
		return cidId;
	}

	public void setCidId(Cidade cidId) {
		this.cidId = cidId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(endId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Endereco other = (Endereco) obj;
		return Objects.equals(endId, other.endId);
	}

}