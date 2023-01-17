package com.example.warehouse.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WarehouseTest {

    @Test
    void testWarehouseConstructor() {
        Warehouse warehouse = new Warehouse("Warehouse");

        assertAll("warehouse",
                () -> assertEquals("Warehouse", warehouse.getName())
        );
    }

    @Test
    void testWarehouseDefaultConstructor() {
        List<Material> materials = new ArrayList<>();
        materials.add(new Material());
        materials.add(new Material());

        Warehouse warehouse = new Warehouse();

        warehouse.setId(1L);
        warehouse.setName("Warehouse");
        warehouse.setMaterials(materials);


        assertAll("warehouse",
                () -> assertEquals(1L, warehouse.getId()),
                () -> assertEquals("Warehouse", warehouse.getName()),
                () -> assertEquals(materials, warehouse.getMaterials())
        );
    }
}
