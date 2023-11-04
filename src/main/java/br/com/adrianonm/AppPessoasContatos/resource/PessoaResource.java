package br.com.adrianonm.AppPessoasContatos.resource;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

import br.com.adrianonm.AppPessoasContatos.dto.PessoaDto;
import br.com.adrianonm.AppPessoasContatos.model.Pessoa;
import br.com.adrianonm.AppPessoasContatos.service.PessoaService;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/pessoas")// http://localhost:8080/api/pessoas
public class PessoaResource {
	
	private PessoaService pessoaService;
	
	@Autowired
	public PessoaResource(PessoaService pessoaService) {
		this.pessoaService = pessoaService;
	}

	@PostMapping    //http://localhost:8080/api/pessoas
	public ResponseEntity<Pessoa> save(@RequestBody Pessoa pessoa){
		Pessoa newPessoa = pessoaService.save(pessoa);
		if(newPessoa == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(newPessoa);
	}
	
	@GetMapping  // http://localhost:8080/api/pessoas
	public ResponseEntity<List<Pessoa>> getAllPessoas(){
		List<Pessoa> pessoa = pessoaService.getAll();
		if(pessoa == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(pessoa);
	}
	
	@GetMapping("/{id}") //http://localhost:8080/api/pessoas/id
	public ResponseEntity<Optional<Pessoa>> getById(@PathVariable Long id){
		Optional<Pessoa> pessoa = pessoaService.getById(id);
		if(pessoa == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(pessoa);
	}
	
	@PutMapping("/{id}") //http://localhost:8080/api/pessoas/id
	public ResponseEntity<Pessoa> update(@RequestBody Pessoa pessoa,@PathVariable Long id){
		Optional<Pessoa> pessoaEdit = pessoaService.getById(id);
		Pessoa newPessoa = pessoaEdit.get();
		if(pessoaEdit.isPresent()) {
			newPessoa.setNome(pessoa.getNome());
			newPessoa.setEndereco(pessoa.getEndereco());
			newPessoa.setCep(pessoa.getCep());
			newPessoa.setCidade(pessoa.getCidade());
			newPessoa.setUf(pessoa.getUf());
		}

		if(newPessoa == null)
			return ResponseEntity.notFound().build();
		
		pessoaService.save(newPessoa);
		return ResponseEntity.ok(newPessoa);
	}
	
	@DeleteMapping("/{id}") //http://localhost:8080/api/pessoas/id
	public ResponseEntity<?> delete(@PathVariable Long id){
		pessoaService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping ("/maladireta/{id}") //http://localhost:8080/api/pessoas/maladireta/id
	public ResponseEntity<List<PessoaDto>> findAll(){
		List<Pessoa> list = pessoaService.findAll();
		List<PessoaDto> listDto = list.stream().map(obj-> new PessoaDto(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
}
