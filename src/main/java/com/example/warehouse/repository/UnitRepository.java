package com.example.warehouse.repository;

import com.example.warehouse.model.Unit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitRepository extends CrudRepository<Unit, Long> {
}
