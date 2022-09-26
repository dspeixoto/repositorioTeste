package com.escola.api.controller.form;

import java.math.BigDecimal;

import com.escola.api.model.Estudante;
import com.escola.api.repository.EstudanteRepository;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstudanteForm {

	
	private String nome;
	private int idade;
	private String serie;
	private int turma;

	public Estudante converter() {
		return new Estudante(nome, idade, serie, turma);
	}
	
	public Estudante atualizar(Long id, EstudanteRepository estudanteRepository) {
		Estudante estudante = estudanteRepository.getOne(id);
		estudante.setNome(this.nome);
		estudante.setIdade(this.idade);
		estudante.setSerie(this.serie);
		estudante.setTurma(this.turma);
		return estudante;
	}	
}
