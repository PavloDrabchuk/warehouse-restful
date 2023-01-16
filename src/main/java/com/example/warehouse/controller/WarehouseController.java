package com.example.warehouse.controller;

import com.example.warehouse.dto.warehouse.WarehouseDTO;
import com.example.warehouse.service.WarehouseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/warehouses")
public class WarehouseController {

    final private WarehouseService warehouseService;

    @Autowired
    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @GetMapping
    public List<WarehouseDTO> getAllWarehouses() {
        return warehouseService.getAllWarehouses();
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<WarehouseDTO> getWarehouseById(@PathVariable("id") Long id) {
        WarehouseDTO warehouseDTO = warehouseService.getWarehouseById(id);

        return ResponseEntity
                .status((warehouseDTO != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                .body(warehouseDTO);
    }

    @PostMapping
    public ResponseEntity<WarehouseDTO> createWarehouse(@Valid @RequestBody WarehouseDTO warehouseDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(warehouseService.createWarehouse(warehouseDTO));
    }

    @PutMapping
    public ResponseEntity<WarehouseDTO> updateWarehouse(@Valid @RequestBody WarehouseDTO newWarehouseDTO) {
        WarehouseDTO warehouseDTO = warehouseService.updateWarehouseById(newWarehouseDTO.getId(), newWarehouseDTO);

        return ResponseEntity
                .status((warehouseDTO != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                .body(warehouseDTO);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Object> deleteWarehouseById(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(warehouseService.deleteWarehouseById(id)
                        ? HttpStatus.OK
                        : HttpStatus.BAD_REQUEST)
                .build();
    }
}
