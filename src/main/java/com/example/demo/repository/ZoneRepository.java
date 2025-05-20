package com.example.demo.repository;

import com.example.demo.model.Zone;
import com.example.demo.model.ZoneType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ZoneRepository extends JpaRepository<Zone, Long> {
    // Rechercher les zones par type
    List<Zone> findByType(ZoneType type);
    
    // Rechercher les zones par cible
    List<Zone> findByCible(String cible);
    
    // Rechercher les zones par type et cible
    List<Zone> findByTypeAndCible(ZoneType type, String cible);
} 