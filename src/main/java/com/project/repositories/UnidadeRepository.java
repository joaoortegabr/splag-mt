package com.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.entities.Unidade;

@Repository
public interface UnidadeRepository extends JpaRepository<Unidade, Long>{

}
