package com.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
@EnableWebMvc
public class SwaggerConfig {
	
    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("SPLAG-MT")
                .version("v1")
                .description("Documentação do projeto prático Splag-MT"));
    }
}
