package com.linktic.api.infrastructure.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "apis")
public class ApiProperties {

	private Producto producto;
	private Inventario inventario;

	@Data
	public static class Producto {
		private String url;
	}

	@Data
	public static class Inventario {
		private String url;
	}
}