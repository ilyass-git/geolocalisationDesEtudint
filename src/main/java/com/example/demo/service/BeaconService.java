package com.example.demo.service;

import com.example.demo.model.Beacon;
import com.example.demo.repository.BeaconRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BeaconService {
    
    @Autowired
    private BeaconRepository beaconRepository;

    // Récupérer tous les beacons
    public List<Beacon> getAllBeacons() {
        return beaconRepository.findAll();
    }

    // Récupérer un beacon par son ID
    public Optional<Beacon> getBeaconById(String id) {
        return beaconRepository.findById(id);
    }

    // Ajouter un nouveau beacon
    public Beacon addBeacon(Beacon beacon) {
        // Vérifier si le beacon existe déjà
        if (beaconRepository.existsById(beacon.getId())) {
            throw new RuntimeException("Un beacon avec cet ID existe déjà");
        }
        return beaconRepository.save(beacon);
    }

    // Mettre à jour un beacon
    public Beacon updateBeacon(String id, Beacon beaconDetails) {
        Beacon beacon = beaconRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Beacon non trouvé"));

        beacon.setLatitude(beaconDetails.getLatitude());
        beacon.setLongitude(beaconDetails.getLongitude());

        return beaconRepository.save(beacon);
    }

    // Mettre à jour la position d'un beacon
    public Beacon updateBeaconPosition(String id, Double latitude, Double longitude) {
        Beacon beacon = beaconRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Beacon non trouvé"));

        beacon.setLatitude(latitude);
        beacon.setLongitude(longitude);

        return beaconRepository.save(beacon);
    }

    // Supprimer un beacon
    public void deleteBeacon(String id) {
        Beacon beacon = beaconRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Beacon non trouvé"));
        beaconRepository.delete(beacon);
    }
} 