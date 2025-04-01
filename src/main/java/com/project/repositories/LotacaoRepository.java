package com.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.entities.Lotacao;
import com.project.entities.ck.LotacaoCK;

@Repository
public interface LotacaoRepository extends JpaRepository<Lotacao, LotacaoCK>{

}
