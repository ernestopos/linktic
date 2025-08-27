package com.linktic.api.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Compra {
	private Long idProducto;
	private Integer cantidad;
	private String result;
}