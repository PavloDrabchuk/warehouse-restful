package com.example.warehouse.service.impl;

import com.example.warehouse.model.Material;
import com.example.warehouse.repository.MaterialRepository;
import com.example.warehouse.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MaterialServiceImpl implements MaterialService {

    private final MaterialRepository materialRepository;

    @Autowired
    public MaterialServiceImpl(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    @Override
    public void createMaterial(Material material) {
        materialRepository.save(material);
    }

    @Override
    public List<Material> getAllMaterials() {
        return (List<Material>) materialRepository.findAll();
    }

    @Override
    public Optional<Material> getMaterialById(UUID id) {
        return materialRepository.findById(id);
    }

    @Override
    public int updateMaterialById(UUID id, Material newMaterial) {
        Optional<Material> material = materialRepository.findById(id);

        if (material.isPresent()) {
            material.get().setName(newMaterial.getName());
            material.get().setStatus(newMaterial.getStatus());
            material.get().setNomenclature(newMaterial.getNomenclature());
            material.get().setUnit(newMaterial.getUnit());

            materialRepository.save(material.get());
            return 1;
        }

        return 0;
    }

    @Override
    public void deleteMaterialById(UUID id) {
        materialRepository.deleteById(id);
    }

    @Override
    public void deleteAllMaterials() {
        materialRepository.deleteAll();
    }
}
