package com.project.entities;

import java.io.Serializable;
import java.util.Objects;

import com.project.entities.ck.ServidorEfetivoCK;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "servidor_efetivo")
@IdClass(ServidorEfetivoCK.class)
public class ServidorEfetivo implements Serializable {
	private static final long serialVersionUID = -2993846180935236148L;
	
	@Id
	@Column(name = "pes_id")
	private Long pesId;
	
	@Column(name = "se_matricula", length = 20)
	private String seMatricula;

	public ServidorEfetivo() {
	}

	public ServidorEfetivo(Long pesId, String seMatricula) {
		this.pesId = pesId;
		this.seMatricula = seMatricula;
	}

	public Long getPesId() {
		return pesId;
	}

	public void setPesId(Long pesId) {
		this.pesId = pesId;
	}

	public String getSeMatricula() {
		return seMatricula;
	}

	public void setSeMatricula(String seMatricula) {
		this.seMatricula = seMatricula;
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
		ServidorEfetivo other = (ServidorEfetivo) obj;
		return Objects.equals(pesId, other.pesId);
	}

}