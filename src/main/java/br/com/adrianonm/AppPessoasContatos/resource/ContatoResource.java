package br.com.adrianonm.AppPessoasContatos.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.adrianonm.AppPessoasContatos.model.Contato;
import br.com.adrianonm.AppPessoasContatos.model.Pessoa;
import br.com.adrianonm.AppPessoasContatos.service.ContatoService;
import br.com.adrianonm.AppPessoasContatos.service.PessoaService;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api")
public class ContatoResource {
	
	private ContatoService contatoService;
 	private PessoaService pessoaService;
	
	@Autowired
	public ContatoResource(ContatoService contatoService, PessoaService pessoaService) {
		this.contatoService = contatoService;
		this.pessoaService = pessoaService;
	}
	
	@PostMapping("/pessoas/{id}/contatos")
	public ResponseEntity<Contato> save(@RequestBody Contato contato, @PathVariable Long id){
		Pessoa pessoaContato = pessoaService.getById(id).get();
		
		Contato newContato = new Contato();
		newContato.setContato(contato.getContato());
		newContato.setTipo(contato.getTipo());
		newContato.setPessoa(pessoaContato);
		
		Contato newContatoSave = contatoService.save(newContato);
		
		if(newContatoSave == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(newContatoSave);
	}
	

	@GetMapping("/contatos/{id}")
	public ResponseEntity<Optional<Contato>> getById(@PathVariable Long id){
		Optional<Contato> contato = contatoService.getById(id);
		if(contato == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(contato);
	}
	
//	@GetMapping("/pessoas/{id}/contatos")
//	public ResponseEntity<List<Contato>> getContatosByPessoaId(@PathVariable Long id) {
//	    List<Contato> contatos = contatoService.getContatosByPessoaId(id);
//	    if (contatos.isEmpty()) {
//	        return ResponseEntity.noContent().build();
//	    } else {
//	        return ResponseEntity.ok(contatos);
//	    }
//	}
//	public List<Contato> listContatosPessoa(@PathVariable Long idPessoa){
//		Pessoa pessoa = pessoaService.getById(idPessoa).get();
//		
//		List<Contato> contato = contatoService.getAll();
//		
//		return contato;
		
		
//		if(contato == null)
//			return ResponseEntity.notFound().build();
//		return ResponseEntity.ok(contato);
//	}
	
//	@PutMapping
//	public ResponseEntity<Contato> update(@RequestBody Contato contato){
//		Contato newContato = contatoService.update(contato);
//		if(newContato == null)
//			return ResponseEntity.notFound().build();
//		return ResponseEntity.ok(newContato);
//	}
//	
//	@DeleteMapping("/{id}")
//	public ResponseEntity<?> delete(@PathVariable Long id){
//		contatoService.delete(id);
//		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//	}



}
 