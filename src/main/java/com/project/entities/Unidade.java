package com.project.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "unidade", indexes = {
	    @Index(name = "idx_unid_id", columnList = "unid_id")
	})
public class Unidade implements Serializable {
	private static final long serialVersionUID = 2802310619782857224L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "unid_id")
	private Long unidId;
	@Column(name = "unid_nome", length = 200)
	private String unidNome;
	@Column(name = "unid_sigla", length = 20, unique = true)
	private String unidSigla;
	@OneToMany(mappedBy = "unidId", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UnidadeEndereco> unidadeEnderecos;
	
	public Unidade() {
	}

	public Unidade(String unidNome, String unidSigla) {
		this.unidNome = unidNome;
		this.unidSigla = unidSigla;
	}

	public Long getUnidId() {
		return unidId;
	}

	public void setUnidId(Long unidId) {
		this.unidId = unidId;
	}

	public String getUnidNome() {
		return unidNome;
	}

	public void setUnidNome(String unidNome) {
		this.unidNome = unidNome;
	}

	public String getUnidSigla() {
		return unidSigla;
	}

	public void setUnidSigla(String unidSigla) {
		this.unidSigla = unidSigla;
	}

	@Override
	public int hashCode() {
		return Objects.hash(unidId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Unidade other = (Unidade) obj;
		return Objects.equals(unidId, other.unidId);
	}

}