package br.com.carlos.controllers;

import java.util.List;

//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import br.com.carlos.Model.Usuario;
import br.com.carlos.repository.UsuarioRepository;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/usuarios")
@CrossOrigin
public class UsuariosController {
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value="Permite o cadastro de usuarios")
	public Usuario create(@RequestBody Usuario usuario) {
		String senhaCriptografada= 
				 new BCryptPasswordEncoder().encode(usuario.getSenha());
		usuario.setSenha(senhaCriptografada);
		
		return usuarioRepository.save(usuario);
		
	}
	
	@GetMapping
	@ApiOperation(value="Permite a listagem de todos os usuarios")
	public List<Usuario> listar() {
		return usuarioRepository.findAll();
		
	}
//	
//	@GetMapping("/{id}")
//	public  Usuario busca(@PathVariable("id") Long id) {
//		return usuarioRepository.findById(id).get();
//		
//	}
//	
//	@DeleteMapping("/{id}")
//	public  void deletar(@PathVariable("id") Long id) {
//		 usuarioRepository.deleteById(id);
//		
//	}
	
}
