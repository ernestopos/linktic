package com.linktic.api.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.linktic.api.infrastructure.entity.InventarioEntity;

public interface InventarioJpaRepository extends JpaRepository<InventarioEntity, Long> {
	public InventarioEntity findByIdProducto(Long idProducto);
}