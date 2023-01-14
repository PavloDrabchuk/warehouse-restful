package com.example.warehouse.service.impl;

import com.example.warehouse.model.Nomenclature;
import com.example.warehouse.repository.NomenclatureRepository;
import com.example.warehouse.service.NomenclatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NomenclatureServiceImpl implements NomenclatureService {

    final private NomenclatureRepository nomenclatureRepository;

    @Autowired
    public NomenclatureServiceImpl(NomenclatureRepository nomenclatureRepository) {
        this.nomenclatureRepository = nomenclatureRepository;
    }

    @Override
    public void createNomenclature(Nomenclature nomenclature) {
        nomenclatureRepository.save(nomenclature);
    }

    @Override
    public List<Nomenclature> getAllNomenclature() {
        return (List<Nomenclature>) nomenclatureRepository.findAll();
    }

    @Override
    public Optional<Nomenclature> getNomenclatureById(Long id) {
        return nomenclatureRepository.findById(id);
    }

    @Override
    public int updateNomenclatureById(Long id, Nomenclature newNomenclature) {
        Optional<Nomenclature> nomenclature = nomenclatureRepository.findById(id);

        if (nomenclature.isPresent()) {
            nomenclature.get().setName(newNomenclature.getName());

            nomenclatureRepository.save(nomenclature.get());
            return 1;
        }

        return 0;
    }

    @Override
    public void deleteNomenclatureById(Long id) {
        nomenclatureRepository.deleteById(id);
    }

    @Override
    public void deleteAllNomenclatures() {
        nomenclatureRepository.deleteAll();
    }
}
