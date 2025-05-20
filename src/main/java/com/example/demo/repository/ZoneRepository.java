package com.example.demo.repository;

import com.example.demo.model.Zone;
import com.example.demo.model.ZoneType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ZoneRepository extends JpaRepository<Zone, Long> {
    // Rechercher les zones par type
    List<Zone> findByType(ZoneType type);
    
    // Rechercher les zones par cible
    @Query("SELECT z FROM Zone z JOIN z.cible c WHERE c = :cible")
    List<Zone> findByCible(@Param("cible") String cible);
    
    // Rechercher les zones par type et cible
    @Query("SELECT z FROM Zone z JOIN z.cible c WHERE z.type = :type AND c = :cible")
    List<Zone> findByTypeAndCible(@Param("type") ZoneType type, @Param("cible") String cible);
} 