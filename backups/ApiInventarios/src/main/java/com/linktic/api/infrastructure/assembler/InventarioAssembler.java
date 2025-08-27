package com.linktic.api.infrastructure.assembler;

import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import com.linktic.api.domain.model.Inventario;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class InventarioAssembler implements RepresentationModelAssembler<Inventario, Inventario> {

	@Override
	public Inventario toModel(Inventario inventario) {
		inventario.add(linkTo(methodOn(com.linktic.api.infrastructure.controller.InventarioController.class)
				.saldoProducto(inventario.getId())).withSelfRel());
		return inventario;
	}
}