package com.project.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.entities.ServidorEfetivo;
import com.project.entities.ck.ServidorEfetivoCK;

@Repository
public interface ServidorEfetivoRepository extends JpaRepository<ServidorEfetivo, ServidorEfetivoCK>{

	@Query(value = "SELECT p.pes_nome, p.pes_data_nascimento, u.unid_nome, fp.fp_bucket " +
			"FROM servidor_efetivo se " +
			"JOIN pessoa p ON p.pes_id = se.pes_id " +
			"JOIN lotacao l ON l.pes_id = se.pes_id " + 
			"JOIN unidade u ON u.unid_id = l.unid_id " + 
			"LEFT JOIN foto_pessoa fp ON fp.pes_id = p.pes_id " + 
			"WHERE l.unid_id = :unidId", nativeQuery = true)
    Page<Object[]> findServidorEfetivoLotadoPorUnidade(@Param("unidId") Long unidId, Pageable pageable);
	
}
