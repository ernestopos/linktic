package com.linktic.api.application;

import com.linktic.api.domain.model.Inventario;
import com.linktic.api.domain.ports.InventarioRepositoryPort;

public class InventarioService {
	
	private final InventarioRepositoryPort inventarioRepository;

	public InventarioService(InventarioRepositoryPort inventarioRepository) {
		this.inventarioRepository = inventarioRepository;
	}
	
	public Inventario saldoProducto(Long id) {
		return inventarioRepository.saldoProducto(id);
	}
	
	public Inventario actualizarSaldo(Inventario inventario) {
		return inventarioRepository.actualizarSaldo(inventario);
	}		
}