package com.example.demo.controller;

import com.example.demo.model.Zone;
import com.example.demo.model.ZoneType;
import com.example.demo.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/zones")
@CrossOrigin(origins = "*")
public class ZoneController {

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
    public ResponseEntity<Zone> addZone(@RequestBody Zone zone) {
        try {
            Zone newZone = zoneService.addZone(zone);
            return ResponseEntity.ok(newZone);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
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