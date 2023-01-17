package com.example.warehouse.service.impl;

import com.example.warehouse.dto.material.MaterialCreateDTO;
import com.example.warehouse.dto.material.MaterialDTO;
import com.example.warehouse.dto.warehouse.WarehouseDTO;
import com.example.warehouse.mapper.MaterialMapper;
import com.example.warehouse.mapper.WarehouseMapper;
import com.example.warehouse.model.Material;
import com.example.warehouse.repository.MaterialRepository;
import com.example.warehouse.service.MaterialService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class MaterialServiceImpl implements MaterialService {

    private final MaterialRepository materialRepository;
    private final MaterialMapper materialMapper;
    private final WarehouseMapper warehouseMapper;

    @Autowired
    public MaterialServiceImpl(MaterialRepository materialRepository, MaterialMapper materialMapper, WarehouseMapper warehouseMapper) {
        this.materialRepository = materialRepository;
        this.materialMapper = materialMapper;
        this.warehouseMapper = warehouseMapper;
    }

    @Override
    public MaterialDTO createMaterial(MaterialCreateDTO materialCreateDTO) {
        Material material = materialMapper.toMaterial(materialCreateDTO);

        return materialMapper.toMaterialDTO(materialRepository.save(material));
    }

    @Override
    public List<MaterialDTO> getAllMaterials() {
        return materialRepository.findAll().stream()
                .map(materialMapper::toMaterialDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MaterialDTO> getAllMaterialsByWarehouseId(Long warehouseId) {
        return materialRepository.findAllByWarehouseId(warehouseId).stream()
                .map(materialMapper::toMaterialDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MaterialDTO getMaterialById(UUID id) {
        return materialMapper.toMaterialDTO(materialRepository.findById(id).orElse(null));
    }

    @Override
    public MaterialDTO getMaterialByWarehouseIdAndId(Long warehouseId, UUID id) {
        return materialMapper.toMaterialDTO(materialRepository.findMaterialByIdAndWarehouseId(id, warehouseId).orElse(null));
    }


    @Override
    public MaterialDTO updateMaterialById(MaterialDTO newMaterialDTO, WarehouseDTO warehouseDTO) {
        Optional<Material> material = materialRepository.findById(newMaterialDTO.getId());

        if (material.isPresent()) {
            materialMapper.updateMaterialFromDto(newMaterialDTO, material.get());
            return materialMapper.toMaterialDTO(materialRepository.save(material.get()));
        }
        return null;
    }

    @Override
    public void deleteMaterialById(UUID id) {
        materialRepository.deleteById(id);
    }

    @Override
    public boolean deleteMaterialByWarehouseIdAndId(Long warehouseId, UUID id) {
        Optional<Material> material = materialRepository.findMaterialByIdAndWarehouseId(id, warehouseId);

        if (material.isPresent()) {
            materialRepository.deleteMaterialByIdAndWarehouseId(id, warehouseId);
            return true;
        }
        return false;
    }
}
