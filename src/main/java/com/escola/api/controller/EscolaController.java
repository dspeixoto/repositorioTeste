package com.escola.api.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.escola.api.controller.dto.EstudanteDto;
import com.escola.api.controller.form.EstudanteForm;
import com.escola.api.model.Estudante;
import com.escola.api.repository.EstudanteRepository;

@RestController
@RequestMapping("/estudantes")
public class EscolaController {
	
	@Autowired
	EstudanteRepository estudanteRepository;
	
	@GetMapping
	public List<EstudanteDto> lista(String nome) {
		List<Estudante> estudantes = estudanteRepository.findAll();
		return EstudanteDto.converter(estudantes);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<EstudanteDto> cadastrar(@RequestBody EstudanteForm form, UriComponentsBuilder uriBuilder) {
		Estudante estudante = form.converter();
		estudanteRepository.save(estudante);
		
		URI uri = uriBuilder.path("/estudantes/{id}").buildAndExpand(estudante.getId()).toUri();
		return ResponseEntity.created(uri).body(new EstudanteDto(estudante));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EstudanteDto> detalhado(@PathVariable Long id) {
		Optional<Estudante> estudante = estudanteRepository.findById(id);	
		if(estudante.isPresent()) {
			return ResponseEntity.ok(new EstudanteDto(estudante.get()));
		}		
		return ResponseEntity.notFound().build();		
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<EstudanteDto> atualizar(@PathVariable Long id, @RequestBody EstudanteForm form) {
		Optional<Estudante> optional = estudanteRepository.findById(id);
		if(optional.isPresent()) {
			Estudante estudante = form.atualizar(id, estudanteRepository);
			return ResponseEntity.ok(new EstudanteDto(estudante));
		}
		return ResponseEntity.notFound().build();	
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id){
		Optional<Estudante> optional = estudanteRepository.findById(id);
		if(optional.isPresent()) {
			estudanteRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();	
	}
}
