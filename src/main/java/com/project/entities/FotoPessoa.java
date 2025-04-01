package com.project.entities;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.entities.ck.FotoPessoaCK;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "foto_pessoa")
@IdClass(FotoPessoaCK.class) 
public class FotoPessoa implements Serializable {
	private static final long serialVersionUID = 9162970082841105618L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "fpId")
	private Long fpId;
	
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pesId", referencedColumnName = "pesId", nullable = false)
	private Pessoa pesId;
    
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate fpData;
	@Column(length = 50)
	private String fpBucket;
	@Column(length = 50)
	private String fpHash;

	public FotoPessoa() {
	}

	public FotoPessoa(Pessoa pesId, LocalDate fpData, String fpBucket, String fpHash) {
		this.pesId = pesId;
		this.fpData = fpData;
		this.fpBucket = fpBucket;
		this.fpHash = fpHash;
	}

	public Long getFpId() {
		return fpId;
	}

	public void setFpId(Long fpId) {
		this.fpId = fpId;
	}

	public Pessoa getPesId() {
		return pesId;
	}

	public void setPesId(Pessoa pesId) {
		this.pesId = pesId;
	}

	public LocalDate getFpData() {
		return fpData;
	}

	public void setFpData(LocalDate fpData) {
		this.fpData = fpData;
	}

	public String getFpBucket() {
		return fpBucket;
	}

	public void setFpBucket(String fpBucket) {
		this.fpBucket = fpBucket;
	}

	public String getFpHash() {
		return fpHash;
	}

	public void setFpHash(String fpHash) {
		this.fpHash = fpHash;
	}

}