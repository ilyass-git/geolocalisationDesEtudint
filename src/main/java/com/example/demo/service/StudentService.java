package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@Service
@Validated
public class StudentService {
    
    @Autowired
    private StudentRepository studentRepository;

    // Récupérer tous les étudiants
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Récupérer un étudiant par son ID
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    // Récupérer un étudiant par son matricule
    public Student getStudentByMatricule(String matricule) {
        return studentRepository.findByMatricule(matricule);
    }

    // Ajouter un nouvel étudiant
    @Transactional
    public Student addStudent(@Valid Student student) {
        // Vérifier si le matricule existe déjà
        if (studentRepository.findByMatricule(student.getMatricule()) != null) {
            throw new RuntimeException("Un étudiant avec ce matricule existe déjà");
        }
        
        // Vérifier que l'année et le groupe sont renseignés
        if (student.getAnnee() == null) {
            throw new RuntimeException("L'année est obligatoire");
        }
        if (student.getGroupe() == null || student.getGroupe().trim().isEmpty()) {
            throw new RuntimeException("Le groupe est obligatoire");
        }
        
        return studentRepository.save(student);
    }

    // Mettre à jour un étudiant
    @Transactional
    public Student updateStudent(Long id, Student studentDetails) {
        Student student = studentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Étudiant non trouvé"));

        student.setNom(studentDetails.getNom());
        student.setPrenom(studentDetails.getPrenom());
        student.setMatricule(studentDetails.getMatricule());
        student.setGroupe(studentDetails.getGroupe());
        student.setAnnee(studentDetails.getAnnee());

        return studentRepository.save(student);
    }

    // Mettre à jour la position d'un étudiant
    @Transactional
    public Student updateStudentPosition(Long id, Double latitude, Double longitude) {
        Student student = studentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Étudiant non trouvé"));

        student.setLatitude(latitude);
        student.setLongitude(longitude);

        return studentRepository.save(student);
    }

    // Supprimer un étudiant
    @Transactional
    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Étudiant non trouvé"));
        studentRepository.delete(student);
    }

    // Récupérer les étudiants par groupe
    public List<Student> getStudentsByGroupe(String groupe) {
        return studentRepository.findByGroupe(groupe);
    }

    // Récupérer les étudiants par année
    public List<Student> getStudentsByAnnee(Integer annee) {
        return studentRepository.findByAnnee(annee);
    }
} 