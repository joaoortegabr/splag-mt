package com.project.entities.ck;

import java.io.Serializable;
import java.util.Objects;

public class PessoaEnderecoCK implements Serializable {
	private static final long serialVersionUID = 8657635756006153053L;
	
	private Long pesId;
	private Long endId;
	
	public PessoaEnderecoCK() {
	}

	public PessoaEnderecoCK(Long pesId, Long endId) {
		this.pesId = pesId;
		this.endId = endId;
	}

	public Long getPesId() {
		return pesId;
	}

	public void setPesId(Long pesId) {
		this.pesId = pesId;
	}

	public Long getEndId() {
		return endId;
	}

	public void setEndId(Long endId) {
		this.endId = endId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(endId, pesId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PessoaEnderecoCK other = (PessoaEnderecoCK) obj;
		return Objects.equals(endId, other.endId) && Objects.equals(pesId, other.pesId);
	}
	
}