package com.project.models.dtos;

import java.io.Serializable;
import java.time.LocalDate;

public record ServidorTemporarioRequest (
		Long pesId,
		LocalDate stDataAdmissao,
		LocalDate stDataDemissao
		) implements Serializable {

}
