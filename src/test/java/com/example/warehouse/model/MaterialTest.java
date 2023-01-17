package com.example.warehouse.model;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MaterialTest {

    @Test
    void testMaterialConstructor() {
        Nomenclature nomenclature = new Nomenclature("Nomenclature1");
        Unit unit = new Unit("Unit1");
        Warehouse warehouse = new Warehouse("Warehouse1");

        Material material = new Material("Wood", 12, true, nomenclature, unit, warehouse);

        assertAll("material",
                () -> assertEquals("Wood", material.getName()),
                () -> assertEquals(12, material.getAmount()),
                () -> assertEquals(true, material.getStatus()),
                () -> assertEquals(nomenclature, material.getNomenclature()),
                () -> assertEquals(unit, material.getUnit()),
                () -> assertEquals(warehouse, material.getWarehouse())
        );
    }

    @Test
    void testMaterialConstructor2() {
        Nomenclature nomenclature = new Nomenclature("Nomenclature1");
        Unit unit = new Unit("Unit1");

        Material material = new Material("Wood", true, nomenclature, unit);

        assertAll("material",
                () -> assertEquals("Wood", material.getName()),
                () -> assertEquals(true, material.getStatus()),
                () -> assertEquals(nomenclature, material.getNomenclature()),
                () -> assertEquals(unit, material.getUnit())
        );
    }

    @Test
    void testMaterialDefaultConstructor() {
        Nomenclature nomenclature = new Nomenclature("Nomenclature1");
        Unit unit = new Unit("Unit1");
        Warehouse warehouse = new Warehouse("Warehouse1");
        UUID uuid = UUID.randomUUID();

        Material material = new Material();

        material.setId(uuid);
        material.setName("Wood");
        material.setAmount(12);
        material.setStatus(true);
        material.setNomenclature(nomenclature);
        material.setUnit(unit);
        material.setWarehouse(warehouse);

        assertAll("material",
                () -> assertEquals(uuid, material.getId()),
                () -> assertEquals("Wood", material.getName()),
                () -> assertEquals(12, material.getAmount()),
                () -> assertEquals(true, material.getStatus()),
                () -> assertEquals(nomenclature, material.getNomenclature()),
                () -> assertEquals(unit, material.getUnit()),
                () -> assertEquals(warehouse, material.getWarehouse())
        );
    }
}
