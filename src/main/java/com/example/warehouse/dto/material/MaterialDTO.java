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

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Long getNomenclatureId() {
        return nomenclatureId;
    }

    public void setNomenclatureId(Long nomenclatureId) {
        this.nomenclatureId = nomenclatureId;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }
}
