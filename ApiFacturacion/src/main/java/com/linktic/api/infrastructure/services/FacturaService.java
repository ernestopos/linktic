package com.linktic.api.infrastructure.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;

import com.linktic.api.domain.model.Producto;
import com.linktic.api.domain.model.Inventario;
import reactor.util.retry.Retry;
import java.time.Duration;
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatusCode;
import com.linktic.api.infrastructure.errors.NegocioException;
import com.linktic.api.utilities.UtilitiesSources;

@Service
@RequiredArgsConstructor
public class FacturaService {

	@Autowired
	private WebClient.Builder webClientBuilder;

	@Value("${apis.producto.url}")
	private String productoUrl;
	
	@Value("${apis.inventario.url}")
	private String inventarioUrl;
	
	@Value("${apis.producto.api.key}")
	private String apiKeyProducto;
	
	@Value("${apis.inventario.api.key}")
	private String apiKeyInventario;
	
	public Producto consultarProducto(Long idProducto) {
		try {
			return webClientBuilder.build().get().uri(productoUrl + "/" + idProducto)
					.header("x-api-key", apiKeyProducto)
					.retrieve()
					.onStatus(HttpStatusCode::is4xxClientError, response -> response.bodyToMono(String.class).map(
							msg -> new NegocioException("ERR-CLIENT", "Error de cliente en Producto: " + msg, "ERROR")))
					.onStatus(HttpStatusCode::is5xxServerError,
							response -> response.bodyToMono(String.class)
									.map(msg -> new NegocioException("ERR-SERVER",
											"Error de servidor en Producto: " + msg, "ERROR")))
					.bodyToMono(Producto.class).timeout(Duration.ofMillis(5000))
					.retryWhen(Retry.fixedDelay(3, Duration.ofMillis(1000))).block();
		} catch (WebClientRequestException ex) {
			throw new NegocioException("WebClientRequestException", UtilitiesSources.getCausas(ex.getCause()),
					"Error al lanzar el método: consultarProducto");
		} catch (Exception ex) {
			throw new NegocioException("ERROR", UtilitiesSources.getCausas(ex.getCause()), "Error al lanzar el método: consultarProducto");
		}
	}

	public Inventario consultarInventario(Long idProducto) {
		try {
			return webClientBuilder.build().get().uri(inventarioUrl + "/" + idProducto)
					.header("x-api-key", apiKeyInventario)
					.retrieve().bodyToMono(Inventario.class).timeout(Duration.ofMillis(5000))
					.retryWhen(Retry.fixedDelay(3, Duration.ofMillis(1000))).block();
		} catch (WebClientRequestException ex) {
			throw new NegocioException("WebClientRequestException", "Error al lanzar el método: consultarInventario",
					"No se pudo consultar el inventario");
		} catch (Exception ex) {
			throw new NegocioException("ERROR", ex.getMessage(), "Error al lanzar el método: consultarInventario");
		}
	}

	public Inventario actualizarInventario(Inventario inventario) {
		try {
			return webClientBuilder.build().post().uri(inventarioUrl)
					.header("x-api-key", apiKeyInventario)
					.contentType(MediaType.APPLICATION_JSON).bodyValue(inventario).retrieve()
					.bodyToMono(Inventario.class).timeout(Duration.ofMillis(5000))
					.retryWhen(Retry.fixedDelay(3, Duration.ofMillis(1000))).block();
		} catch (WebClientRequestException ex) {
			throw new NegocioException("WebClientRequestException", "Error al lanzar el método: actualizarInventario",
					"No se pudo actualizar el saldo del producto");
		} catch (Exception ex) {
			throw new NegocioException("ERR-UNKNOWN", "Error al lanzar el método: consultarProducto",
					"Error inesperado al consumir el API de Inventarios");
		}
	}
}