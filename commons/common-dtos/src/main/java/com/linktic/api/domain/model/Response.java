package com.linktic.api.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.server.core.Relation;
import org.springframework.hateoas.RepresentationModel;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Relation(collectionRelation = "responses", itemRelation = "response")
public class Response extends RepresentationModel<Response> {
	private Long idvalue;
	private String msg;
}