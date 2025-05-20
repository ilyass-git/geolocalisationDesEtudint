package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.repository.ZoneRepository;
import com.example.demo.repository.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ZoneService {
    
    @Autowired
    private ZoneRepository zoneRepository;
    
    @Autowired
    private PointRepository pointRepository;

    // Récupérer toutes les zones
    public List<Zone> getAllZones() {
        return zoneRepository.findAll();
    }

    // Récupérer une zone par son ID
    public Optional<Zone> getZoneById(Long id) {
        return zoneRepository.findById(id);
    }

    // Récupérer les zones par type
    public List<Zone> getZonesByType(ZoneType type) {
        return zoneRepository.findByType(type);
    }

    // Récupérer les zones par cible
    public List<Zone> getZonesByCible(String cible) {
        return zoneRepository.findByCible(cible);
    }

    // Ajouter une nouvelle zone
    @Transactional
    public Zone addZone(Zone zone) {
        return zoneRepository.save(zone);
    }

    // Mettre à jour une zone
    @Transactional
    public Zone updateZone(Long id, Zone zoneDetails) {
        Zone zone = zoneRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Zone non trouvée"));

        zone.setNom(zoneDetails.getNom());
        zone.setType(zoneDetails.getType());
        zone.setCible(zoneDetails.getCible());
        zone.setCouleur(zoneDetails.getCouleur());

        // Mettre à jour les points
        pointRepository.deleteByZone(zone);
        zone.setPoints(zoneDetails.getPoints());
        for (Point point : zone.getPoints()) {
            point.setZone(zone);
        }

        return zoneRepository.save(zone);
    }

    // Supprimer une zone
    @Transactional
    public void deleteZone(Long id) {
        Zone zone = zoneRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Zone non trouvée"));
        zoneRepository.delete(zone);
    }

    // Vérifier si un point est dans une zone
    public boolean isPointInZone(Double latitude, Double longitude, Zone zone) {
        // Implémentation de l'algorithme du point dans un polygone
        // (Ray casting algorithm)
        boolean inside = false;
        List<Point> points = zone.getPoints();
        
        for (int i = 0, j = points.size() - 1; i < points.size(); j = i++) {
            if ((points.get(i).getLatitude() > latitude) != (points.get(j).getLatitude() > latitude) &&
                (longitude < (points.get(j).getLongitude() - points.get(i).getLongitude()) * 
                (latitude - points.get(i).getLatitude()) / 
                (points.get(j).getLatitude() - points.get(i).getLatitude()) + 
                points.get(i).getLongitude())) {
                inside = !inside;
            }
        }
        
        return inside;
    }
} 