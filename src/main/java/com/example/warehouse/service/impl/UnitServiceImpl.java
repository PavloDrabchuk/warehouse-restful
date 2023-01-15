package com.example.warehouse.service.impl;

import com.example.warehouse.model.Unit;
import com.example.warehouse.repository.UnitRepository;
import com.example.warehouse.service.UnitService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnitServiceImpl implements UnitService {

    final private UnitRepository unitRepository;

    public UnitServiceImpl(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    @Override
    public Unit createUnit(Unit unit) {
        return unitRepository.save(unit);
    }

    @Override
    public List<Unit> getAllUnits() {
        return (List<Unit>) unitRepository.findAll();
    }

    @Override
    public Optional<Unit> getUnitById(Long id) {
        return unitRepository.findById(id);
    }

    @Override
    public Unit updateUnitById(Long id, Unit newUnit) {
        Optional<Unit> unit = unitRepository.findById(id);

        if (unit.isPresent()) {
            unit.get().setName(newUnit.getName());

            return unitRepository.save(unit.get());
        }
        return unitRepository.save(newUnit);
    }

    @Override
    public void deleteUnitById(Long id) {
        unitRepository.deleteById(id);
    }

    @Override
    public void deleteAllUnits() {
        unitRepository.deleteAll();
    }
}
