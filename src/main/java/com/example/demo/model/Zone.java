package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Zone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    @NotNull(message = "Le type est obligatoire")
    @Enumerated(EnumType.STRING)
    private ZoneType type;

    @NotBlank(message = "La cible est obligatoire")
    private String cible; // ID de la cible (classe, étudiant ou année)

    @OneToMany(mappedBy = "zone", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Point> points = new ArrayList<>();

    @NotBlank(message = "La couleur est obligatoire")
    private String couleur;

    @Override
    public String toString() {
        return "Zone{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", type=" + type +
                ", cible='" + cible + '\'' +
                ", couleur='" + couleur + '\'' +
                '}';
    }
} 