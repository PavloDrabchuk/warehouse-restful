package com.example.warehouse.service;

import com.example.warehouse.dto.unit.UnitDTO;
import com.example.warehouse.dto.warehouse.WarehouseDTO;
import com.example.warehouse.mapper.UnitMapper;
import com.example.warehouse.model.Unit;
import com.example.warehouse.model.Warehouse;
import com.example.warehouse.repository.UnitRepository;
import com.example.warehouse.service.impl.UnitServiceImpl;
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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class UnitServiceImplTest {

    @InjectMocks
    private UnitServiceImpl unitService;

    @Mock
    private UnitRepository unitRepository;

    @Mock
    private UnitMapper unitMapper;


    @Test
    void testCreateUnit() {
        Unit unit = new Unit();
        when(unitRepository.save(unit)).thenReturn(unit);

        UnitDTO unitDTO = unitService.createUnit(unitMapper.toUnitDTO(unit));
        assertEquals(unitDTO, unitMapper.toUnitDTO(unit));
    }

    @Test
    void testFindAllUnits() {
        List<Unit> units = new ArrayList<>();
        units.add(new Unit());
        units.add(new Unit());

        when(unitRepository.findAll()).thenReturn(units);
        unitService.getAllUnits();

        verify(unitMapper, times(units.size())).toUnitDTO(any(Unit.class));
    }

    @Test
    void testFindUnitById() {
        Unit unit = new Unit();
        when(unitRepository.findById(1L)).thenReturn(Optional.of(unit));

        unitService.getUnitById(1L);
        verify(unitMapper, times(1)).toUnitDTO(any(Unit.class));
    }

    @Test
    void testFailedUpdateUnitById() {
        Unit unit = new Unit();

        when(unitRepository.findById(1L)).thenReturn(Optional.of(unit));

        UnitDTO unitDTO = unitService.updateUnitById(2L, unitMapper.toUnitDTO(unit));
        assertNull(unitDTO);
    }

    @Test
    void testUpdateUnitById() {
        Unit unit = new Unit("Unit1");
        Unit unit2 = new Unit("Unit2");

        when(unitRepository.findById(1L)).thenReturn(Optional.of(unit));
        when(unitRepository.findById(2L)).thenReturn(Optional.of(unit));

        UnitDTO unitDTO = unitService.updateUnitById(1L, unitMapper.toUnitDTO(unit2));
        assertEquals(unitMapper.toUnitDTO(unit2), unitDTO);
    }

    @Test
    void testDeleteUnitById() {
        Unit unit = new Unit();
        when(unitRepository.findById(1L)).thenReturn(Optional.of(unit));

        boolean deleteStatus = unitService.deleteUnitById(1L);
        assertTrue(deleteStatus);
    }

    @Test
    void testFailedDeleteUnitById() {
        Unit unit = new Unit();
        when(unitRepository.findById(1L)).thenReturn(Optional.of(unit));

        boolean deleteStatus = unitService.deleteUnitById(2L);
        assertFalse(deleteStatus);
    }
}
