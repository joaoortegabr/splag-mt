package com.project.entities.ck;

import java.io.Serializable;
import java.util.Objects;

public class LotacaoCK implements Serializable {
	private static final long serialVersionUID = -3456187517434316210L;

	private Long lotId;
	private Long pesId;
	private Long unidId;
	
	public LotacaoCK() {
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