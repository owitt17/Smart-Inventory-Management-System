package com.example.inventory_backend.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ItemRepository extends JpaRepository<Item, Long> {

    java.util.List<Item> findByNameContainingIgnoreCase(String query);

    @Query("SELECT COUNT(i) FROM Item i")
    long countAllItems();

    @Query("SELECT COALESCE(SUM(i.quantity), 0) FROM Item i")
    long sumAllQuantities();

    @Query("SELECT COUNT(i) FROM Item i WHERE i.quantity = 0")
    long countOutOfStock();

    @Query("SELECT COUNT(i) FROM Item i WHERE i.quantity < :threshold")
    long countLowStock(@Param("threshold") int threshold);
}
