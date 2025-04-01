package com.project.models.dtos;

import java.io.Serializable;

public record ServidorEfetivoRequest (
		Long pesId,
		String seMatricula
		) implements Serializable {

}
