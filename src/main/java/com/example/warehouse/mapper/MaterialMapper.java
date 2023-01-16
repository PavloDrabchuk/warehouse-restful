package com.example.warehouse.mapper;

import com.example.warehouse.dto.material.MaterialCreateDTO;
import com.example.warehouse.dto.material.MaterialDTO;
import com.example.warehouse.model.Material;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE)
public interface MaterialMapper {

    @Mapping(target = "nomenclatureId", source = "nomenclature.id")
    @Mapping(target = "unitId", source = "unit.id")
    MaterialDTO toMaterialDTO(Material material);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "warehouse", ignore = true)
    @Mapping(target = "unit.id", source = "unitId")
    @Mapping(target = "nomenclature.id", source = "nomenclatureId")
    Material toMaterial(MaterialCreateDTO materialCreateDTO);

    void updateMaterialFromDto(MaterialDTO materialDTO, @MappingTarget Material material);
}
