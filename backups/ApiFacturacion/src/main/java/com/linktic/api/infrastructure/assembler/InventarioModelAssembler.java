package com.linktic.api.infrastructure.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import com.linktic.api.domain.model.Inventario;
import com.linktic.api.infrastructure.controller.InventarioController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class InventarioModelAssembler implements RepresentationModelAssembler<Inventario, EntityModel<Inventario>> {

	@Override
	public EntityModel<Inventario> toModel(Inventario inventario) {
		return EntityModel.of(inventario,
				linkTo(methodOn(InventarioController.class).saldoProducto(inventario.getId())).withSelfRel(),
				linkTo(methodOn(InventarioController.class).actualizarSaldo(inventario)).withSelfRel());
	}
}