package br.com.carlos.controllers;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.carlos.modeljwt.JWTRequest;
import br.com.carlos.modeljwt.JWTResponse;
import br.com.carlos.repository.UsuarioRepository;
import br.com.carlos.service.UserService;
import br.com.carlos.utils.JWTUtils;

@RestController
public class HomeController {
	
	@Autowired
	private JWTUtils jwtUtil;
	
	@Autowired
	private AuthenticationManager autheticationManager;
	
	@Autowired
	private UserService service;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	
	@PostMapping("/autenticar")
	public JWTResponse autheticate(@RequestBody JWTRequest jwtRequest,HttpServletResponse response) throws Exception {
		try {
			autheticationManager.authenticate(
					
					new UsernamePasswordAuthenticationToken(jwtRequest.getUserName(),jwtRequest.getPassword())
					);
			
			final UserDetails userDetail= service.loadUserByUsername(jwtRequest.getUserName());
			
			final String token= jwtUtil.generateToken(userDetail);
			
			return new JWTResponse(token);	
		} catch (BadCredentialsException e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		
		
	}
	
}
