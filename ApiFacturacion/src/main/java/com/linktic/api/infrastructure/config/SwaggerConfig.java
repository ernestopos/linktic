package com.linktic.api.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
	@Bean
	public OpenAPI apiInfo() {
		return new OpenAPI().info(
				new Info()
				.title("API COMPRAS")
				.description("Documentación que maneja las capacidades expuestar para el proceso de compras ")
				.version("1.0.0")
				.contact(new Contact()
				.name("Linktic Project")
				.url("https://www.apache.org/licenses/LICENSE-2.0")));

	}
}