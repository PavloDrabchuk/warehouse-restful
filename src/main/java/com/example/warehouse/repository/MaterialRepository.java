package com.example.warehouse.repository;

import com.example.warehouse.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MaterialRepository extends JpaRepository<Material, UUID> {

    List<Material> findAllByWarehouseId(Long warehouseId);

    Optional<Material> findMaterialByIdAndWarehouseId(UUID id, Long warehouseId);

    void deleteMaterialByIdAndWarehouseId(UUID id, Long warehouseId);
}
