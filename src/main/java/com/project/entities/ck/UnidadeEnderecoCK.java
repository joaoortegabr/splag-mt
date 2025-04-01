package com.project.entities.ck;

import java.io.Serializable;
import java.util.Objects;

public class UnidadeEnderecoCK implements Serializable {
	private static final long serialVersionUID = 7414054697980741127L;
	
	private Long unidId;
	private Long endId;
	
	public UnidadeEnderecoCK() {
	}
	
	public UnidadeEnderecoCK(Long unidId, Long endId) {
		this.unidId = unidId;
		this.endId = endId;
	}
	
	public Long getUnidId() {
		return unidId;
	}
	
	public void setUnidId(Long unidId) {
		this.unidId = unidId;
	}
	
	public Long getEndId() {
		return endId;
	}
	
	public void setEndId(Long endId) {
		this.endId = endId;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(endId, unidId);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UnidadeEnderecoCK other = (UnidadeEnderecoCK) obj;
		return Objects.equals(endId, other.endId) && Objects.equals(unidId, other.unidId);
	}
	
}