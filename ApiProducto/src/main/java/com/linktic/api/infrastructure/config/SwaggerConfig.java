package com.linktic.api.infrastructure.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
	@Bean
	public OpenAPI apiInfo() {
		return new OpenAPI()
				.info(new Info().title("API PRODUCTOS")
						.description("Documentación para las capacidades expuestas en los productos").version("1.0")
						.description("Documentación de la API con seguridad por API Key"))
				.components(
						new Components().addSecuritySchemes("apiKeyScheme",
								new SecurityScheme().type(SecurityScheme.Type.APIKEY).in(SecurityScheme.In.HEADER)
										.name("x-api-key")))
				.addSecurityItem(new SecurityRequirement().addList("apiKeyScheme"));
	}
}