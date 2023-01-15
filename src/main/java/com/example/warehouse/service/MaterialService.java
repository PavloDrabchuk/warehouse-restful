package com.example.warehouse.service;

import com.example.warehouse.dto.MaterialCreateDTO;
import com.example.warehouse.dto.MaterialDTO;
import com.example.warehouse.model.Material;
import com.example.warehouse.model.Warehouse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MaterialService {

    MaterialDTO createMaterial(MaterialCreateDTO materialCreateDTO);

    List<Material> getAllMaterials();

    List<MaterialDTO> getAllMaterialsByWarehouse(Warehouse warehouse);

    Optional<Material> getMaterialById(UUID id);

    int updateMaterialById(UUID id, Material newMaterial);

    void deleteMaterialById(UUID id);

    void deleteAllMaterials();
}
