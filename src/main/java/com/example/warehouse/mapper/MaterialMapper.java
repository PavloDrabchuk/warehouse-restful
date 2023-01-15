package com.example.warehouse.mapper;

import com.example.warehouse.dto.MaterialCreateDTO;
import com.example.warehouse.dto.MaterialDTO;
import com.example.warehouse.model.Material;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MaterialMapper {

    @Mapping(target = "nomenclatureId", source = "nomenclature.id")
    @Mapping(target = "unitId", source = "unit.id")
    MaterialDTO toMaterialDTO(Material material);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "warehouse", ignore = true)
    Material toMaterial(MaterialCreateDTO materialCreateDTO);
}
