package com.linktic.api.domain.ports;

import java.util.List;
import com.linktic.api.domain.model.Producto;

public interface ProductoRepositoryPort {
	Producto crear(Producto usuario);
	Producto getProductoById(Long id);
	List<Producto> listar();
}