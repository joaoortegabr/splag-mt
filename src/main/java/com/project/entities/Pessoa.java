package com.project.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;

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
@Table(name = "pessoa", indexes = {
	    @Index(name = "idx_pes_id", columnList = "pes_id")
	})
public class Pessoa implements Serializable {
	private static final long serialVersionUID = -8155722864456324062L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pes_id")
	private Long pesId;
	@Column(name = "pes_nome", length = 200)
	private String pesNome;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@Column(name = "pes_data_nascimento")
	private LocalDate pesDataNascimento;
	@Column(name = "pes_sexo", length = 9)
	private String pesSexo;
	@Column(name = "pes_mae",length = 200)
	private String pesMae;
	@Column(name = "pes_pai", length = 200)
	private String pesPai;
	@OneToMany(mappedBy = "pesId", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<FotoPessoa> fotoPessoa;
	@OneToMany(mappedBy = "pesId", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PessoaEndereco> pessoaEndereco;
	@OneToMany(mappedBy = "pesId", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ServidorTemporario> servidorTemporario;
	@OneToMany(mappedBy = "pesId", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ServidorEfetivo> servidorEfetivo;
	@OneToMany(mappedBy = "pesId", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Lotacao> lotacao;
	
	
	public Pessoa() {
	}

	public Pessoa(String pesNome, LocalDate pesDataNascimento, String pesSexo, String pesMae, String pesPai) {
		this.pesNome = pesNome;
		this.pesDataNascimento = pesDataNascimento;
		this.pesSexo = pesSexo;
		this.pesMae = pesMae;
		this.pesPai = pesPai;
	}

	public Long getPesId() {
		return pesId;
	}

	public void setPesId(Long pesId) {
		this.pesId = pesId;
	}

	public String getPesNome() {
		return pesNome;
	}

	public void setPesNome(String pesNome) {
		this.pesNome = pesNome;
	}

	public LocalDate getPesDataNascimento() {
		return pesDataNascimento;
	}

	public void setPesDataNascimento(LocalDate pesDataNascimento) {
		this.pesDataNascimento = pesDataNascimento;
	}

	public String getPesSexo() {
		return pesSexo;
	}

	public void setPesSexo(String pesSexo) {
		this.pesSexo = pesSexo;
	}

	public String getPesMae() {
		return pesMae;
	}

	public void setPesMae(String pesMae) {
		this.pesMae = pesMae;
	}

	public String getPesPai() {
		return pesPai;
	}

	public void setPesPai(String pesPai) {
		this.pesPai = pesPai;
	}

	@Override
	public int hashCode() {
		return Objects.hash(pesId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(pesId, other.pesId);
	}

}