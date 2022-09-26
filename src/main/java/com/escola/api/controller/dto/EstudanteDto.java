package com.escola.api.controller.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.escola.api.model.Estudante;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstudanteDto {
	
	private Long id;
	private String nome;
	private int idade;
	private String serie;
	private int turma;
	

	public EstudanteDto(Estudante estudante) {
		this.id = estudante.getId();
		this.nome = estudante.getNome();
		this.idade = estudante.getIdade();
		this.serie = estudante.getSerie();
		this.turma = estudante.getTurma();		
	}
	
	public static List<EstudanteDto> converter(List<Estudante> estudantes){
		return estudantes.stream().map(EstudanteDto::new).collect(Collectors.toList());
	}
	

}
