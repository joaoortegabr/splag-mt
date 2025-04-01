package com.project.models.dtos;

import java.io.Serializable;

public record UnidadeRequest (
		Long unidId,
		String unidNome,
		String unidSigla
		) implements Serializable {

}
