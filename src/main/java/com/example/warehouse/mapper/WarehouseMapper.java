package com.example.warehouse.mapper;

import com.example.warehouse.dto.warehouse.WarehouseDTO;
import com.example.warehouse.model.Warehouse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface WarehouseMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    WarehouseDTO toWarehouseDTO(Warehouse warehouse);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "materials", ignore = true)
    Warehouse toWarehouse(WarehouseDTO warehouseDTO);

    void updateWarehouseFromDTO(WarehouseDTO warehouseDTO, @MappingTarget Warehouse warehouse);
}
