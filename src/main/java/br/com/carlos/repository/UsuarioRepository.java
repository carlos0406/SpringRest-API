package br.com.carlos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import br.com.carlos.Model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long > {
	
	@Query("select u from Usuario u where u.email like %:email% ")
	Optional<Usuario> findByUserEmail(@Param("email") String email);
	
	@Query("select u from Usuario u where u.email like %:email%"+" and u.senha like:senha")
	Optional<Usuario> findByUserEmailAndPassword(@Param("email") String email,@Param("senha")String senha);
}
