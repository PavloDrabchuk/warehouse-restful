package com.example.warehouse.controller;

import com.example.warehouse.model.Nomenclature;
import com.example.warehouse.service.NomenclatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/warehouses/nomenclature")
public class NomenclatureController {

    final private NomenclatureService nomenclatureService;

    @Autowired
    public NomenclatureController(NomenclatureService nomenclatureService) {
        this.nomenclatureService = nomenclatureService;
    }

    @GetMapping
    public List<Nomenclature> getAllNomenclatures() {
        return nomenclatureService.getAllNomenclature();
    }

    @GetMapping(path = "{id}")
    public Optional<Nomenclature> getNomenclatureById(@PathVariable("id") Long id) {
        return nomenclatureService.getNomenclatureById(id);
    }

    @PostMapping
    public Nomenclature createNomenclature(@RequestBody Nomenclature nomenclature) {
        return nomenclatureService.createNomenclature(nomenclature);
    }

    @PutMapping
    public Nomenclature updateNomenclature(@RequestBody Nomenclature nomenclature) {
        return nomenclatureService.updateNomenclatureById(nomenclature.getId(), nomenclature);
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteNomenclatureById(@PathVariable("id") Long id) {
        nomenclatureService.deleteNomenclatureById(id);
    }
}
