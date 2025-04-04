package com.project.entities;

import com.project.entities.ck.PessoaEnderecoCK;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pessoa_endereco")
@IdClass(PessoaEnderecoCK.class)
public class PessoaEndereco {

	@Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pes_id", referencedColumnName = "pes_id", nullable = false)
    private Pessoa pesId;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "end_id", referencedColumnName = "end_id", nullable = false)
    private Endereco endId;

	public Pessoa getPesId() {
		return pesId;
	}

	public void setPesId(Pessoa pesId) {
		this.pesId = pesId;
	}

	public Endereco getEndId() {
		return endId;
	}

	public void setEndId(Endereco endId) {
		this.endId = endId;
	}
	
}
