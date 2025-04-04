package com.project.models.dtos;

import java.io.Serializable;

public record EnderecoUnidadePorNomeServidorResponse (
		String endTipoLogradouro,
		String endLogradouro,
	    Integer endNumero,
		String endBairro,
		String cidCidade,
		String cidUf
		) implements Serializable {

}
