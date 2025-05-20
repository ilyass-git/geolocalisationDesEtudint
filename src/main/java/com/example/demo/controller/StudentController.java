package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // Récupérer tous les étudiants
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    // Récupérer un étudiant par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Récupérer un étudiant par son matricule
    @GetMapping("/matricule/{matricule}")
    public ResponseEntity<Student> getStudentByMatricule(@PathVariable String matricule) {
        Student student = studentService.getStudentByMatricule(matricule);
        return student != null ? ResponseEntity.ok(student) : ResponseEntity.notFound().build();
    }

    // Ajouter un nouvel étudiant
    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody Student student) {
        try {
            Student savedStudent = studentService.addStudent(student);
            return ResponseEntity.ok(savedStudent);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    // Mettre à jour un étudiant
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        try {
            Student updatedStudent = studentService.updateStudent(id, student);
            return ResponseEntity.ok(updatedStudent);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Mettre à jour la position d'un étudiant
    @PutMapping("/{id}/position")
    public ResponseEntity<Student> updateStudentPosition(
            @PathVariable Long id,
            @RequestParam Double latitude,
            @RequestParam Double longitude) {
        try {
            Student updatedStudent = studentService.updateStudentPosition(id, latitude, longitude);
            return ResponseEntity.ok(updatedStudent);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Supprimer un étudiant
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        try {
            studentService.deleteStudent(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Récupérer les étudiants par groupe
    @GetMapping("/groupe/{groupe}")
    public List<Student> getStudentsByGroupe(@PathVariable String groupe) {
        return studentService.getStudentsByGroupe(groupe);
    }

    // Récupérer les étudiants par année
    @GetMapping("/annee/{annee}")
    public List<Student> getStudentsByAnnee(@PathVariable Integer annee) {
        return studentService.getStudentsByAnnee(annee);
    }
} 