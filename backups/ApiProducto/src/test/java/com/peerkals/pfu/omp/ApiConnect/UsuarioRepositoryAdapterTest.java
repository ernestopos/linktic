package com.peerkals.pfu.omp.ApiConnect;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.linktic.api.domain.model.Usuario;
import com.linktic.api.infrastructure.adapater.UsuarioRepositoryAdapter;
import com.linktic.api.infrastructure.entity.UsuarioEntity;
import com.linktic.api.infrastructure.repository.UsuarioJpaRepository;

public class UsuarioRepositoryAdapterTest {

	@Mock
	private UsuarioJpaRepository jpaRepository;

	@Mock
	private UsuarioRepositoryAdapter usuarioRepositoryAdapter;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this); // Inicializa los mocks
	}

	@Test
    void shouldReturnUserWhenIdExists() {
         when(jpaRepository.findById(Long.parseLong("1")))
        .thenReturn(Optional.of(new UsuarioEntity(Long.parseLong("1"), "Juan Pérez", "juan@example.com")));
         UsuarioEntity usuario = jpaRepository.findById(Long.parseLong("1"))
				    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        assertThat(usuario).isNotNull();
        verify(jpaRepository, times(1)).findById(Long.parseLong("1"));
    }
}
