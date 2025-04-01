package com.project.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.entities.ck.ServidorTemporarioCK;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "servidor_temporario")
@IdClass(ServidorTemporarioCK.class)
public class ServidorTemporario implements Serializable {
	private static final long serialVersionUID = -5274859839328934179L;
	
	@Id
	@Column(name = "pesId")
	private Long pesId;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate stDataAdmissao;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate stDataDemissao;

	
	public ServidorTemporario() {
	}

	public ServidorTemporario(Long pesId, LocalDate stDataAdmissao, LocalDate stDataDemissao) {
		this.pesId = pesId;
		this.stDataAdmissao = stDataAdmissao;
		this.stDataDemissao = stDataDemissao;
	}

	public Long getPesId() {
		return pesId;
	}

	public void setPesId(Long pesId) {
		this.pesId = pesId;
	}

	public LocalDate getStDataAdmissao() {
		return stDataAdmissao;
	}

	public void setStDataAdmissao(LocalDate stDataAdmissao) {
		this.stDataAdmissao = stDataAdmissao;
	}

	public LocalDate getStDataDemissao() {
		return stDataDemissao;
	}

	public void setStDataDemissao(LocalDate stDataDemissao) {
		this.stDataDemissao = stDataDemissao;
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
		ServidorTemporario other = (ServidorTemporario) obj;
		return Objects.equals(pesId, other.pesId);
	}

}