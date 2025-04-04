package com.project.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.entities.Unidade;

@Repository
public interface UnidadeRepository extends JpaRepository<Unidade, Long>{

	@Query(value = "SELECT e.end_tipo_logradouro, e.end_logradouro, e.end_numero, e.end_bairro, c.cid_nome, c.cid_uf " +
			"FROM pessoa p " +
			"JOIN lotacao l ON p.pes_id = l.pes_id " +
			"JOIN unidade u ON u.unid_id = l.unid_id " + 
			"JOIN unidade_endereco ue ON u.unid_id = ue.unid_id " +
			"JOIN endereco e ON ue.end_id = e.end_id " +
			"JOIN cidade c ON e.cid_id = c.cid_id " +
			"WHERE p.pes_nome LIKE %:pesNome%", nativeQuery = true)
	Page<Object[]> findnderecoPorNomeServidor(@Param("pesNome") String pesNome, Pageable pageable);
	
}
