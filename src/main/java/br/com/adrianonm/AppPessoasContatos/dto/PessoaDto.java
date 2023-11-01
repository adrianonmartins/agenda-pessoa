package br.com.adrianonm.AppPessoasContatos.dto;


import java.io.Serializable;
import br.com.adrianonm.AppPessoasContatos.model.Pessoa;


	


public class PessoaDto implements Serializable {
		
	private static final long serialVersionUID = 1L;
		
		private Long id;
		private String nome;
		private String malaDireta;
			
		public PessoaDto() {}
		
		public PessoaDto(Pessoa obj) {
			this.id = obj.getId();
			this.nome = obj.getNome();
			this.malaDireta = obj.getEndereco() + " - CEP: " + obj.getCep() + " - "+obj.getCidade()+ " / "+obj.getUf();
			
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getMalaDireta() {
			return malaDireta;
		}

		public void setMalaDireta(String malaDireta) {
			this.malaDireta = malaDireta;
		} 

		
		
}
