package com.project.entities.ck;

import java.io.Serializable;
import java.util.Objects;

import com.project.entities.Pessoa;

public class FotoPessoaCK implements Serializable {
	private static final long serialVersionUID = 2869085694579746884L;

	private Long fpId;
	private Pessoa pesId;
	
	public FotoPessoaCK() {
	}

	public FotoPessoaCK(Long fpId, Pessoa pesId) {
		this.fpId = fpId;
		this.pesId = pesId;
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

	@Override
	public int hashCode() {
		return Objects.hash(fpId, pesId.getPesId());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FotoPessoaCK other = (FotoPessoaCK) obj;
		return Objects.equals(fpId, other.fpId) && Objects.equals(pesId, other.pesId);
	}

}