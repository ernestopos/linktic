package com.linktic.api.infrastructure.assembler;

import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import com.linktic.api.domain.model.Producto;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ProductoAssembler implements RepresentationModelAssembler<Producto, Producto> {

	@Override
	public Producto toModel(Producto producto) {
		producto.add(linkTo(methodOn(com.linktic.api.infrastructure.controller.ProductoController.class)
				.obtener(producto.getId())).withSelfRel());
		return producto;
	}
}