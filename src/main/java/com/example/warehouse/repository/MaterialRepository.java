package com.example.warehouse.repository;

import com.example.warehouse.model.Material;
import com.example.warehouse.model.Warehouse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MaterialRepository extends CrudRepository<Material, UUID> {
    List<Material> findAllByWarehouse(Warehouse warehouse);
}
