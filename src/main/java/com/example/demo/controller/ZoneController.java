package com.example.demo.controller;

import com.example.demo.model.Zone;
import com.example.demo.model.ZoneType;
import com.example.demo.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/zones")
@CrossOrigin(origins = "*")
public class ZoneController {
    private static final Logger logger = LoggerFactory.getLogger(ZoneController.class);

    @Autowired
    private ZoneService zoneService;

    // Récupérer toutes les zones
    @GetMapping
    public List<Zone> getAllZones() {
        return zoneService.getAllZones();
    }

    // Récupérer une zone par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Zone> getZoneById(@PathVariable Long id) {
        return zoneService.getZoneById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Récupérer les zones par type
    @GetMapping("/type/{type}")
    public List<Zone> getZonesByType(@PathVariable ZoneType type) {
        return zoneService.getZonesByType(type);
    }

    // Récupérer les zones par cible
    @GetMapping("/cible/{cible}")
    public List<Zone> getZonesByCible(@PathVariable String cible) {
        return zoneService.getZonesByCible(cible);
    }

    // Ajouter une nouvelle zone
    @PostMapping
    public ResponseEntity<?> addZone(@RequestBody Zone zone) {
        logger.info("Tentative de création d'une nouvelle zone: {}", zone);
        try {
            // Validation des champs
            if (zone.getNom() == null || zone.getNom().trim().isEmpty()) {
                logger.error("Erreur: Nom de zone manquant");
                return ResponseEntity.badRequest().body("Le nom de la zone est requis");
            }
            if (zone.getType() == null) {
                logger.error("Erreur: Type de zone manquant");
                return ResponseEntity.badRequest().body("Le type de la zone est requis");
            }
            if (zone.getCible() == null || zone.getCible().isEmpty()) {
                logger.error("Erreur: Aucune cible sélectionnée");
                return ResponseEntity.badRequest().body("Au moins une cible doit être sélectionnée");
            }
            if (zone.getPoints() == null || zone.getPoints().isEmpty()) {
                logger.error("Erreur: Aucun point défini pour la zone");
                return ResponseEntity.badRequest().body("La zone doit contenir au moins 3 points");
            }
            if (zone.getPoints().size() < 3) {
                logger.error("Erreur: Nombre de points insuffisant: {}", zone.getPoints().size());
                return ResponseEntity.badRequest().body("La zone doit contenir au moins 3 points");
            }

            // Vérification des cibles
            logger.info("Cibles reçues: {}", zone.getCible());
            if (zone.getCible().stream().anyMatch(c -> c == null || c.trim().isEmpty())) {
                logger.error("Erreur: Certaines cibles sont vides ou nulles");
                return ResponseEntity.badRequest().body("Toutes les cibles doivent être valides");
            }

            logger.info("Validation des données réussie, tentative de création de la zone");
            Zone newZone = zoneService.addZone(zone);
            logger.info("Zone créée avec succès: {}", newZone);
            return ResponseEntity.ok(newZone);
        } catch (RuntimeException e) {
            logger.error("Erreur lors de la création de la zone: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body("Erreur lors de la création de la zone: " + e.getMessage());
        }
    }

    // Mettre à jour une zone
    @PutMapping("/{id}")
    public ResponseEntity<Zone> updateZone(@PathVariable Long id, @RequestBody Zone zone) {
        try {
            Zone updatedZone = zoneService.updateZone(id, zone);
            return ResponseEntity.ok(updatedZone);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Supprimer une zone
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteZone(@PathVariable Long id) {
        try {
            zoneService.deleteZone(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Vérifier si un point est dans une zone
    @GetMapping("/{id}/contains")
    public ResponseEntity<Boolean> isPointInZone(
            @PathVariable Long id,
            @RequestParam Double latitude,
            @RequestParam Double longitude) {
        return zoneService.getZoneById(id)
                .map(zone -> ResponseEntity.ok(zoneService.isPointInZone(latitude, longitude, zone)))
                .orElse(ResponseEntity.notFound().build());
    }
} 