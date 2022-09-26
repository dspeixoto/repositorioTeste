package com.escola.api.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Entity
@Table(name="TB_ESTUDANTE")
public class Estudante implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String nome;
	private int idade;
	private String serie;
	private int turma;	
	
	public Estudante(String nome, int idade, String serie, int turma) {
		this.nome = nome;
		this.idade = idade;
		this.serie = serie;
		this.turma = turma;
	}
	
}
