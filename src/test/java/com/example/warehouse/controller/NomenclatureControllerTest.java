package com.example.warehouse.controller;

import com.example.warehouse.service.NomenclatureService;
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
public class NomenclatureControllerTest {

    private MockMvc mockMvc;

    @Mock
    private NomenclatureService nomenclatureService;

    @InjectMocks
    private NomenclatureController nomenclatureController;

    private final String NOMENCLATURES_URL = "/api/v1/warehouses/nomenclature";

    @Mock
    private Validator validator;


    @BeforeEach
    void setUp() {
        nomenclatureController = new NomenclatureController(nomenclatureService);

        this.mockMvc = MockMvcBuilders
                .standaloneSetup(nomenclatureService)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setValidator(validator)
                .build();
    }

    @Test
    void testGetAllNomenclatures() throws Exception {
        mockMvc.perform(get(NOMENCLATURES_URL)).andExpect(status().isOk());

        verify(nomenclatureService).getAllNomenclatures();
        verifyNoMoreInteractions(nomenclatureService);
    }

    @Test
    void testGetNomenclatureByIdWithIncorrectId() throws Exception {
        mockMvc.perform(get("/api/v1/warehouses/nomenclature/{id}", "1"))
                .andExpect(status().isNotFound());

        verify(nomenclatureService).getNomenclatureById(any(Long.class));
    }

    @Test
    void createNomenclature() throws Exception {
        String content = "{\n" +
                "    \"name\":\"Nomenclature1\"\n" +
                "}";

        mockMvc.perform(post(NOMENCLATURES_URL)
                        .contentType("application/json")
                        .content(content))
                .andExpect(status().isCreated());
    }

    @Test
    void testUpdateNomenclatureWithIncorrectId() throws Exception {
        String content = "{\n" +
                "        \"id\": 1,\n" +
                "        \"name\": \"Nomenclature1_upd\"\n" +
                "}";

        mockMvc.perform(put(NOMENCLATURES_URL)
                        .contentType("application/json")
                        .content(content))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteNomenclatureByIdWithIncorrectId() throws Exception {
        mockMvc.perform(delete("/api/v1/warehouses/nomenclature/{id}", "1"))
                .andExpect(status().isBadRequest());
    }

}
