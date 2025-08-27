package com.linktic.api.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.hateoas.server.core.Relation;
import org.springframework.hateoas.RepresentationModel;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Relation(collectionRelation = "productos", itemRelation = "producto")
public class Producto extends RepresentationModel<Producto> {
	private Long id;
	private String nombre;
	private String descripcion;
	private Double precio;
}