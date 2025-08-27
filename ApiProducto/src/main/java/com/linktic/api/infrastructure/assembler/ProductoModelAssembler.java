package com.linktic.api.infrastructure.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import com.linktic.api.domain.model.Producto;
import com.linktic.api.infrastructure.controller.ProductoController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductoModelAssembler implements RepresentationModelAssembler<Producto, EntityModel<Producto>> {

	@Override
	public EntityModel<Producto> toModel(Producto producto) {
		return EntityModel.of(producto,
				linkTo(methodOn(ProductoController.class).obtener(producto.getId())).withSelfRel(),
				linkTo(methodOn(ProductoController.class).listar()).withRel("productos"));
	}

	@Override
	public CollectionModel<EntityModel<Producto>> toCollectionModel(Iterable<? extends Producto> productos) {
		return RepresentationModelAssembler.super.toCollectionModel(productos)
				.add(linkTo(methodOn(ProductoController.class).listar()).withSelfRel());
	}

}