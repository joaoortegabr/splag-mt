package com.project.models.dtos;

import java.io.Serializable;

public record ServidorEfetivoLotadoPorUnidadeResponse (
		String pesNome,
		int pesIdade,
	    String unidNome,
		String fpBucket
		) implements Serializable {

}

