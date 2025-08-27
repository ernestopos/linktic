package com.linktic.api.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
	@Bean
	public WebClient productoWebClient(WebClient.Builder builder) {
		return builder.baseUrl("http://localhost:8081/api/productos").build();
	}

	@Bean
	public WebClient inventarioWebClient(WebClient.Builder builder) {
		return builder.baseUrl("http://localhost:8082/api/inventario") // tu ApiInventario
				.build();
	}
}