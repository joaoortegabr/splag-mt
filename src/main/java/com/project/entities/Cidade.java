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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "cidade")
public class Cidade implements Serializable {
	private static final long serialVersionUID = 4939571542886365015L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cidId;
	@Column(length = 200)
	private String cidNome;
	@Column(length = 2)
	private String cidUf;
	@OneToMany(mappedBy = "cidId", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Endereco> endereco;
	
	public Cidade() {
	}

	public Cidade(String cidNome, String cidUf) {
		this.cidNome = cidNome;
		this.cidUf = cidUf;
	}

	public Long getCidId() {
		return cidId;
	}

	public void setCidId(Long cidId) {
		this.cidId = cidId;
	}

	public String getCidNome() {
		return cidNome;
	}

	public void setCidNome(String cidNome) {
		this.cidNome = cidNome;
	}

	public String getCidUf() {
		return cidUf;
	}

	public void setCidUf(String cidUf) {
		this.cidUf = cidUf;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cidId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cidade other = (Cidade) obj;
		return Objects.equals(cidId, other.cidId);
	}

}