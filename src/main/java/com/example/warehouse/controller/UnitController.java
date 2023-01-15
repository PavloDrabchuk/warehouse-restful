package com.example.warehouse.controller;

import com.example.warehouse.model.Unit;
import com.example.warehouse.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/warehouses/unit")
public class UnitController {

    final private UnitService unitService;

    @Autowired
    public UnitController(UnitService unitService) {
        this.unitService = unitService;
    }

    @GetMapping
    public List<Unit> getAllUnits() {
        return unitService.getAllUnits();
    }

    @GetMapping(path = "{id}")
    public Optional<Unit> hetUnitById(@PathVariable("id") Long id) {
        return unitService.getUnitById(id);
    }

    @PostMapping
    public Unit createUnit(@RequestBody Unit unit) {
        return unitService.createUnit(unit);
    }

    @PutMapping
    public Unit updateUnit(@RequestBody Unit unit) {
        return unitService.updateUnitById(unit.getId(), unit);
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUnitById(@PathVariable("id") Long id) {
        unitService.deleteUnitById(id);
    }
}
