package com.example.warehouse.controller;

import com.example.warehouse.model.Warehouse;
import com.example.warehouse.service.WarehouseService;
import com.example.warehouse.service.impl.WarehouseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/warehouses")
public class WarehouseController {

    final private WarehouseService warehouseService;

    @Autowired
    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @GetMapping
    public List<Warehouse> getAllWarehouses() {
        return warehouseService.getAllWarehouse();
    }

    @GetMapping(path = "{id}")
    public Optional<Warehouse> getWarehouseById(@PathVariable("id") Long id) {
        return warehouseService.getWarehouseById(id);
    }

    @PostMapping
    public Warehouse createWarehouse(@RequestBody Warehouse warehouse) {
        return warehouseService.createWarehouse(warehouse);
    }

    @PutMapping
    public Warehouse updateWarehouse(@RequestBody Warehouse warehouse) {
        return warehouseService.updateWarehouseById(warehouse.getId(), warehouse);
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteWarehouseById(@PathVariable("id") Long id) {
        warehouseService.deleteWarehouseById(id);
    }
}
