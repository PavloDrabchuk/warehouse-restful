package com.example.warehouse.controller;

import com.example.warehouse.mapper.UnitMapper;
import com.example.warehouse.service.UnitService;
import com.example.warehouse.service.WarehouseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit.jupiter.SpringExtension;
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
public class UnitControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UnitService unitService;

    @InjectMocks
    private UnitController unitController;

    private final String UNITS_URL = "/api/v1/warehouses/unit";

    @Mock
    private Validator validator;


    @BeforeEach
    void setUp() {
        unitController = new UnitController(unitService);

        this.mockMvc = MockMvcBuilders
                .standaloneSetup(unitController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setValidator(validator)
                .build();
    }

    @Test
    void testGetAllUnits() throws Exception {
        mockMvc.perform(get(UNITS_URL)).andExpect(status().isOk());

        verify(unitService).getAllUnits();
        verifyNoMoreInteractions(unitService);
    }

    @Test
    void testGetUnitByIdWithIncorrectId() throws Exception {
        mockMvc.perform(get("/api/v1/warehouses/unit/{id}", "1"))
                .andExpect(status().isNotFound());

        verify(unitService).getUnitById(any(Long.class));
    }

    @Test
    void createUnit() throws Exception {
        String content = "{\n" +
                "    \"name\":\"Unit1\"\n" +
                "}";

        mockMvc.perform(post(UNITS_URL)
                        .contentType("application/json")
                        .content(content))
                .andExpect(status().isCreated());
    }

    @Test
    void testUpdateUnitWithIncorrectId() throws Exception {
        String content = "{\n" +
                "        \"id\": 1,\n" +
                "        \"name\": \"Unit1_upd\"\n" +
                "}";

        mockMvc.perform(put(UNITS_URL)
                        .contentType("application/json")
                        .content(content))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteUnitByIdWithIncorrectId() throws Exception {
        mockMvc.perform(delete("/api/v1/warehouses/unit/{id}", "1"))
                .andExpect(status().isBadRequest());
    }

}
