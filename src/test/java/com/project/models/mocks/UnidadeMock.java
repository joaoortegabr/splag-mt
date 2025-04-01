package com.project.models.mocks;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.project.entities.Unidade;

@Component
public class UnidadeMock {

	public Unidade single() {
		
		Unidade unidade = new Unidade(
            "Cuiabá",
            "CB_MT-012"
            );
        unidade.setUnidId(1L);
		
		return unidade;
	}
	
	public List<Unidade> list() {
		
		Unidade unidade1 = new Unidade(
            "Cuiabá",
            "CB_MT-012"
            );
		
		Unidade unidade2 = new Unidade(
            "Rondonópolis",
            "RD_MT-003"
            );

		unidade1.setUnidId(1L);
		unidade2.setUnidId(2L);
		
		List<Unidade> list = new ArrayList<>();
		list.add(unidade1);
		list.add(unidade1);
		
		return list;
	}
	
}
