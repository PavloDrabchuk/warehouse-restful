package com.example.warehouse.service;

import com.example.warehouse.dto.material.MaterialCreateDTO;
import com.example.warehouse.dto.material.MaterialDTO;
import com.example.warehouse.dto.warehouse.WarehouseDTO;
import com.example.warehouse.model.Material;

import java.util.List;
import java.util.UUID;

public interface MaterialService {

    MaterialDTO createMaterial(MaterialCreateDTO materialCreateDTO);

    List<MaterialDTO> getAllMaterials();

    List<MaterialDTO> getAllMaterialsByWarehouseId(Long warehouseId);

    MaterialDTO getMaterialById(UUID id);

    MaterialDTO getMaterialByWarehouseIdAndId(Long warehouseId, UUID id);

    MaterialDTO updateMaterialById(MaterialDTO newMaterialDTO, WarehouseDTO warehouseDTO);

    void deleteMaterialById(UUID id);

    boolean deleteMaterialByWarehouseIdAndId(Long warehouseId, UUID id);
}
