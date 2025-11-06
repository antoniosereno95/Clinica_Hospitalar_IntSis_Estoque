package br.upe.intsis.estoque.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Estoque Hospitalar API")
                        .version("v1")
                        .description("Documentação dos endpoints REST do sistema de estoque hospitalar"));
    }
}