package br.com.adrianonm.AppPessoasContatos.service.interfaces;

import java.util.List;
import java.util.Optional;

import br.com.adrianonm.AppPessoasContatos.model.Pessoa;

public interface PessoaServiceInterface {
	Pessoa save(Pessoa pessoa);
	Optional<Pessoa> getById(Long id);
	List<Pessoa> getAll();
	Pessoa update(Pessoa pessoa);
	void delete(Long id);
}
