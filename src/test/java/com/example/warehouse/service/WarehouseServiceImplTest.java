package com.example.warehouse.service;

import com.example.warehouse.dto.warehouse.WarehouseDTO;
import com.example.warehouse.mapper.WarehouseMapper;
import com.example.warehouse.model.Warehouse;
import com.example.warehouse.repository.WarehouseRepository;
import com.example.warehouse.service.impl.WarehouseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
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
        verify(warehouseMapper,times(1)).toWarehouseDTO(any(Warehouse.class));
    }

}
