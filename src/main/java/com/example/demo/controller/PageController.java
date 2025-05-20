package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.model.Beacon;
import com.example.demo.model.Zone;
import com.example.demo.service.StudentService;
import com.example.demo.service.BeaconService;
import com.example.demo.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PageController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private BeaconService beaconService;

    @Autowired
    private ZoneService zoneService;

    @GetMapping("/")
    public String home(Model model) {
        // Récupérer toutes les données nécessaires pour la page d'administration
        List<Student> students = studentService.getAllStudents();
        List<Beacon> beacons = beaconService.getAllBeacons();
        List<Zone> zones = zoneService.getAllZones();

        // Ajouter les données au modèle
        model.addAttribute("students", students);
        model.addAttribute("beacons", beacons);
        model.addAttribute("zones", zones);

        return "page1";
    }

    @GetMapping("/simulation")
    public String simulation(Model model) {
        // Récupérer toutes les données nécessaires pour la page de simulation
        List<Student> students = studentService.getAllStudents();
        List<Beacon> beacons = beaconService.getAllBeacons();
        List<Zone> zones = zoneService.getAllZones();

        // Ajouter les données au modèle
        model.addAttribute("students", students);
        model.addAttribute("beacons", beacons);
        model.addAttribute("zones", zones);

        return "page2";
    }
} 