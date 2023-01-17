package com.example.warehouse.controller;

import com.example.warehouse.service.WarehouseService;
import com.example.warehouse.service.impl.WarehouseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import org.springframework.validation.Validator;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class WarehouseControllerTest {

    private MockMvc mockMvc;

    @Mock
    private WarehouseService warehouseService;

    @InjectMocks
    private WarehouseController warehouseController;

    private final String WAREHOUSES_URL = "/api/v1/warehouses";

    @Mock
    private Validator validator;

    @BeforeEach
    void setUp() {
        warehouseController = new WarehouseController(warehouseService);

        this.mockMvc = MockMvcBuilders
                .standaloneSetup(warehouseController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setValidator(validator)
                .build();
    }

    @Test
    void testGetAllWarehouses() throws Exception {
        mockMvc.perform(get(WAREHOUSES_URL)).andExpect(status().isOk());

        verify(warehouseService).getAllWarehouses();
        verifyNoMoreInteractions(warehouseService);
    }

    @Test
    void testGetWarehouseByIdWithIncorrectId() throws Exception {
        mockMvc.perform(get("/api/v1/warehouses/{id}", "1"))
                .andExpect(status().isNotFound());

        verify(warehouseService).getWarehouseById(any(Long.class));
    }

    @Test
    void createWarehouse() throws Exception {
        String content = "{\n" +
                "    \"name\":\"Warehouse1\"\n" +
                "}";

        mockMvc.perform(post(WAREHOUSES_URL)
                        .contentType("application/json")
                        .content(content))
                .andExpect(status().isCreated());
    }

    @Test
    void testUpdateWarehouseWithIncorrectId() throws Exception {
        String content = "{\n" +
                "        \"id\": 1,\n" +
                "        \"name\": \"Warehouse1_upd\"\n" +
                "}";

        mockMvc.perform(put(WAREHOUSES_URL)
                        .contentType("application/json")
                        .content(content))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteWarehouseByIdWithIncorrectId() throws Exception {
        mockMvc.perform(delete("/api/v1/warehouses/{id}", "1"))
                .andExpect(status().isBadRequest());
    }
}
