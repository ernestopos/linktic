package com.linktic.api.application;

import java.util.List;
import com.linktic.api.domain.model.Producto;
import com.linktic.api.domain.ports.ProductoRepositoryPort;

public class ProductoService {
	
	private final ProductoRepositoryPort productoRepository;

	public ProductoService(ProductoRepositoryPort productoRepository) {
		this.productoRepository = productoRepository;
	}

	public Producto crearProducto(Producto producto) {
		return productoRepository.crear(producto);
	}

	public Producto obtenerProducto(Long id) {
		return productoRepository.getProductoById(id);
	}

	public List<Producto> listarProducto() {
		return productoRepository.listar();
	}	
}