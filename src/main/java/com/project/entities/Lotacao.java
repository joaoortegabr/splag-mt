package com.project.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import com.project.entities.ck.LotacaoCK;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "lotacao")
@IdClass(LotacaoCK.class) 
public class Lotacao implements Serializable {
	private static final long serialVersionUID = 2381376501900144323L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "lotId")
	private Long lotId;
	
	@Id
	@Column(name = "pesId")
	private Long pesId;
	
	@Id
	@Column(name = "unidId")
	private Long unidId;
	
	private LocalDate lotDataLotacao;
	private LocalDate lotDataRemocao;
	@Column(length = 100)
	private String lotPortaria;
	
	public Lotacao() {
	}

	public Lotacao(Long lotId, Long pesId, Long unidId, LocalDate lotDataLotacao, LocalDate lotDataRemocao,
			String lotPortaria) {
		this.lotId = lotId;
		this.pesId = pesId;
		this.unidId = unidId;
		this.lotDataLotacao = lotDataLotacao;
		this.lotDataRemocao = lotDataRemocao;
		this.lotPortaria = lotPortaria;
	}

	public Long getLotId() {
		return lotId;
	}

	public void setLotId(Long lotId) {
		this.lotId = lotId;
	}

	public Long getPesId() {
		return pesId;
	}

	public void setPesId(Long pesId) {
		this.pesId = pesId;
	}

	public Long getUnidId() {
		return unidId;
	}

	public void setUnidId(Long unidId) {
		this.unidId = unidId;
	}

	public LocalDate getLotDataLotacao() {
		return lotDataLotacao;
	}

	public void setLotDataLotacao(LocalDate lotDataLotacao) {
		this.lotDataLotacao = lotDataLotacao;
	}

	public LocalDate getLotDataRemocao() {
		return lotDataRemocao;
	}

	public void setLotDataRemocao(LocalDate lotDataRemocao) {
		this.lotDataRemocao = lotDataRemocao;
	}

	public String getLotPortaria() {
		return lotPortaria;
	}

	public void setLotPortaria(String lotPortaria) {
		this.lotPortaria = lotPortaria;
	}

	@Override
	public int hashCode() {
		return Objects.hash(lotId, pesId, unidId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lotacao other = (Lotacao) obj;
		return Objects.equals(lotId, other.lotId) && Objects.equals(pesId, other.pesId)
				&& Objects.equals(unidId, other.unidId);
	}

}