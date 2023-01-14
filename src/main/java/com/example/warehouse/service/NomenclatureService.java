package com.example.warehouse.service;

import com.example.warehouse.model.Nomenclature;

import java.util.List;
import java.util.Optional;

public interface NomenclatureService {

    void createNomenclature(Nomenclature nomenclature);

    List<Nomenclature> getAllNomenclature();

    Optional<Nomenclature> getNomenclatureById(Long id);

    int updateNomenclatureById(Long id, Nomenclature newNomenclature);

    void deleteNomenclatureById(Long id);

    void deleteAllNomenclatures();
}
