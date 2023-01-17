package com.example.warehouse.service;

import com.example.warehouse.dto.warehouse.WarehouseDTO;
import com.example.warehouse.mapper.WarehouseMapper;
import com.example.warehouse.model.Warehouse;
import com.example.warehouse.repository.WarehouseRepository;
import com.example.warehouse.service.impl.WarehouseServiceImpl;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class WarehouseServiceImplTest {

    @InjectMocks
    private WarehouseServiceImpl warehouseService;

    @Mock
    private WarehouseRepository warehouseRepository;

    @Mock
    private WarehouseMapper warehouseMapper;

    @Test
    void testCreateWarehouse() {
        Warehouse warehouse = new Warehouse();
        when(warehouseRepository.save(warehouse)).thenReturn(warehouse);

        WarehouseDTO warehouseDTO = warehouseService.createWarehouse(warehouseMapper.toWarehouseDTO(warehouse));
        assertEquals(warehouseDTO, warehouseMapper.toWarehouseDTO(warehouse));
    }


    @Test
    void testFindAllWarehouses() {
        List<Warehouse> warehouses = new ArrayList<>();
        warehouses.add(new Warehouse());
        warehouses.add(new Warehouse());

        when(warehouseRepository.findAll()).thenReturn(warehouses);
        warehouseService.getAllWarehouses();

        verify(warehouseMapper, times(warehouses.size())).toWarehouseDTO(any(Warehouse.class));
    }

    @Test
    void testFindWarehouseById() {
        Warehouse warehouse = new Warehouse();
        when(warehouseRepository.findById(1L)).thenReturn(Optional.of(warehouse));

        warehouseService.getWarehouseById(1L);
        verify(warehouseMapper, times(1)).toWarehouseDTO(any(Warehouse.class));
    }

    @Test
    void testFailedUpdateWarehouseById() {
        Warehouse warehouse = new Warehouse();

        when(warehouseRepository.findById(1L)).thenReturn(Optional.of(warehouse));

        WarehouseDTO warehouseDTO = warehouseService.updateWarehouseById(2L, warehouseMapper.toWarehouseDTO(warehouse));
        assertNull(warehouseDTO);

    }

    @Test
    void testUpdateWarehouseById() {
        Warehouse warehouse = new Warehouse("Warehouse1");
        Warehouse warehouse2 = new Warehouse("Warehouse2");

        when(warehouseRepository.findById(1L)).thenReturn(Optional.of(warehouse));
        when(warehouseRepository.findById(2L)).thenReturn(Optional.of(warehouse2));

        WarehouseDTO warehouseDTO = warehouseService.updateWarehouseById(1L, warehouseMapper.toWarehouseDTO(warehouse2));
        assertEquals(warehouseMapper.toWarehouseDTO(warehouse2), warehouseDTO);
    }

    @Test
    void testDeleteWarehouseById() {
        Warehouse warehouse = new Warehouse();
        when(warehouseRepository.findById(1L)).thenReturn(Optional.of(warehouse));

        boolean deleteStatus = warehouseService.deleteWarehouseById(1L);
        assertTrue(deleteStatus);
    }

    @Test
    void testFailedDeleteWarehouseById() {
        Warehouse warehouse = new Warehouse();
        when(warehouseRepository.findById(1L)).thenReturn(Optional.of(warehouse));

        boolean deleteStatus = warehouseService.deleteWarehouseById(2L);
        assertFalse(deleteStatus);
    }

}
