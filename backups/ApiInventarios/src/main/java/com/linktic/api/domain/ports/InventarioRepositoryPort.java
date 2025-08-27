package com.linktic.api.domain.ports;

import com.linktic.api.domain.model.Inventario;

public interface InventarioRepositoryPort {
	Inventario saldoProducto(Long id);
	Inventario actualizarSaldo(Inventario inventario);	
}