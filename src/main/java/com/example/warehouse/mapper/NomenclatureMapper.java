package com.example.warehouse.mapper;

import com.example.warehouse.dto.nomenclature.NomenclatureDTO;
import com.example.warehouse.model.Nomenclature;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface NomenclatureMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    NomenclatureDTO toNomenclatureDTO(Nomenclature nomenclature);

    @Mapping(target = "id", ignore = true)
    Nomenclature toNomenclature(NomenclatureDTO nomenclatureDTO);

    void updateNomenclatureFromDTO(NomenclatureDTO nomenclatureDTO, @MappingTarget Nomenclature nomenclature);
}
