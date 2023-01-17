package com.example.warehouse.service.impl;

import com.example.warehouse.dto.unit.UnitDTO;
import com.example.warehouse.mapper.UnitMapper;
import com.example.warehouse.model.Unit;
import com.example.warehouse.repository.UnitRepository;
import com.example.warehouse.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UnitServiceImpl implements UnitService {

    final private UnitRepository unitRepository;
    final private UnitMapper unitMapper;

    @Autowired
    public UnitServiceImpl(UnitRepository unitRepository, UnitMapper unitMapper) {
        this.unitRepository = unitRepository;
        this.unitMapper = unitMapper;
    }

    @Override
    public UnitDTO createUnit(UnitDTO unitDTO) {
        return unitMapper.toUnitDTO(unitRepository.save(unitMapper.toUnit(unitDTO)));
    }

    @Override
    public List<UnitDTO> getAllUnits() {
        return unitRepository.findAll().stream()
                .map(unitMapper::toUnitDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UnitDTO getUnitById(Long id) {
        return unitMapper.toUnitDTO(unitRepository.findById(id).orElse(null));
    }

    @Override
    public UnitDTO updateUnitById(Long id, UnitDTO unitDTO) {
        Optional<Unit> unit = unitRepository.findById(id);

        if (unit.isPresent()) {
            unitMapper.updateUnitFromDTO(unitDTO, unit.get());

            return unitMapper.toUnitDTO(unitRepository.save(unit.get()));
        }
        return null;
    }

    @Override
    public boolean deleteUnitById(Long id) {
        Optional<Unit> unit = unitRepository.findById(id);

        if (unit.isPresent()) {
            unitRepository.deleteById(id);

            return true;
        } else {
            return false;
        }
    }
}
