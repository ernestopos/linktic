package com.linktic.api.infrastructure.mapper;

import org.mapstruct.Mapper;

import com.linktic.api.domain.model.Inventario;
import com.linktic.api.infrastructure.entity.InventarioEntity;

@Mapper(componentModel = "spring")
public interface InventarioMapper {	
	Inventario toPojo(InventarioEntity entity);
	InventarioEntity toEntity(Inventario rawclass);	
}