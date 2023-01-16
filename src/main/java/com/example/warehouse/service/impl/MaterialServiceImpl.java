package com.example.warehouse.service.impl;

import com.example.warehouse.dto.MaterialCreateDTO;
import com.example.warehouse.dto.MaterialDTO;
import com.example.warehouse.mapper.MaterialMapper;
import com.example.warehouse.model.Material;
import com.example.warehouse.model.Warehouse;
import com.example.warehouse.repository.MaterialRepository;
import com.example.warehouse.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MaterialServiceImpl implements MaterialService {

    private final MaterialRepository materialRepository;
    private final MaterialMapper materialMapper;

    @Autowired
    public MaterialServiceImpl(MaterialRepository materialRepository, MaterialMapper materialMapper) {
        this.materialRepository = materialRepository;
        this.materialMapper = materialMapper;
    }

    @Override
    public MaterialDTO createMaterial(MaterialCreateDTO materialCreateDTO, Warehouse warehouse) {
        Material material = materialMapper.toMaterial(materialCreateDTO);
        material.setWarehouse(warehouse);

        return materialMapper.toMaterialDTO(materialRepository.save(material));
    }

    @Override
    public List<Material> getAllMaterials() {
        return (List<Material>) materialRepository.findAll();
    }

    @Override
    public List<MaterialDTO> getAllMaterialsByWarehouse(Warehouse warehouse) {
        return materialRepository.findAllByWarehouse(warehouse).stream()
                .map(materialMapper::toMaterialDTO)
                .collect(Collectors.toList());
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
