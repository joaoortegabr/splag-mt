package com.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.entities.FotoPessoa;
import com.project.entities.ck.FotoPessoaCK;

@Repository
public interface FotoPessoaRepository extends JpaRepository<FotoPessoa, FotoPessoaCK>{

}
