package com.example.demo.repository;

import com.example.demo.model.Point;
import com.example.demo.model.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface PointRepository extends JpaRepository<Point, Long> {
    // Rechercher les points d'une zone
    List<Point> findByZone(Zone zone);
    
    @Modifying
    @Transactional
    @Query("DELETE FROM Point p WHERE p.zone = ?1")
    void deleteByZone(Zone zone);
} 