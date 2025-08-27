package com.linktic.api.infrastructure.mapper;

import com.linktic.api.domain.model.Inventario;
import com.linktic.api.infrastructure.entity.InventarioEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-27T05:14:11-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.35.0.v20230814-2020, environment: Java 17.0.8.1 (Eclipse Adoptium)"
)
@Component
public class InventarioMapperImpl implements InventarioMapper {

    @Override
    public Inventario toPojo(InventarioEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Inventario inventario = new Inventario();

        return inventario;
    }

    @Override
    public InventarioEntity toEntity(Inventario rawclass) {
        if ( rawclass == null ) {
            return null;
        }

        InventarioEntity inventarioEntity = new InventarioEntity();

        return inventarioEntity;
    }
}
