package com.example.warehouse.service;

import com.example.warehouse.dto.material.MaterialDTO;
import com.example.warehouse.mapper.MaterialMapper;
import com.example.warehouse.mapper.WarehouseMapper;
import com.example.warehouse.model.Material;
import com.example.warehouse.model.Warehouse;
import com.example.warehouse.repository.MaterialRepository;
import com.example.warehouse.repository.WarehouseRepository;
import com.example.warehouse.service.impl.MaterialServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class MaterialServiceImplTest {

    @InjectMocks
    private MaterialServiceImpl materialService;

    @Mock
    private MaterialRepository materialRepository;

    @Mock
    private WarehouseRepository warehouseRepository;

    @Mock
    private MaterialMapper materialMapper;

    @Mock
    private WarehouseMapper warehouseMapper;

    @Test
    void testCreateMaterial() {
        Material material = new Material();
        when(materialRepository.save(material)).thenReturn(material);

        MaterialDTO materialDTO = materialService.createMaterial(materialMapper.toMaterialCreateDTO(material));

        assertEquals(materialDTO, materialMapper.toMaterialDTO(material));
    }

    @Test
    void testGetAllMaterials() {
        List<Material> materials = new ArrayList<>();
        materials.add(new Material());
        materials.add(new Material());

        when(materialRepository.findAll()).thenReturn(materials);
        materialService.getAllMaterials();

        verify(materialMapper, times(materials.size())).toMaterialDTO(any(Material.class));
    }

    @Test
    void testGetAllMaterialsByWarehouseId() {
        Warehouse warehouse = new Warehouse();
        when(warehouseRepository.findById(1L)).thenReturn(Optional.of(warehouse));

        List<Material> materials = new ArrayList<>();
        materials.add(new Material());
        materials.add(new Material());

        when(materialRepository.findAllByWarehouseId(1L)).thenReturn(materials);
        materialService.getAllMaterialsByWarehouseId(1L);

        verify(materialMapper, times(materials.size())).toMaterialDTO(any(Material.class));
    }

    @Test
    void testGetMaterialById() {
        Material material = new Material();
        material.setId(UUID.fromString("97088f04-83a4-4b8c-8e58-4ac27d03f78f"));
        when(materialRepository.findById(UUID.fromString("97088f04-83a4-4b8c-8e58-4ac27d03f78f"))).thenReturn(Optional.of(material));

        materialService.getMaterialById(UUID.fromString("97088f04-83a4-4b8c-8e58-4ac27d03f78f"));
        verify(materialMapper, times(1)).toMaterialDTO(any(Material.class));
    }

    @Test
    void testGetMaterialByWarehouseIdAndId() {
        Warehouse warehouse = new Warehouse();
        when(warehouseRepository.findById(1L)).thenReturn(Optional.of(warehouse));

        Material material = new Material();
        material.setId(UUID.fromString("97088f04-83a4-4b8c-8e58-4ac27d03f78f"));
        when(materialRepository.findMaterialByIdAndWarehouseId(UUID.fromString("97088f04-83a4-4b8c-8e58-4ac27d03f78f"), 1L)).thenReturn(Optional.of(material));

        materialService.getMaterialByWarehouseIdAndId(1L, UUID.fromString("97088f04-83a4-4b8c-8e58-4ac27d03f78f"));
        verify(materialMapper, times(1)).toMaterialDTO(any(Material.class));
    }

    @Test
    void testFailedGetMaterialByWarehouseIdAndId() {
        Warehouse warehouse = new Warehouse();
        when(warehouseRepository.findById(1L)).thenReturn(Optional.of(warehouse));

        Material material = new Material();
        material.setId(UUID.fromString("97088f04-83a4-4b8c-8e58-4ac27d03f78f"));
        when(materialRepository.findMaterialByIdAndWarehouseId(UUID.fromString("97088f04-83a4-4b8c-8e58-4ac27d03f78f"), 1L)).thenReturn(Optional.of(material));

        MaterialDTO materialDTO = materialService.getMaterialByWarehouseIdAndId(1L, UUID.fromString("87088f04-83a4-4b8c-8e58-4ac27d03f78f"));
        assertNull(materialDTO);
    }

    @Test
    void testDeleteMaterialById() {
        Material material = new Material();
        material.setId(UUID.fromString("97088f04-83a4-4b8c-8e58-4ac27d03f78f"));

        when(materialRepository.findById(UUID.fromString("97088f04-83a4-4b8c-8e58-4ac27d03f78f"))).thenReturn(Optional.of(material));

        materialService.deleteMaterialById(UUID.fromString("97088f04-83a4-4b8c-8e58-4ac27d03f78f"));
        assertEquals(0, materialRepository.findAll().size());
    }

    @Test
    void testDeleteMaterialByWarehouseIdAndId() {
        Warehouse warehouse = new Warehouse();
        when(warehouseRepository.findById(1L)).thenReturn(Optional.of(warehouse));

        Material material = new Material();
        material.setId(UUID.fromString("97088f04-83a4-4b8c-8e58-4ac27d03f78f"));

        when(materialRepository.findMaterialByIdAndWarehouseId(UUID.fromString("97088f04-83a4-4b8c-8e58-4ac27d03f78f"), 1L)).thenReturn(Optional.of(material));

        boolean deleteStatus = materialService.deleteMaterialByWarehouseIdAndId(1L, UUID.fromString("97088f04-83a4-4b8c-8e58-4ac27d03f78f"));
        assertTrue(deleteStatus);
    }

    @Test
    void testFailedDeleteMaterialByWarehouseIdAndId() {
        Warehouse warehouse = new Warehouse();
        when(warehouseRepository.findById(1L)).thenReturn(Optional.of(warehouse));

        Material material = new Material();
        material.setId(UUID.fromString("97088f04-83a4-4b8c-8e58-4ac27d03f78f"));

        when(materialRepository.findMaterialByIdAndWarehouseId(UUID.fromString("97088f04-83a4-4b8c-8e58-4ac27d03f78f"), 1L)).thenReturn(Optional.of(material));

        boolean deleteStatus = materialService.deleteMaterialByWarehouseIdAndId(2L, UUID.fromString("87088f04-83a4-4b8c-8e58-4ac27d03f78f"));
        assertFalse(deleteStatus);
    }
}
