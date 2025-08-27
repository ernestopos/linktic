package com.linktic.api.infrastructure.mapper;

import com.linktic.api.domain.model.Producto;
import com.linktic.api.infrastructure.entity.ProductoEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-27T05:14:11-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.35.0.v20230814-2020, environment: Java 17.0.8.1 (Eclipse Adoptium)"
)
@Component
public class ProductoMapperImpl implements ProductoMapper {

    @Override
    public Producto toPojo(ProductoEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Producto producto = new Producto();

        return producto;
    }

    @Override
    public ProductoEntity toEntity(Producto rawclass) {
        if ( rawclass == null ) {
            return null;
        }

        ProductoEntity productoEntity = new ProductoEntity();

        return productoEntity;
    }
}
