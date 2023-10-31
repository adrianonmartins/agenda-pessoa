package br.com.adrianonm.AppPessoasContatos.service.interfaces;

import java.util.List;
import java.util.Optional;

import br.com.adrianonm.AppPessoasContatos.model.Contato;


public interface ContatoServiceInterface {
	Contato save(Contato contato);
	Optional<Contato> getById(Long id);
	List<Contato> getAll();
	Contato update(Contato 	Contato);
	void delete(Long id);
	
}
