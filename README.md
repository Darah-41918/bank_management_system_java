# Système de Gestion Bancaire - Devoir Final Java

## Auteur
Projet réalisé dans le cadre du Devoir Final de Java - 3e Année CTPEA 2025 par Abdarare HÉRARD.

## Description du Projet
Ce projet est un système complet de gestion bancaire développé en Java dans le cadre du devoir final de 3e année CTPEA 2025. Il permet de gérer une banque avec ses clients, leurs comptes et toutes les transactions effectuées.
Le système offre une interface en ligne de commande pour effectuer toutes les opérations bancaires courantes : création de clients et de comptes, dépôts, retraits, virements, consultation de soldes et historique des transactions.

## Encodage
Les fichiers utilisent l'encodage UTF-8 pour supporter les caractères accentués (français).

## Concepts Java Utilisés
- POO : Classes, Héritage, Polymorphisme, Abstraction
- Collections : ArrayList, List
- I/O : BufferedReader, BufferedWriter, FileReader, FileWriter
- Exception Handling : try-catch, IOException
- String Manipulation : split, parsing
- Date/Time API : LocalDate
- Scanner : Lecture des entrées utilisateur

## Fonctionnalités Principales

### Gestion des Clients
- Ajouter un nouveau client à la banque
- Lister tous les clients enregistrés
- Rechercher un client par son numéro

### Gestion des Comptes
- Créer un compte pour un client (Courant ou Épargne)
- Lister tous les comptes de tous les clients
- Rechercher un compte par son numéro
- Deux types de comptes :
    - Compte Courant : avec découvert autorisé
    - Compte Épargne : avec taux d'intérêt

### Opérations Bancaires
- Dépôt : ajouter de l'argent sur un compte
- Retrait : retirer de l'argent (avec vérification du solde/découvert)
- Virement : transférer de l'argent entre deux comptes
- Consultation : afficher le solde d'un compte
- Historique : afficher toutes les transactions d'un compte
- Intérêts : appliquer les intérêts sur les comptes épargne

### Persistance des Données
- Sauvegarde automatique dans des fichiers CSV
- Chargement automatique au démarrage
- Trois fichiers de données : clients, comptes, transactions

##  Architecture du Projet

### Structure des Classes
- ├── Main.java                 # Point d'entrée avec menu interactif
- ├── Banque.java              # Classe principale de la banque
- ├── Client.java              # Représente un client
- ├── Compte.java              # Classe abstraite pour les comptes
- ├── CompteCourant.java       # Compte avec découvert autorisé
- ├── CompteEpargne.java       # Compte avec taux d'intérêt
- ├── Transaction.java         # Représente une transaction
- └── CSVHandler.java          # Gestion des fichiers CSV

### Diagramme de Classes (Hiérarchie)
Banque
-   └── clients: List<Client>

Client
-   ├── numero: String
-   ├── nom: String
-   └── comptes: List<Compte>

Compte (abstract)
-   ├── numero: String
-   ├── proprietaire: Client
-   ├── solde: double
-   └── transactions: List<Transaction>
-        ├── CompteCourant
-             └── decouvertAutorise:double
-        └── CompteEpargne
-             └── tauxInteret: double

Transaction
-   ├── type: String
-   ├── montant: double
-   └── date: LocalDate

##  Fichiers de Données
Le système utilise trois fichiers CSV pour la persistance des données :
1. clients.csv
Format : numeroClient;nomClient
2. comptes.csv
Format : numeroCompte;type;numeroClient;solde;extra
-         Pour Épargne : extra = taux d'intérêt (ex: 0.05)
-         Pour Courant : extra = découvert autorisé (ex: 500.0)
3. transactions.csv
Format : numeroCompte;typeTransaction;montant;date

##  Installation et Exécution

### Prérequis
- Java JDK 11 ou supérieur
- Système d'exploitation : Windows

#### Compilation
javac *.java

### Exécution
java Main
- Les fichiers CSV doivent être présents dans le même répertoire que les fichiers .class. S'ils n'existent pas, ils seront créés automatiquement à la fermeture du programme.

## Guide d'Utilisation

### Menu Principal
Au lancement, le programme affiche le menu suivant :
Bienvenue dans le système de gestion de MaBanque !
Menu principal :
- 1- Ajouter un client
- 2- Lister les clients
- 3- Ajouter un compte
- 4- Lister les comptes des clients
- 5- Déposer
- 6- Retirer
- 7- Virement
- 8- Afficher balance
- 9- Afficher transactions
- 10- Appliquer intérêts
- 0- Quitter

## Détails d'Implémentation

### Méthodes Principales

#### Classe Banque
- ajouterClient(Client client) : Ajoute un client
- rechercherClient(String numero) : Recherche un client par numéro
- rechercherCompte(String numeroCompte) : Recherche un compte
- getTousLesComptes() : Retourne tous les comptes

#### Classe Compte
- deposer(double montant) : Effectue un dépôt
- retirer(double montant) : Effectue un retrait (retourne boolean)
- afficherTransactions() : Affiche l'historique
- getType() : Méthode abstraite implémentée par les sous-classes

#### Classe CompteEpargne
- appliquerInterets() : Calcule et ajoute les intérêts
- setTauxInteret(double taux) : Modifie le taux d'intérêt

#### Classe CSVHandler
- chargerClients(Banque, String) : Charge les clients depuis CSV
- chargerComptes(Banque, String) : Charge les comptes depuis CSV
- chargerTransactions(Banque, String) : Charge les transactions
- sauvegarderClients(Banque, String) : Sauvegarde les clients
- sauvegarderComptes(Banque, String) : Sauvegarde les comptes
- sauvegarderTransactions(Banque, String) : Sauvegarde les transactions