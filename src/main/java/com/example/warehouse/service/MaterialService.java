package com.example.warehouse.service;

import com.example.warehouse.model.Material;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MaterialService {

    void createMaterial(Material material);

    List<Material> getAllMaterials();

    Optional<Material> getMaterialById(UUID id);

    int updateMaterialById(UUID id, Material newMaterial);

    void deleteMaterialById(UUID id);

    void deleteAllMaterials();
}
