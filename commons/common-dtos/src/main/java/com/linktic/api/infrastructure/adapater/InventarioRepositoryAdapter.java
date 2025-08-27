package com.linktic.api.infrastructure.adapater;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.linktic.api.domain.model.Inventario;
import com.linktic.api.domain.ports.InventarioRepositoryPort;
import com.linktic.api.infrastructure.entity.InventarioEntity;
import com.linktic.api.infrastructure.errors.NegocioException;
import com.linktic.api.infrastructure.mapper.InventarioMapper;
import com.linktic.api.infrastructure.repository.InventarioJpaRepository;

@Component
public class InventarioRepositoryAdapter implements InventarioRepositoryPort {

	private final InventarioJpaRepository jpaRepository;
	private final InventarioMapper inventarioMapper;

	public InventarioRepositoryAdapter(InventarioJpaRepository jpaRepository, InventarioMapper inventarioMapper) {
		this.jpaRepository = jpaRepository;
		this.inventarioMapper = inventarioMapper;
	}

	@Override
	public Inventario saldoProducto(Long id) {
		InventarioEntity inventory = jpaRepository.findByIdProducto(id);
		if (inventory == null) {
			throw new NegocioException("PRODUCTS_NO_EXIST",
					"No se encontró este producto id: " + id + " en el inventario",
					"La lista de productos consultada no contiene registros.");
		}
		return inventarioMapper.toPojo(inventory);
	}

	@Override
	public Inventario actualizarSaldo(Inventario inventario) {
		InventarioEntity inventory = jpaRepository.findByIdProducto(inventario.getIdProducto());
		if (inventory == null) {
			throw new NegocioException("PRODUCTS_NO_EXIST",
					"No se encontró este producto id: " + inventario.getIdProducto() + " en el inventario",
					"La lista de productos consultada no contiene registros.");
		}
		if (inventory != null) {
			inventory.setSaldo(inventario.getSaldo());
			jpaRepository.save(inventory);
		} else {
			jpaRepository.save(inventarioMapper.toEntity(inventario));
		}
		return inventario;
	}
}