package com.example.warehouse.mapper;

import com.example.warehouse.dto.MaterialDTO;
import com.example.warehouse.model.Material;
import org.springframework.stereotype.Component;

@Component
public class MaterialMapperImpl implements MaterialMapper {

    @Override
    public MaterialDTO toMaterialDTO(Material material) {
        if (material == null) {
            return null;
        }

        MaterialDTO materialDTO = new MaterialDTO();

        materialDTO.setId(material.getId());
        materialDTO.setName(material.getName());
        materialDTO.setStatus(material.getStatus());
        materialDTO.setNomenclatureId(material.getNomenclature().getId());
        materialDTO.setUnitId(material.getUnit().getId());

        return materialDTO;
    }
}
