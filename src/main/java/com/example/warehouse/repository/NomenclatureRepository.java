package com.example.warehouse.repository;

import com.example.warehouse.model.Nomenclature;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NomenclatureRepository extends CrudRepository<Nomenclature, Long> {
}
