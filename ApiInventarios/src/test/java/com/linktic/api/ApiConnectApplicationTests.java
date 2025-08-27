package com.linktic.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.linktic.api.infrastructure.entity.InventarioEntity;
import com.linktic.api.infrastructure.repository.InventarioJpaRepository;

@SpringBootTest
class ApiConnectApplicationTests {
	
	@Mock
	private InventarioJpaRepository inventarioJpaRepository;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void shouldReturnProductsWhenIdExists() {
		when(inventarioJpaRepository.findByIdProducto(1L))
        .thenReturn(new InventarioEntity(1L, 1L, 100));		
		InventarioEntity inventario = inventarioJpaRepository.findByIdProducto(1L);		
		assertThat(inventario).isNotNull();
        verify(inventarioJpaRepository, times(1)).findByIdProducto(1L);        
	}

}
