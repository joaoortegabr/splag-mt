package com.project.models.dtos;

import java.io.Serializable;
import java.time.LocalDate;

public record LotacaoRequest (
		Long lotId,
		Long pesId,
		Long unidId,
		LocalDate lotDataLotacao,
		LocalDate lotDataRemocao,
		String lotPortaria
		) implements Serializable {

}
