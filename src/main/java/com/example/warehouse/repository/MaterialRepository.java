package com.example.warehouse.repository;

import com.example.warehouse.model.Material;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MaterialRepository extends CrudRepository<Material, UUID> {
}
