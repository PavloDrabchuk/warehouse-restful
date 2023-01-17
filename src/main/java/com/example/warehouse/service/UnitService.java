package com.example.warehouse.service;

import com.example.warehouse.dto.unit.UnitDTO;

import java.util.List;

public interface UnitService {

    UnitDTO createUnit(UnitDTO unitDTO);

    List<UnitDTO> getAllUnits();

    UnitDTO getUnitById(Long id);

    UnitDTO updateUnitById(Long id, UnitDTO unitDTO);

    boolean deleteUnitById(Long id);
}
