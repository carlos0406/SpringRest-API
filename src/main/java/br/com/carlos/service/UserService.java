package br.com.carlos.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.carlos.Model.Usuario;
import br.com.carlos.repository.UsuarioRepository;

@Service	
public class UserService implements UserDetailsService {
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
		Usuario u=usuarioRepository.findByUserEmail(userEmail).get();
		return new User(u.getEmail(),u.getSenha(),new ArrayList<>());
	}

	
	
}
