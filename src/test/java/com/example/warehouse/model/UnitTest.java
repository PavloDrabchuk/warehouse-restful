package com.example.warehouse.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnitTest {

    @Test
    void testUnitConstructor() {
        Unit unit = new Unit("Unit1");

        assertAll("unit",
                () -> assertEquals("Unit1", unit.getName())
        );
    }

    @Test
    void testUnitDefaultConstructor() {
        Unit unit = new Unit();

        unit.setId(1L);
        unit.setName("Unit1");

        assertAll("unit",
                () -> assertEquals(1L, unit.getId()),
                () -> assertEquals("Unit1", unit.getName())
        );
    }
}
