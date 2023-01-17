package com.example.warehouse.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NomenclatureTest {

    @Test
    void testNomenclatureConstructor() {
        Nomenclature nomenclature = new Nomenclature("Nomenclature1");

        assertAll("nomenclature",
                () -> assertEquals("Nomenclature1", nomenclature.getName())
        );
    }

    @Test
    void testNomenclatureDefaultConstructor() {
        Nomenclature nomenclature = new Nomenclature();

        nomenclature.setId(1L);
        nomenclature.setName("Nomenclature1");

        assertAll("nomenclature",
                () -> assertEquals(1L, nomenclature.getId()),
                () -> assertEquals("Nomenclature1", nomenclature.getName())
        );
    }
}
