package com.example.warehouse.service;

import com.example.warehouse.dto.nomenclature.NomenclatureDTO;
import com.example.warehouse.mapper.NomenclatureMapper;
import com.example.warehouse.model.Nomenclature;
import com.example.warehouse.repository.NomenclatureRepository;
import com.example.warehouse.service.impl.NomenclatureServiceImpl;
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
public class NomenclatureServiceImplTest {

    @InjectMocks
    private NomenclatureServiceImpl nomenclatureService;

    @Mock
    private NomenclatureRepository nomenclatureRepository;

    @Mock
    private NomenclatureMapper nomenclatureMapper;

    @Test
    void testCreateNomenclature() {
        Nomenclature nomenclature = new Nomenclature();
        when(nomenclatureRepository.save(nomenclature)).thenReturn(nomenclature);

        NomenclatureDTO nomenclatureDTO = nomenclatureService.createNomenclature(nomenclatureMapper.toNomenclatureDTO(nomenclature));
        assertEquals(nomenclatureDTO, nomenclatureMapper.toNomenclatureDTO(nomenclature));
    }

    @Test
    void testFindAllNomenclatures() {
        List<Nomenclature> nomenclatures = new ArrayList<>();
        nomenclatures.add(new Nomenclature());
        nomenclatures.add(new Nomenclature());

        when(nomenclatureRepository.findAll()).thenReturn(nomenclatures);
        nomenclatureService.getAllNomenclatures();

        verify(nomenclatureMapper, times(nomenclatures.size())).toNomenclatureDTO(any(Nomenclature.class));
    }

    @Test
    void testFindNomenclatureById() {
        Nomenclature nomenclature = new Nomenclature();
        when(nomenclatureRepository.findById(1L)).thenReturn(Optional.of(nomenclature));

        nomenclatureService.getNomenclatureById(1L);
        verify(nomenclatureMapper, times(1)).toNomenclatureDTO(any(Nomenclature.class));
    }

    @Test
    void testFailedUpdateNomenclatureById() {
        Nomenclature nomenclature = new Nomenclature();

        when(nomenclatureRepository.findById(1L)).thenReturn(Optional.of(nomenclature));

        NomenclatureDTO nomenclatureDTO = nomenclatureService.updateNomenclatureById(2L, nomenclatureMapper.toNomenclatureDTO(nomenclature));
        assertNull(nomenclatureDTO);
    }

    @Test
    void testUpdateNomenclatureById() {
        Nomenclature nomenclature = new Nomenclature("Nomenclature1");
        Nomenclature nomenclature2 = new Nomenclature("Nomenclature2");

        when(nomenclatureRepository.findById(1L)).thenReturn(Optional.of(nomenclature));
        when(nomenclatureRepository.findById(2L)).thenReturn(Optional.of(nomenclature2));

        NomenclatureDTO nomenclatureDTO = nomenclatureService.updateNomenclatureById(1L, nomenclatureMapper.toNomenclatureDTO(nomenclature2));
        assertEquals(nomenclatureMapper.toNomenclatureDTO(nomenclature2), nomenclatureDTO);
    }

    @Test
    void testDeleteNomenclatureById() {
        Nomenclature nomenclature = new Nomenclature();
        when(nomenclatureRepository.findById(1L)).thenReturn(Optional.of(nomenclature));

        boolean deleteStatus = nomenclatureService.deleteNomenclatureById(1L);
        assertTrue(deleteStatus);
    }

    @Test
    void testFailedDeleteNomenclatureById() {
        Nomenclature nomenclature = new Nomenclature();
        when(nomenclatureRepository.findById(1L)).thenReturn(Optional.of(nomenclature));

        boolean deleteStatus = nomenclatureService.deleteNomenclatureById(2L);
        assertFalse(deleteStatus);
    }
}
