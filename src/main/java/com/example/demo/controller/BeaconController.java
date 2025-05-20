package com.example.demo.controller;

import com.example.demo.model.Beacon;
import com.example.demo.service.BeaconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/beacons")
@CrossOrigin(origins = "*")
public class BeaconController {

    @Autowired
    private BeaconService beaconService;

    // Récupérer tous les beacons
    @GetMapping
    public List<Beacon> getAllBeacons() {
        return beaconService.getAllBeacons();
    }

    // Récupérer un beacon par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Beacon> getBeaconById(@PathVariable String id) {
        return beaconService.getBeaconById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Ajouter un nouveau beacon
    @PostMapping
    public ResponseEntity<Beacon> addBeacon(@RequestBody Beacon beacon) {
        try {
            Beacon newBeacon = beaconService.addBeacon(beacon);
            return ResponseEntity.ok(newBeacon);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Mettre à jour un beacon
    @PutMapping("/{id}")
    public ResponseEntity<Beacon> updateBeacon(@PathVariable String id, @RequestBody Beacon beacon) {
        try {
            Beacon updatedBeacon = beaconService.updateBeacon(id, beacon);
            return ResponseEntity.ok(updatedBeacon);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Mettre à jour la position d'un beacon
    @PutMapping("/{id}/position")
    public ResponseEntity<Beacon> updateBeaconPosition(
            @PathVariable String id,
            @RequestParam Double latitude,
            @RequestParam Double longitude) {
        try {
            Beacon updatedBeacon = beaconService.updateBeaconPosition(id, latitude, longitude);
            return ResponseEntity.ok(updatedBeacon);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Supprimer un beacon
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBeacon(@PathVariable String id) {
        try {
            beaconService.deleteBeacon(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
} 