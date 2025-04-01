package com.project.models.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public record LotacaoResponse (
		Long lotId,
		Long pesId,
		Long unidId,
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
		LocalDate lotDataLotacao,
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
		LocalDate lotDataRemocao,
		String lotPortaria
		) implements Serializable {

}
