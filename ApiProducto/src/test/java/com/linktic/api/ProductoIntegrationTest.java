package com.linktic.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import static org.assertj.core.api.Assertions.assertThat;
import com.linktic.api.domain.model.Producto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application.properties")
public class ProductoIntegrationTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	private String baseUrl;

	@BeforeEach
	void setUp() {
		baseUrl = "http://localhost:" + port + "/api/productos";
	}
	
	@Test
    void shouldCreateProductoWithApiKey() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", "PROD-123456");
        headers.setContentType(MediaType.APPLICATION_JSON);
        Producto producto = new Producto(1L, "Zapatillas Nike", "zapatos deportivos", 58000D);
        HttpEntity<Producto> request = new HttpEntity<>(producto, headers);
        ResponseEntity<Producto> response = restTemplate.postForEntity(baseUrl, request, Producto.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getNombre()).isEqualTo("Zapatillas Nike");
    }
}