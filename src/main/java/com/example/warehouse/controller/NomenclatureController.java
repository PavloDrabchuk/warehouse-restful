package com.example.warehouse.controller;

import com.example.warehouse.dto.nomenclature.NomenclatureDTO;
import com.example.warehouse.service.NomenclatureService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/warehouses/nomenclature")
public class NomenclatureController {

    final private NomenclatureService nomenclatureService;

    @Autowired
    public NomenclatureController(NomenclatureService nomenclatureService) {
        this.nomenclatureService = nomenclatureService;
    }

    @GetMapping
    public List<NomenclatureDTO> getAllNomenclatures() {
        return nomenclatureService.getAllNomenclatures();
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<NomenclatureDTO> getNomenclatureById(@PathVariable("id") Long id) {
        NomenclatureDTO nomenclatureDTO = nomenclatureService.getNomenclatureById(id);

        return ResponseEntity
                .status((nomenclatureDTO != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                .body(nomenclatureDTO);
    }

    @PostMapping
    public ResponseEntity<NomenclatureDTO> createNomenclature(@Valid @RequestBody NomenclatureDTO nomenclatureDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nomenclatureService.createNomenclature(nomenclatureDTO));
    }

    @PutMapping
    public ResponseEntity<NomenclatureDTO> updateNomenclature(@Valid @RequestBody NomenclatureDTO newNomenclatureDTO) {
        NomenclatureDTO nomenclatureDTO = nomenclatureService.updateNomenclatureById(newNomenclatureDTO.getId(), newNomenclatureDTO);

        return ResponseEntity
                .status((nomenclatureDTO != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                .body(nomenclatureDTO);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Object> deleteNomenclatureById(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(nomenclatureService.deleteNomenclatureById(id)
                        ? HttpStatus.OK
                        : HttpStatus.BAD_REQUEST)
                .build();
    }
}
