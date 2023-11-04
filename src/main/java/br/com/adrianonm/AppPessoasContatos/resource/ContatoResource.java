package br.com.adrianonm.AppPessoasContatos.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
		this.pessoaService  = pessoaService;
	}
	
	@PostMapping("/pessoas/{id}/contatos") // http://localhost:8080/api/pessoas/id/contatos
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
	

	@GetMapping("/contatos/{id}") //http://localhost:8080/api/contatos/id
	public ResponseEntity<Optional<Contato>> getById(@PathVariable Long id){
		Optional<Contato> contato = contatoService.getById(id);
		if(contato == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(contato);
	}
	
	@GetMapping("/pessoas/{id}/contatos") //http://localhost:8080/api/pessoas/idPessoa/contatos
	public ResponseEntity<List<Contato>> getContatosPessoa(@PathVariable Long id) {
	    List<Contato> contatos = contatoService.getContatos(id);
	    if (contatos.isEmpty()) {
	        return ResponseEntity.noContent().build();
	    } else {
	        return ResponseEntity.ok(contatos);
	    }
	}

	@PutMapping("/contatos/{id}") // http://localhost:8080/api/contatos/id
	public ResponseEntity<Contato> update(@RequestBody Contato contato,@PathVariable Long id){
		Optional<Contato> contatoEdit = contatoService.getById(id);
		Contato newContato = contatoEdit.get();
		
		if (contatoEdit.isPresent()) {
			newContato.setTipo(contato.getTipo());
			newContato.setContato(contato.getContato());
		}
		if(newContato == null)
			return ResponseEntity.notFound().build();
		contatoService.save(newContato);
		return ResponseEntity.ok(newContato);
	}
			
	@DeleteMapping("/contatos/{id}") // http://localhost:8080/api/contatos/id
	public Contato deleteContato(@PathVariable Long id){
		return contatoService.delete(id);
	}
}