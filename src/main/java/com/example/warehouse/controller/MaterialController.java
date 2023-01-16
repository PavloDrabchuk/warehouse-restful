package com.example.warehouse.controller;

import com.example.warehouse.dto.MaterialCreateDTO;
import com.example.warehouse.dto.MaterialDTO;
import com.example.warehouse.model.Material;
import com.example.warehouse.model.Warehouse;
import com.example.warehouse.service.MaterialService;
import com.example.warehouse.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/warehouses")
public class MaterialController {

    final private MaterialService materialService;
    final private WarehouseService warehouseService;

    @Autowired
    public MaterialController(MaterialService materialService, WarehouseService warehouseService) {
        this.materialService = materialService;
        this.warehouseService = warehouseService;
    }

    @GetMapping(path = "{id}/material")
    public ResponseEntity<List<MaterialDTO>> getAllMaterialsByWarehouseId(@PathVariable("id") Long id) {
        Optional<Warehouse> warehouse = warehouseService.getWarehouseById(id);

        if (warehouse.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(materialService.getAllMaterialsByWarehouse(warehouse.get()));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping(path = "{id}/material")
    public ResponseEntity<MaterialDTO> createMaterial(@PathVariable("id") Long id,
                                                      @RequestBody MaterialCreateDTO materialCreateDTO) {
        Optional<Warehouse> warehouse = warehouseService.getWarehouseById(id);

        if (warehouse.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(materialService.createMaterial(materialCreateDTO, warehouse.get()));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }
}
