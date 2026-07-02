package com.conaf.microservicio.despachos.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                .title("API de Gestión de Despachos")
                .version("1.0")
                .description("Endpoints para la administración y asignación de brigadas a incendios"));
    }
}
