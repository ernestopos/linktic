package com.linktic.api.infrastructure.adapater;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.linktic.api.domain.model.Producto;
import com.linktic.api.domain.ports.ProductoRepositoryPort;
import com.linktic.api.infrastructure.entity.ProductoEntity;
import com.linktic.api.infrastructure.mapper.ProductoMapper;
import com.linktic.api.infrastructure.repository.ProductoJpaRepository;

@Component
public class ProductoRepositoryAdapter implements ProductoRepositoryPort {

	private final ProductoJpaRepository jpaRepository;
	private final ProductoMapper productoMapper;

	public ProductoRepositoryAdapter(ProductoJpaRepository jpaRepository, ProductoMapper productoMapper) {
		this.jpaRepository = jpaRepository;
		this.productoMapper=productoMapper;
	}

	@Override
	public Producto crear(Producto producto) {		
		ProductoEntity saved = jpaRepository.save(productoMapper.toEntity(producto));
		producto.setId(saved.getId());
		return producto;
	}

	@Override
	public Producto getProductoById(Long id) {
		return jpaRepository.findById(id).
				map(e -> new Producto(e.getId(), e.getNombre(), e.getDescripcion(), e.getPrecio())).orElse(new Producto());
	}

	@Override
	public List<Producto> listar() {
		return jpaRepository.findAll().stream().map(e -> new Producto(e.getId(), e.getNombre(), e.getDescripcion(), e.getPrecio()))
				.collect(Collectors.toList());
	}
}