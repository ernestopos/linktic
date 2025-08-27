package com.linktic.api.infrastructure.adapater;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.linktic.api.infrastructure.config.ApiProperties;
import reactor.util.retry.Retry;
import java.time.Duration;

@Service
@RequiredArgsConstructor
public class FacturaService {
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@Autowired
	private ApiProperties apiProperties;

	public String consultarProducto(Long idProducto) {
		return webClientBuilder.build()
				.get()
				.uri(apiProperties.getProducto().getUrl() + "/" + idProducto)
				.retrieve()
				.bodyToMono(String.class)
				.timeout(Duration.ofMillis(5000))
				.retryWhen(Retry.fixedDelay(3, Duration.ofMillis(1000)))
				.block();
	}

	public String consultarInventario(Long idProducto) {
		return webClientBuilder.build()
				.get()
				.uri(apiProperties.getInventario()
				.getUrl() + "/" + idProducto)
				.retrieve()
				.bodyToMono(String.class)
				.timeout(Duration.ofMillis(5000))
				.retryWhen(Retry.fixedDelay(3, Duration.ofMillis(1000))).block();
	}
}