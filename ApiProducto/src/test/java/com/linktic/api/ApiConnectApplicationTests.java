package com.linktic.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.linktic.api.application.ProductoService;
import com.linktic.api.domain.model.Producto;
import com.linktic.api.domain.ports.ProductoRepositoryPort;
import com.linktic.api.infrastructure.entity.ProductoEntity;
import com.linktic.api.infrastructure.repository.ProductoJpaRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ApiConnectApplicationTests {
	
	@Mock
	private ProductoJpaRepository productoJpaRepository;
	
	@InjectMocks
	private ProductoService productoService;
	
	@Mock
	private ProductoRepositoryPort port;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void shouldReturnProductsWhenIdExists() {
		when(productoJpaRepository.findById(1L)).thenReturn(Optional.of(new ProductoEntity(1L, "Zapatillas Nike", "zapatos deportivos",58000D)));
		ProductoEntity producto = productoJpaRepository.findById(1L).orElseThrow(() -> new RuntimeException("Producto no encontrado"));		
		assertThat(producto).isNotNull();
        verify(productoJpaRepository, times(1)).findById(1L);		
	}
	
	@Test
    void shouldSaveProduct() {
        Producto producto = new Producto(1L, "Zapatillas Nike", "zapatos deportivos", 58000D);
        when(port.crear(any(Producto.class))).thenReturn(producto);
        Producto savedProducto = productoService.crearProducto(new Producto(1L, "Zapatillas Nike", "zapatos deportivos", 58000D));
        assertThat(savedProducto).isNotNull();
        assertThat(savedProducto.getNombre()).isEqualTo("Zapatillas Nike");
        verify(port, times(1)).crear(any(Producto.class));
    }
}
