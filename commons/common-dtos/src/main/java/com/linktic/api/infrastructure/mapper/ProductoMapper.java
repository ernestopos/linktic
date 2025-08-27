package com.linktic.api.infrastructure.mapper;

import org.mapstruct.Mapper;

import com.linktic.api.domain.model.Producto;
import com.linktic.api.infrastructure.entity.ProductoEntity;

@Mapper(componentModel = "spring")
public interface ProductoMapper {
	
	Producto toPojo(ProductoEntity entity);
	ProductoEntity toEntity(Producto rawclass);
	
}