package com.project.entities.ck;

import java.io.Serializable;
import java.util.Objects;

public class ServidorEfetivoCK implements Serializable {
	private static final long serialVersionUID = -838448416694757053L;
	
	private Long pesId;

    public ServidorEfetivoCK() {
	}

	public ServidorEfetivoCK(Long pesId) {
		this.pesId = pesId;
	}

	public Long getPesId() {
		return pesId;
	}

	public void setPesId(Long pesId) {
		this.pesId = pesId;
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
		ServidorEfetivoCK other = (ServidorEfetivoCK) obj;
		return Objects.equals(pesId, other.pesId);
	}

   
}
