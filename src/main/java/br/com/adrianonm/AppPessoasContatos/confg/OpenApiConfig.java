package br.com.adrianonm.AppPessoasContatos.confg;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfig {
	
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.components(new Components().addSecuritySchemes("basicScheme",
						new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic")))
				.info(new Info()
						.title("App Agenda de Contatos")
						.description("Este aplicativo faz controle dos contatos de uma pessoaa")
						.contact(new Contact().name("Adriano Nascimento Martins").email("adrianonascimentomartins@gmail.com")
						.url("http://localhost:8080/swagger-ui/index.html"))
						.version("Versão 0.0.1-SNAPSHOT"));
	}
	
	
}
