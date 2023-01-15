package com.example.warehouse.service;

import com.example.warehouse.model.Nomenclature;

import java.util.List;
import java.util.Optional;

public interface NomenclatureService {

    Nomenclature createNomenclature(Nomenclature nomenclature);

    List<Nomenclature> getAllNomenclature();

    Optional<Nomenclature> getNomenclatureById(Long id);

    Nomenclature updateNomenclatureById(Long id, Nomenclature newNomenclature);

    void deleteNomenclatureById(Long id);

    void deleteAllNomenclatures();
}
