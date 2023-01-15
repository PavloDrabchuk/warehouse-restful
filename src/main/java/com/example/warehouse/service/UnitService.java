package com.example.warehouse.service;

import com.example.warehouse.model.Unit;

import java.util.List;
import java.util.Optional;

public interface UnitService {

    Unit createUnit(Unit unit);

    List<Unit> getAllUnits();

    Optional<Unit> getUnitById(Long id);

    Unit updateUnitById(Long id, Unit newUnit);

    void deleteUnitById(Long id);

    void deleteAllUnits();
}
