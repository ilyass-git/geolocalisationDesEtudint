package com.example.demo.config;

import com.example.demo.model.*;
import com.example.demo.repository.BeaconRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.ZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private BeaconRepository beaconRepository;

    @Autowired
    private ZoneRepository zoneRepository;

    @Override
    public void run(String... args) {
        // Initialiser des étudiants de test
        if (studentRepository.count() == 0) {
            Student student1 = new Student();
            student1.setNom("Dupont");
            student1.setPrenom("Jean");
            student1.setMatricule("ETU001");
            student1.setGroupe("G1");
            student1.setAnnee(2024);
            student1.setLatitude(48.8566);
            student1.setLongitude(2.3522);

            Student student2 = new Student();
            student2.setNom("Martin");
            student2.setPrenom("Sophie");
            student2.setMatricule("ETU002");
            student2.setGroupe("G1");
            student2.setAnnee(2024);
            student2.setLatitude(48.8567);
            student2.setLongitude(2.3523);

            studentRepository.saveAll(Arrays.asList(student1, student2));
        }

        // Initialiser des beacons de test
        if (beaconRepository.count() == 0) {
            Beacon beacon1 = new Beacon();
            beacon1.setId("BEACON001");
            beacon1.setLatitude(48.8566);
            beacon1.setLongitude(2.3522);

            Beacon beacon2 = new Beacon();
            beacon2.setId("BEACON002");
            beacon2.setLatitude(48.8567);
            beacon2.setLongitude(2.3523);

            beaconRepository.saveAll(Arrays.asList(beacon1, beacon2));
        }

        // Initialiser des zones de test
        if (zoneRepository.count() == 0) {
            Zone zone1 = new Zone();
            zone1.setNom("Zone G1");
            zone1.setType(ZoneType.CLASSE);
            zone1.setCible("G1");
            zone1.setCouleur("#FF0000");

            Point point1 = new Point();
            point1.setLatitude(48.8565);
            point1.setLongitude(2.3521);
            point1.setZone(zone1);

            Point point2 = new Point();
            point2.setLatitude(48.8568);
            point2.setLongitude(2.3524);
            point2.setZone(zone1);

            zone1.setPoints(Arrays.asList(point1, point2));
            zoneRepository.save(zone1);
        }
    }
} 