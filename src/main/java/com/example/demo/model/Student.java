package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    @NotBlank(message = "Le prénom est obligatoire")
    private String prenom;
    
    @NotBlank(message = "Le matricule est obligatoire")
    @Column(unique = true)
    private String matricule;
    
    private Double latitude;
    private Double longitude;
    
    @NotBlank(message = "Le groupe est obligatoire")
    private String groupe;

    @NotNull(message = "L'année est obligatoire")
    private Integer annee;
} 