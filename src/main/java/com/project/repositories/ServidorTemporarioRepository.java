package com.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.entities.ServidorTemporario;
import com.project.entities.ck.ServidorTemporarioCK;

@Repository
public interface ServidorTemporarioRepository extends JpaRepository<ServidorTemporario, ServidorTemporarioCK>{

}
