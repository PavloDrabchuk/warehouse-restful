package com.example.warehouse.mapper;

import com.example.warehouse.dto.unit.UnitDTO;
import com.example.warehouse.model.Unit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UnitMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    UnitDTO toUnitDTO(Unit unit);

    @Mapping(target = "id", ignore = true)
    Unit toUnit(UnitDTO unitDTO);

    void updateUnitFromDTO(UnitDTO unitDTO, @MappingTarget Unit unit);
}
