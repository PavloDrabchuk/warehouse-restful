package com.example.warehouse.model;


import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(nullable = false, unique = true, length = 40)
    private String name;

    @Column(nullable = false)
    private Boolean status = false;

    @OneToOne(optional = false)
    @JoinColumn(nullable = false, unique = true)
    private Nomenclature nomenclature;

    @ManyToOne
    private Unit unit;

    public Material() {
    }

    public Material(String name, Boolean status, Nomenclature nomenclature, Unit unit) {
        this.name = name;
        this.status = status;
        this.nomenclature = nomenclature;
        this.unit = unit;
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
}
