package br.com.adrianonm.AppPessoasContatos.confg;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*"); // Permite acesso de qualquer origem
        config.addAllowedMethod("*"); // Permite todos os métodos HTTP (GET, POST, etc.)
        config.addAllowedHeader("*"); // Permite todos os cabeçalhos HTTP
        source.registerCorsConfiguration("/**", config); // Aplica a configuração a todas as URLs

        return new CorsFilter(source);
    }
}
