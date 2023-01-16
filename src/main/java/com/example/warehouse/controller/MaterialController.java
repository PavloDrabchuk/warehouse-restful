package com.example.warehouse.controller;

import com.example.warehouse.dto.material.MaterialCreateDTO;
import com.example.warehouse.dto.material.MaterialDTO;
import com.example.warehouse.dto.warehouse.WarehouseDTO;
import com.example.warehouse.service.MaterialService;
import com.example.warehouse.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @GetMapping(path = "{id}/materials")
    public ResponseEntity<List<MaterialDTO>> getAllMaterialsByWarehouseId(@PathVariable("id") Long id) {
        WarehouseDTO warehouseDTO = warehouseService.getWarehouseById(id);

        if (warehouseDTO == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(materialService.getAllMaterialsByWarehouseId(warehouseDTO.getId()));
    }


    @GetMapping(path = "{warehouseId}/material")
    public ResponseEntity<MaterialDTO> getMaterialByIdAndWarehouse(@PathVariable("warehouseId") Long warehouseId,
                                                                   @RequestParam(name = "id") String materialId) {
        WarehouseDTO warehouse = warehouseService.getWarehouseById(warehouseId);

        if (warehouse == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        MaterialDTO materialDTO = materialService.getMaterialByWarehouseIdAndId(warehouse.getId(), UUID.fromString(materialId));

        return ResponseEntity
                .status((materialDTO != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                .body(materialDTO);
    }


    @PostMapping(path = "{id}/material")
    public ResponseEntity<MaterialDTO> createMaterial(@PathVariable("id") Long warehouseId,
                                                      @RequestBody MaterialCreateDTO materialCreateDTO) {
        WarehouseDTO warehouse = warehouseService.getWarehouseById(warehouseId);

        if (warehouse != null) {
            materialCreateDTO.setWarehouseId(warehouseId);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(materialService.createMaterial(materialCreateDTO));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @PutMapping(path = "{id}/material")
    public ResponseEntity<MaterialDTO> updateMaterial(@PathVariable("id") Long warehouseId,
                                                      @RequestBody MaterialDTO materialDTO) {
        WarehouseDTO warehouse = warehouseService.getWarehouseById(warehouseId);

        if (warehouse != null) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(materialService.updateMaterialById(materialDTO, warehouse));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping(path = "{warehouseId}/material")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> deleteMaterialByWarehouseAndId(@PathVariable("warehouseId") Long warehouseId,
                                                                 @RequestParam("id") String materialId) {

        WarehouseDTO warehouse = warehouseService.getWarehouseById(warehouseId);

        if (warehouse != null) {

            return ResponseEntity
                    .status(materialService.deleteMaterialByWarehouseIdAndId(warehouse.getId(), UUID.fromString(materialId))
                            ? HttpStatus.OK
                            : HttpStatus.BAD_REQUEST)
                    .build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
