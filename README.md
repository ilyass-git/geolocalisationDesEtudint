# Système de Gestion et Suivi des Zones d'Étudiants

## Description du Projet
Ce projet est un système de gestion et de suivi des zones d'étudiants, permettant aux administrateurs de créer et gérer des zones d'accès, de suivre les déplacements des étudiants en temps réel, et de gérer les accès par type (étudiant, classe, groupe).

## Problématiques Résolues

### 1. Gestion Manuelle Inefficace des Zones d'Accès
- **Problème** : Gestion manuelle chronophage et sujette aux erreurs
- **Solution** : Interface cartographique interactive pour la création et gestion des zones

### 2. Difficulté de Suivi des Présences en Temps Réel
- **Problème** : Impossibilité de suivre les présences en temps réel
- **Solution** : Visualisation en temps réel des positions des étudiants sur la carte

### 3. Manque de Flexibilité dans la Gestion des Groupes
- **Problème** : Difficulté à gérer les déplacements de groupes
- **Solution** : Système de zones spécifiques pour groupes et classes

### 4. Absence de Système de Contrôle d'Accès Automatisé
- **Problème** : Pas de contrôle efficace des accès
- **Solution** : Règles d'accès précises et automatiques

### 5. Gestion des Situations d'Urgence
- **Problème** : Difficulté à localiser les étudiants en cas d'urgence
- **Solution** : Système de localisation rapide et gestion des zones de sécurité

## Technologies Utilisées
- **Backend** : Spring Boot
- **Frontend** : HTML, CSS, JavaScript
- **Base de données** : MySQL
- **Cartographie** : Leaflet.js
- **Autres** : Thymeleaf, Bootstrap

## Fonctionnalités Principales
1. Création et gestion de zones interactives
2. Suivi en temps réel des étudiants
3. Gestion des accès par type (étudiant, classe, groupe)
4. Interface cartographique intuitive
5. Système de contrôle d'accès automatisé

## Installation et Configuration

### Prérequis
- Java 17 ou supérieur
- Maven
- MySQL
- Navigateur web moderne

### Installation
1. Cloner le repository
```bash
git clone [URL_DU_REPO]
```

2. Configurer la base de données
- Créer une base de données MySQL
- Configurer les paramètres de connexion dans `application.properties`

3. Compiler et exécuter
```bash
mvn clean install
mvn spring-boot:run
```

## Structure du Projet
```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── example/
│   │           └── demo/
│   │               ├── controller/
│   │               ├── model/
│   │               ├── repository/
│   │               ├── service/
│   │               └── DemoApplication.java
│   └── resources/
│       ├── static/
│       ├── templates/
│       └── application.properties
└── test/
```

## Utilisation
1. Accéder à l'application via `http://localhost:8080`
2. Se connecter avec les identifiants administrateur
3. Créer des zones sur la carte
4. Gérer les accès des étudiants

## Perspectives d'Amélioration
1. Intégration de l'IA pour la prédiction des mouvements
2. Développement d'une application mobile
3. Système de notifications en temps réel
4. Intégration avec d'autres systèmes de gestion
5. Amélioration de la sécurité et de la performance

## Contribution
Les contributions sont les bienvenues. Pour contribuer :
1. Fork le projet
2. Créer une branche pour votre fonctionnalité
3. Commiter vos changements
4. Pousser vers la branche
5. Ouvrir une Pull Request

## Licence
Ce projet est sous licence [VOTRE_LICENCE]

## Contact
- Nom : [VOTRE_NOM]
- Email : [VOTRE_EMAIL]
- Établissement : [NOM_ETABLISSEMENT] 