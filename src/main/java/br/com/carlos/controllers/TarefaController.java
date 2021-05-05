package br.com.carlos.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import br.com.carlos.repository.UsuarioRepository;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/tarefas")
@CrossOrigin
public class TarefaController {
	@Autowired
	TarefaRepository tarefaRepository;

	@Autowired
	UsuarioRepository usuarioRepository;

	@PostMapping
	@ApiOperation(value = "Permite a criação de uma tarefa")
	public Tarefa create(@RequestBody Tarefa tarefa, HttpServletResponse response) {
		boolean erro = false;
		if (tarefa.getTitulo() == null || tarefa.getTitulo().isEmpty()) {
			erro = true;
		} else if (tarefa.getDescricao() == null || tarefa.getDescricao().isEmpty()) {
			erro = true;
		} else if (tarefa.getDeadline() == null) {
			erro = true;
		} else if (tarefa.getPrioridade() == null || tarefa.getPrioridade().isEmpty()) {
			erro = true;
		} else if (tarefa.getResponsavel().getId() == 0
				|| usuarioRepository.findById(tarefa.getResponsavel().getId()) == null) {
			erro = true;
		}

		if (erro) {

			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;

		}
		return tarefaRepository.save(tarefa);

	}

	@GetMapping
	@ApiOperation(value = "Permite a listagem de tarefas com os filtros de titulo,"
			+ " stuação , nome do responsável e pode limitar a quantidade de resultados")
	public List<Tarefa> listar(@RequestParam(value = "titulo", defaultValue = "") String titulo,
			@RequestParam(value = "situacao", defaultValue = "false") boolean situacao,
			@RequestParam(value = "limite", defaultValue = "50") int limite,
			@RequestParam(value = "nomeResponsavel", defaultValue = "") String nomeResponsavel) {
		Pageable primeiras = PageRequest.of(0, limite);

		return tarefaRepository.findByTitulo(titulo, situacao, nomeResponsavel, primeiras);

	}

	@ApiOperation(value = "Permite o retorno de uma tarefa unica baseada no seu ID")
	@GetMapping("/{id}")
	public Tarefa busca(@PathVariable("id") Long id) {
		return tarefaRepository.findById(id).get();

	}

	@PatchMapping("/{id}")
	@ApiOperation(value = "Permite a mudança da situacao de uma tarefa unica baseada no seu ID")
	public Tarefa mudarEstado(@PathVariable("id") Long id, HttpServletResponse response) {
		Tarefa tarefa = tarefaRepository.findById(id).get();

		if (tarefa != null) {
			tarefa.setConcluida(!tarefa.isConcluida());
			return tarefaRepository.save(tarefa);
		}
		response.setStatus(400);
		return null;

	}

	@ApiOperation(value = "Permite a edicao de uma tarefa unica baseada no seu ID")
	@PutMapping("/{id}")
	public Tarefa edicao(@PathVariable("id") Long id, @RequestBody Tarefa tarefa, HttpServletResponse response) {
		boolean erro = false;
		if (tarefa.getTitulo() == null || tarefa.getTitulo().isEmpty()) {
			erro = true;
		} else if (tarefa.getDescricao() == null || tarefa.getDescricao().isEmpty()) {
			erro = true;
		} else if (tarefa.getDeadline() == null) {
			erro = true;
		} else if (tarefa.getPrioridade() == null || tarefa.getPrioridade().isEmpty()) {
			erro = true;
		} else if (tarefa.getResponsavel().getId() == 0
				|| usuarioRepository.findById(tarefa.getResponsavel().getId()) == null) {
			erro = true;
		}else if (!tarefaRepository.existsById(id)) {
			erro=true;
		}
		
		if(erro) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		tarefa.setId(id);
		return tarefaRepository.save(tarefa);
		

	}

	@ApiOperation(value = "Permite excluir uma tarefa unica baseada no seu ID")
	@DeleteMapping("/{id}")
	public String deletar(@PathVariable("id") Long id, HttpServletResponse response) {
		boolean erro = false;
		if (!tarefaRepository.existsById(id)) {
			erro=true;
		}
		
		if(erro) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return "Não existe tarefa com esse valor de ID";
		}
		
		tarefaRepository.deleteById(id);
		return "Tarefa deletada com exito";

	}

}
