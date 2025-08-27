package com.linktic.api.infrastructure.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;
import com.linktic.api.application.ProductoService;
import com.linktic.api.infrastructure.adapater.ProductoRepositoryAdapter;
import com.linktic.api.infrastructure.assembler.ProductoModelAssembler;
import com.linktic.api.infrastructure.errors.NegocioException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.linktic.api.domain.model.Producto;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
@Tag(name = "Productos", description = "Operaciones relacionadas con los productos")
public class ProductoController {

	private final ProductoService productoService;
	private final ProductoModelAssembler productoModelAssembler;

	public ProductoController(ProductoRepositoryAdapter adapter, ProductoModelAssembler productoModelAssembler) {
		this.productoService = new ProductoService(adapter);
		this.productoModelAssembler = productoModelAssembler;
	}

	@PostMapping
	@Operation(summary = "Crear producto", description = "Crea un nuevo producto en el sistema")
	@ApiResponse(responseCode = "201", description = "Producto creado exitosamente")
	public EntityModel<Producto> crear(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Objeto producto a crear", required = true, content = @Content(schema = @Schema(implementation = Producto.class))) @RequestBody Producto producto) {
		return productoModelAssembler.toModel(productoService.crearProducto(producto));
	}

	@GetMapping("/{id}")
	@Operation(summary = "Obtener producto por ID", description = "Busca un producto a partir de su identificador único")
	@ApiResponse(responseCode = "200", description = "Producto encontrado")
	@ApiResponse(responseCode = "404", description = "Producto no encontrado")
	public EntityModel<Producto> obtener(@Parameter(description = "ID del producto") @PathVariable Long id) {
		Producto producto = productoService.obtenerProducto(id);
		if (producto == null) {
			throw new NegocioException("PROD_NOT_FOUND", "El producto no existe",
					"No se encontró un producto con ID " + id);
		}
		return productoModelAssembler.toModel(producto);
	}

	@GetMapping
	@Operation(summary = "Listar productos", description = "Obtiene la lista completa de productos registrados en el sistema")
	@ApiResponse(responseCode = "200", description = "Lista de productos obtenida correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class)))
	public CollectionModel<EntityModel<Producto>> listar() {
		List<Producto> productos = productoService.listarProducto();
		if (productos == null || productos.isEmpty()) {
			throw new NegocioException("PRODUCTS_EMPTY", "No se encontraron productos disponibles",
					"La lista de productos consultada no contiene registros.");
		}
		return productoModelAssembler.toCollectionModel(productos);
	}
}