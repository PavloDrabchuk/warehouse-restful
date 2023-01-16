package com.example.warehouse.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(nullable = false, length = 40)
    private String name;

    @Column(nullable = false)
    private Integer amount;

    @Column(nullable = false)
    private Boolean status = true;

    @OneToOne(optional = false)
    @JoinColumn(nullable = false, unique = true)
    private Nomenclature nomenclature;

    @ManyToOne
    @JoinColumn
    private Unit unit;

    @ManyToOne
    @JoinColumn
    private Warehouse warehouse;

    public Integer getAmount() {
        return amount;
    }


    public Material() {
    }

    public Material(String name, Boolean status, Nomenclature nomenclature, Unit unit) {
        this.name = name;
        this.status = status;
        this.nomenclature = nomenclature;
        this.unit = unit;
    }

    public Material(String name, Boolean status, Nomenclature nomenclature, Unit unit, Warehouse warehouse) {
        this.name = name;
        this.status = status;
        this.nomenclature = nomenclature;
        this.unit = unit;
        this.warehouse = warehouse;
    }

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

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Nomenclature getNomenclature() {
        return nomenclature;
    }

    public void setNomenclature(Nomenclature nomenclature) {
        this.nomenclature = nomenclature;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }
}
