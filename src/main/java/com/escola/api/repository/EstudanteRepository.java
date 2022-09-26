package com.escola.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.escola.api.model.Estudante;

public interface EstudanteRepository extends JpaRepository<Estudante, Long>{
	
	List<Estudante> findByNome(String nome);

}
