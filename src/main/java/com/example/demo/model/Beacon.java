package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Beacon {
    @Id
    private String id; // L'ID du beacon est fourni par l'utilisateur

    private Double latitude;
    private Double longitude;
} 