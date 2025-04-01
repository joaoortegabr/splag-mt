package com.project.models.dtos;

import java.io.Serializable;

public record ServidorEfetivoResponse (
		Long pesId,
		String seMatricula
		) implements Serializable {

}
