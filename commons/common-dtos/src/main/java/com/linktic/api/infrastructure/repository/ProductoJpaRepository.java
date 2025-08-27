package com.linktic.api.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.linktic.api.infrastructure.entity.ProductoEntity;

public interface ProductoJpaRepository extends JpaRepository<ProductoEntity, Long> {
	public ProductoEntity findByNombre(String nombre);
}