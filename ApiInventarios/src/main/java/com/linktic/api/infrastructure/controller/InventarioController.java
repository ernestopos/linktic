package com.linktic.api.infrastructure.controller;

import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;
import com.linktic.api.application.InventarioService;
import com.linktic.api.infrastructure.adapater.InventarioRepositoryAdapter;
import com.linktic.api.infrastructure.assembler.InventarioModelAssembler;
import com.linktic.api.infrastructure.errors.NegocioException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.linktic.api.domain.model.Inventario;

@RestController
@RequestMapping("/api/inventarios")
@Tag(name = "Inventarios", description = "Operaciones relacionadas con el manejo de inventarios")
public class InventarioController {

	private final InventarioService inventarioService;
	private final InventarioModelAssembler inventarioModelAssembler;

	public InventarioController(InventarioRepositoryAdapter adapter, InventarioModelAssembler inventarioModelAssembler) {
		this.inventarioService = new InventarioService(adapter);
		this.inventarioModelAssembler = inventarioModelAssembler;
	}

	@PostMapping
	@Operation(summary = "Actualizar Inventario", description = "Actualiza el saldo de un producto")
	@ApiResponse(responseCode = "200", description = "Saldo actualizado correctamente")
	public EntityModel<Inventario> actualizarSaldo(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Objeto invenario a actualizar", required = true, content = @Content(schema = @Schema(implementation = Inventario.class))) @RequestBody Inventario inventario) {
		return inventarioModelAssembler.toModel(inventarioService.actualizarSaldo(inventario));
	}

	@GetMapping("/{idProducto}")
	@Operation(summary = "Obtener saldo por IdProducto", description = "Busca el saldo de un producto a partir de su identificador único")
	@ApiResponse(responseCode = "200", description = "Saldo del Producto")
	@ApiResponse(responseCode = "404", description = "Producto sin Saldo")
	public EntityModel<Inventario> saldoProducto(@Parameter(description = "ID del producto") @PathVariable Long idProducto) {
		return inventarioModelAssembler.toModel(inventarioService.saldoProducto(idProducto));
	}
}