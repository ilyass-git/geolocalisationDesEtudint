package com.example.demo.repository;

import com.example.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // Rechercher par matricule
    Student findByMatricule(String matricule);
    
    // Rechercher par groupe
    List<Student> findByGroupe(String groupe);
    
    // Rechercher par année
    List<Student> findByAnnee(Integer annee);
} 