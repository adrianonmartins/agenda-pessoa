package br.com.adrianonm.AppPessoasContatos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.adrianonm.AppPessoasContatos.model.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {

	List<Contato> findByPessoaId(Long Id);


}