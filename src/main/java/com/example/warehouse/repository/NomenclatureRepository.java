package com.example.warehouse.repository;

import com.example.warehouse.model.Nomenclature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NomenclatureRepository extends JpaRepository<Nomenclature, Long> {
}
