package com.linktic.api.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.server.core.Relation;
import org.springframework.hateoas.RepresentationModel;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Relation(collectionRelation = "inventarios", itemRelation = "inventario")
public class Inventario extends RepresentationModel<Inventario> {
	private Long id;
	private Long idProducto;
	private Integer saldo;
}