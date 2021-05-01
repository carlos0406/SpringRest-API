package br.com.carlos.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.carlos.Model.Tarefa;
import br.com.carlos.repository.TarefaRepository;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {
	@Autowired
	TarefaRepository tarefaRepository;
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Tarefa create(@RequestBody Tarefa tarefa) {
		return tarefaRepository.save(tarefa);
		
	}
	
	
	@GetMapping
	public List<Tarefa> listar
	(@RequestParam(value="titulo", defaultValue = "")String titulo,
	@RequestParam(value="situacao", defaultValue = "false")boolean situacao,
	@RequestParam(value="limite", defaultValue = "50")int limite,
	@RequestParam(value="nomeResponsavel",defaultValue = "") String nomeResponsavel
			) {
		Pageable primeiras =  PageRequest.of(0, limite);
		
		return tarefaRepository.findByTitulo(titulo,situacao,nomeResponsavel,primeiras);
		
	}
	
	@GetMapping("/{id}")
	public  Tarefa busca(@PathVariable("id") Long id) {
		return tarefaRepository.findById(id).get();
		
	}
	
	@PatchMapping("/{id}")
	public  Tarefa mudarEstado(@PathVariable("id") Long id,HttpServletResponse response) {
		Tarefa tarefa=tarefaRepository.findById(id).get();
		
		if(tarefa!=null) {
			tarefa.setConcluida(!tarefa.isConcluida());
			return tarefaRepository.save(tarefa);
		}
		response.setStatus(400);
		return null;
		
	}
	
	@PutMapping("/{id}")
	public  Tarefa edicao(@PathVariable("id") Long id,@RequestBody Tarefa tarefa,HttpServletResponse response) {
		
		if(tarefaRepository.existsById(id)) {
			tarefa.setId(id);
			response.setStatus(HttpServletResponse.SC_ACCEPTED);
			return tarefaRepository.save(tarefa);
			
		}
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		return null;
		
		
	}
	
	
}
