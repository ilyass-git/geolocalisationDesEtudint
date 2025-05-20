package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.repository.ZoneRepository;
import com.example.demo.repository.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Optional;

@Service
public class ZoneService {
    private static final Logger logger = LoggerFactory.getLogger(ZoneService.class);
    
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
        logger.info("Début de la création de la zone dans le service");
        try {
            // Validation des cibles
            if (zone.getCible() == null || zone.getCible().isEmpty()) {
                logger.error("Erreur: Liste de cibles vide");
                throw new RuntimeException("La zone doit avoir au moins une cible");
            }
            logger.info("Cibles reçues: {}", zone.getCible());

            // Validation des points
            if (zone.getPoints() == null || zone.getPoints().isEmpty()) {
                logger.error("Erreur: Liste de points vide");
                throw new RuntimeException("La zone doit contenir au moins 3 points");
            }

            logger.info("Nombre de points dans la zone: {}", zone.getPoints().size());

            // Validation des coordonnées
            for (int i = 0; i < zone.getPoints().size(); i++) {
                Point point = zone.getPoints().get(i);
                logger.info("Validation du point {}: lat={}, lng={}", i, point.getLatitude(), point.getLongitude());
                
                if (point.getLatitude() == null || point.getLongitude() == null) {
                    logger.error("Erreur: Coordonnées nulles pour le point {}", i);
                    throw new RuntimeException("Tous les points doivent avoir des coordonnées valides");
                }
                if (point.getLatitude() < -90 || point.getLatitude() > 90) {
                    logger.error("Erreur: Latitude invalide pour le point {}: {}", i, point.getLatitude());
                    throw new RuntimeException("La latitude doit être comprise entre -90 et 90");
                }
                if (point.getLongitude() < -180 || point.getLongitude() > 180) {
                    logger.error("Erreur: Longitude invalide pour le point {}: {}", i, point.getLongitude());
                    throw new RuntimeException("La longitude doit être comprise entre -180 et 180");
                }
            }

            logger.info("Tentative de sauvegarde de la zone");
            // Sauvegarder la zone
            Zone savedZone = zoneRepository.save(zone);
            logger.info("Zone sauvegardée avec l'ID: {} et les cibles: {}", savedZone.getId(), savedZone.getCible());

            // Sauvegarder les points
            logger.info("Sauvegarde des points de la zone");
            for (Point point : zone.getPoints()) {
                point.setZone(savedZone);
                Point savedPoint = pointRepository.save(point);
                logger.info("Point sauvegardé: lat={}, lng={}", savedPoint.getLatitude(), savedPoint.getLongitude());
            }

            logger.info("Création de la zone terminée avec succès");
            return savedZone;
        } catch (Exception e) {
            logger.error("Erreur lors de la création de la zone: {}", e.getMessage(), e);
            throw new RuntimeException("Erreur lors de la création de la zone: " + e.getMessage());
        }
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