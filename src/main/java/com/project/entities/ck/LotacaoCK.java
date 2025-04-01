package com.project.entities.ck;

import java.io.Serializable;
import java.util.Objects;

import com.project.entities.Pessoa;
import com.project.entities.Unidade;

public class LotacaoCK implements Serializable {
	private static final long serialVersionUID = -3456187517434316210L;

	private Long lotId;
	private Pessoa pesId;
	private Unidade unidId;
	
	public LotacaoCK() {
	}

	public LotacaoCK(Long lotId, Pessoa pesId, Unidade unidId) {
		this.lotId = lotId;
		this.pesId = pesId;
		this.unidId = unidId;
	}

	public Long getLotId() {
		return lotId;
	}

	public void setLotId(Long lotId) {
		this.lotId = lotId;
	}

	public Pessoa getPesId() {
		return pesId;
	}

	public void setPesId(Pessoa pesId) {
		this.pesId = pesId;
	}

	public Unidade getUnidId() {
		return unidId;
	}

	public void setUnidId(Unidade unidId) {
		this.unidId = unidId;
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
		LotacaoCK other = (LotacaoCK) obj;
		return Objects.equals(lotId, other.lotId) && Objects.equals(pesId, other.pesId)
				&& Objects.equals(unidId, other.unidId);
	}

}