package com.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.entities.ServidorEfetivo;
import com.project.entities.ck.ServidorEfetivoCK;

@Repository
public interface ServidorEfetivoRepository extends JpaRepository<ServidorEfetivo, ServidorEfetivoCK>{

}
