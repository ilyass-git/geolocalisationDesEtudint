package com.example.demo.repository;

import com.example.demo.model.Beacon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
@Repository
public interface BeaconRepository extends JpaRepository<Beacon, String> {
    // L'ID est de type String car il est fourni par l'utilisateur
} 