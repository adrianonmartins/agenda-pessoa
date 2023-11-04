package br.com.adrianonm.AppPessoasContatos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.adrianonm.AppPessoasContatos.model.Contato;
import br.com.adrianonm.AppPessoasContatos.repository.ContatoRepository;
import br.com.adrianonm.AppPessoasContatos.service.interfaces.ContatoServiceInterface;

@Service
public class ContatoService implements ContatoServiceInterface{
	
	private ContatoRepository contatoRepository;
	
	@Autowired
	public ContatoService(ContatoRepository contatoRepository) {
		this.contatoRepository = contatoRepository;
		
	}
	
	@Override
	public Contato save(Contato contato) {
		return contatoRepository.save(contato);
	}

	@Override
	public Optional<Contato> getById(Long id) {

		return contatoRepository.findById(id);
	}

	@Override
	public List<Contato> getAll() {
		return contatoRepository.findAll();
	}
	
	@Override
	public Contato update(Contato contato) {
		Optional<Contato> upContato = contatoRepository.findById(contato.getId());
		if(upContato.isPresent()) {
			Contato newContato = upContato.get();
			newContato.setTipo(contato.getTipo());
			newContato.setContato(contato.getContato());
			return contatoRepository.save(newContato);
		}
		return contato;

	}
	@Override
	public List<Contato> findByPessoaId(long pessoaId){
        return contatoRepository.findByPessoaId(pessoaId);
    }

	@Override
	public Contato delete(Long id) {
		Contato contato = this.getById(id).get();
		contatoRepository.deleteById(id);
		return contato;
	}
	
}
