package com.example.warehouse.mapper;

import com.example.warehouse.dto.MaterialDTO;
import com.example.warehouse.model.Material;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MaterialMapper {

    MaterialDTO toMaterialDTO(Material material);
}
