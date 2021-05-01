package br.com.carlos.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.carlos.Model.Tarefa;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
	@Query("select t from Tarefa t where t.titulo like %:titulo% "
			+" AND t.concluida=:concluido"
			+" AND t.responsavel.nome like %:nomeResponsavel%")
	List <Tarefa> findByTitulo(
			@Param("titulo") String titulo,
		    @Param("concluido") boolean concluido,
		    @Param("nomeResponsavel")String nomeResponsavel,
		    Pageable primeiras);
}	
