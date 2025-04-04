package com.project.utils;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

import org.springframework.stereotype.Service;

@Service
public class CalculadoraIdade {

    public int calcular(Date dataNascimento) {
        if (dataNascimento == null) {
            throw new IllegalArgumentException("Data de nascimento não pode ser nula.");
        }
        LocalDate dataAtual = LocalDate.now();
        Period periodo = Period.between(convertSqlDateToLocalDate(dataNascimento), dataAtual);

        return periodo.getYears();
    }
    
    private static LocalDate convertSqlDateToLocalDate(Date sqlDate) {
        if (sqlDate == null) {
            return null;
        }
        return sqlDate.toLocalDate();
    }
    
}
