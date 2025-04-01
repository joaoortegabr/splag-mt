package com.project.models.dtos;

import java.io.Serializable;

public record UnidadeResponse (
		Long unidId,
		String unidNome,
		String unidSigla
		) implements Serializable {

}
