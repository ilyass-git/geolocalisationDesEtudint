package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Zone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    @NotNull(message = "Le type est obligatoire")
    @Enumerated(EnumType.STRING)
    private ZoneType type;

    @ElementCollection
    @CollectionTable(name = "zone_cibles", joinColumns = @JoinColumn(name = "zone_id"))
    @Column(name = "cible")
    private List<String> cible = new ArrayList<>();

    @OneToMany(mappedBy = "zone", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Point> points = new ArrayList<>();

    @NotBlank(message = "La couleur est obligatoire")
    private String couleur;

    @Override
    public String toString() {
        return "Zone{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", type=" + type +
                ", cible=" + cible +
                ", couleur='" + couleur + '\'' +
                '}';
    }
} 