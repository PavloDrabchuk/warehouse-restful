package com.example.warehouse.service;

import com.example.warehouse.dto.nomenclature.NomenclatureDTO;

import java.util.List;

public interface NomenclatureService {

    NomenclatureDTO createNomenclature(NomenclatureDTO nomenclatureDTO);

    List<NomenclatureDTO> getAllNomenclatures();

    NomenclatureDTO getNomenclatureById(Long id);

    NomenclatureDTO updateNomenclatureById(Long id, NomenclatureDTO nomenclatureDTO);

    boolean deleteNomenclatureById(Long id);

    void deleteAllNomenclatures();
}
