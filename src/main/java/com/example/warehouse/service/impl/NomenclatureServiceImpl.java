package com.example.warehouse.service.impl;

import com.example.warehouse.dto.nomenclature.NomenclatureDTO;
import com.example.warehouse.mapper.NomenclatureMapper;
import com.example.warehouse.model.Nomenclature;
import com.example.warehouse.repository.NomenclatureRepository;
import com.example.warehouse.service.NomenclatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NomenclatureServiceImpl implements NomenclatureService {

    final private NomenclatureRepository nomenclatureRepository;
    final private NomenclatureMapper nomenclatureMapper;

    @Autowired
    public NomenclatureServiceImpl(NomenclatureRepository nomenclatureRepository, NomenclatureMapper nomenclatureMapper) {
        this.nomenclatureRepository = nomenclatureRepository;
        this.nomenclatureMapper = nomenclatureMapper;
    }

    @Override
    public NomenclatureDTO createNomenclature(NomenclatureDTO nomenclatureDTO) {
        return nomenclatureMapper.toNomenclatureDTO(
                nomenclatureRepository.save(nomenclatureMapper.toNomenclature(nomenclatureDTO)));

    }

    @Override
    public List<NomenclatureDTO> getAllNomenclatures() {
        return nomenclatureRepository.findAll().stream()
                .map(nomenclatureMapper::toNomenclatureDTO)
                .collect(Collectors.toList());
    }

    @Override
    public NomenclatureDTO getNomenclatureById(Long id) {
        return nomenclatureMapper.toNomenclatureDTO(nomenclatureRepository.findById(id).orElse(null));
    }

    @Override
    public NomenclatureDTO updateNomenclatureById(Long id, NomenclatureDTO nomenclatureDTO) {
        Optional<Nomenclature> nomenclature = nomenclatureRepository.findById(id);

        if (nomenclature.isPresent()) {
            nomenclatureMapper.updateNomenclatureFromDTO(nomenclatureDTO, nomenclature.get());

            return nomenclatureMapper.toNomenclatureDTO(nomenclatureRepository.save(nomenclature.get()));
        }
        return null;
    }

    @Override
    public boolean deleteNomenclatureById(Long id) {
        Optional<Nomenclature> nomenclature = nomenclatureRepository.findById(id);

        if (nomenclature.isPresent()) {
            nomenclatureRepository.deleteById(id);

            return true;
        } else {
            return false;
        }
    }
}
