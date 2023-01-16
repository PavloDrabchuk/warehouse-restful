package com.example.warehouse.controller;

import com.example.warehouse.dto.unit.UnitDTO;
import com.example.warehouse.service.UnitService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/warehouses/unit")
public class UnitController {

    final private UnitService unitService;

    @Autowired
    public UnitController(UnitService unitService) {
        this.unitService = unitService;
    }

    @GetMapping
    public List<UnitDTO> getAllUnits() {
        return unitService.getAllUnits();
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<UnitDTO> getUnitById(@PathVariable("id") Long id) {
        UnitDTO unitDTO = unitService.getUnitById(id);

        return ResponseEntity
                .status((unitDTO != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                .body(unitDTO);
    }

    @PostMapping
    public ResponseEntity<UnitDTO> createUnit(@Valid @RequestBody UnitDTO unitDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(unitService.createUnit(unitDTO));
    }

    @PutMapping
    public ResponseEntity<UnitDTO> updateUnit(@Valid @RequestBody UnitDTO newUnitDTO) {
        UnitDTO unitDTO = unitService.updateUnitById(newUnitDTO.getId(), newUnitDTO);

        return ResponseEntity
                .status((unitDTO != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                .body(unitDTO);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Object> deleteUnitById(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(unitService.deleteUnitById(id)
                        ? HttpStatus.OK
                        : HttpStatus.BAD_REQUEST)
                .build();
    }
}
