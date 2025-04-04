package com.project.entities;

import com.project.entities.ck.UnidadeEnderecoCK;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "unidade_endereco")
@IdClass(UnidadeEnderecoCK.class)
public class UnidadeEndereco {

	@Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "end_id", referencedColumnName = "end_id", nullable = false)
    private Endereco endId;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unid_id", referencedColumnName = "unid_id", nullable = false)
    private Unidade unidId;

	public Endereco getEndId() {
		return endId;
	}

	public void setEndId(Endereco endId) {
		this.endId = endId;
	}

	public Unidade getUnidId() {
		return unidId;
	}

	public void setUnidId(Unidade unidId) {
		this.unidId = unidId;
	}  
	
}
