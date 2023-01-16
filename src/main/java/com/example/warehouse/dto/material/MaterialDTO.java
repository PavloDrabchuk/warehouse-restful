package com.example.warehouse.dto.material;

import java.util.UUID;

public class MaterialDTO {
    UUID id;
    String name;
    Integer amount;
    Boolean status;
    Long nomenclatureId;
    Long unitId;

    public UUID getId() {
        return id;
    }
}
