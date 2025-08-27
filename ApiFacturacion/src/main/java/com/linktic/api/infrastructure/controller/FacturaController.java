package com.linktic.api.infrastructure.controller;

import org.springframework.web.bind.annotation.*;
import com.linktic.api.infrastructure.errors.NegocioException;
import com.linktic.api.infrastructure.services.FacturaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.linktic.api.domain.model.Compra;
import com.linktic.api.domain.model.Inventario;
import com.linktic.api.domain.model.Producto;

@RestController
@RequestMapping("/api/compra")
@Tag(name = "compra", description = "Operaciones relacionadas a las compras")
public class FacturaController {

	private final FacturaService facturaService;

	public FacturaController(FacturaService facturaService) {
		this.facturaService = facturaService;
	}

	@PostMapping
	@Operation(summary = "Realizar Compra", description = "Realiza el proceso de compra, validando la existencia en el inventario")
	@ApiResponse(responseCode = "200", description = "Compra realizada correctamente")
	public Compra realizarCompra(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Objeto que gestiona la compra", required = true, content = @Content(schema = @Schema(implementation = Compra.class))) @RequestBody Compra compra) {
		Producto producto = facturaService.consultarProducto(compra.getIdProducto());
		Inventario inventario = facturaService.consultarInventario(producto.getId());
		if (compra.getCantidad() > inventario.getSaldo()) {
			throw new NegocioException("INV001", "No hay saldo suficiente",
					"La cantidad que intenta vender no existe en el inventario");
		}
		inventario.setSaldo(inventario.getSaldo() - compra.getCantidad());
		compra.setResult("La compra se realizó correctamente, el nuevo saldo del producto: " + producto.getNombre()
				+ ", es  " + facturaService.actualizarInventario(inventario).getSaldo());
		return compra;
	}
}