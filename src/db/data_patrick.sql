CREATE DATABASE biblio;
\c biblio;

-- ✅ TABLES
CREATE TABLE Livre (
    id_livre SERIAL,
    titre VARCHAR(50) NOT NULL,
    auteur VARCHAR(50),
    PRIMARY KEY (id_livre)
);

CREATE TABLE Exemplaire (
    id_exemplaire SERIAL,
    code VARCHAR(250),
    id_livre INT NOT NULL,
    PRIMARY KEY (id_exemplaire),
    UNIQUE (code),
    FOREIGN KEY (id_livre) REFERENCES Livre (id_livre)
);

CREATE TABLE Type (
    id_type SERIAL,
    nom VARCHAR(50) NOT NULL,
    PRIMARY KEY (id_type)
);

CREATE TABLE Regle (
    id_regle SERIAL,
    nb_jour_duree_pret_max INT NOT NULL,
    nb_livre_preter_max INT NOT NULL,
    nb_prolengement_pret_max INT NOT NULL,
    nb_jour_prolongement_max INT NOT NULL,
    PRIMARY KEY (id_regle)
);

CREATE TABLE Statut (
    id_statut SERIAL,
    nom VARCHAR(50) NOT NULL,
    PRIMARY KEY (id_statut),
    UNIQUE (nom)
);

CREATE TABLE Role (
    id_role SERIAL,
    nom VARCHAR(50) NOT NULL,
    PRIMARY KEY (id_role),
    UNIQUE (nom)
);

CREATE TABLE Abonnement (
    id_abonnement SERIAL,
    mois INT NOT NULL,
    annee INT NOT NULL,
    tarif DECIMAL(25, 2) NOT NULL,
    PRIMARY KEY (id_abonnement)
);

CREATE TABLE Jour_Ferie (
    id_jour_ferie SERIAL PRIMARY KEY,
    description VARCHAR(50),
    date_jf DATE NOT NULL
);

CREATE TABLE Regle_Jour_Ferie (
    id_regle_jour_ferie SERIAL PRIMARY KEY,
    comportement INT NOT NULL,
    date_modif TIMESTAMP NOT NULL
);

CREATE TABLE Etat (
    id_etat SERIAL,
    nom VARCHAR(50) NOT NULL,
    PRIMARY KEY (id_etat)
);

CREATE TABLE Etat_Exemplaire (
    id_etat_exemplaire SERIAL,
    date_modif TIMESTAMP NOT NULL,
    id_exemplaire INT NOT NULL,
    id_etat INT NOT NULL,
    PRIMARY KEY (id_etat_exemplaire),
    FOREIGN KEY (id_exemplaire) REFERENCES Exemplaire (id_exemplaire),
    FOREIGN KEY (id_etat) REFERENCES Etat (id_etat)
);

CREATE TABLE Penalite (
    id_penalite SERIAL,
    nb_jour_de_penalite INT NOT NULL,
    PRIMARY KEY (id_penalite)
);

CREATE TABLE Profil (
    id_profil SERIAL,
    nom VARCHAR(50) NOT NULL,
    id_regle INT NOT NULL,
    PRIMARY KEY (id_profil),
    FOREIGN KEY (id_regle) REFERENCES Regle (id_regle)
);

CREATE TABLE Utilisateur (
    id_utilisateur SERIAL,
    username VARCHAR(50) NOT NULL,
    mdp VARCHAR(50) NOT NULL,
    id_role INT NOT NULL,
    PRIMARY KEY (id_utilisateur),
    UNIQUE (username),
    FOREIGN KEY (id_role) REFERENCES Role (id_role)
);

CREATE TABLE Penalite_Profil (
    id_penalite_profil SERIAL,
    date_modif TIMESTAMP NOT NULL,
    id_penalite INT NOT NULL,
    id_profil INT NOT NULL,
    PRIMARY KEY (id_penalite_profil),
    FOREIGN KEY (id_penalite) REFERENCES Penalite (id_penalite),
    FOREIGN KEY (id_profil) REFERENCES Profil (id_profil)
);

CREATE TABLE Adherent (
    id_adherent SERIAL,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    date_de_naissance DATE NOT NULL,
    id_utilisateur INT,
    id_profil INT NOT NULL,
    PRIMARY KEY (id_adherent),
    UNIQUE (id_utilisateur),
    FOREIGN KEY (id_utilisateur) REFERENCES Utilisateur (id_utilisateur),
    FOREIGN KEY (id_profil) REFERENCES Profil (id_profil)
);

CREATE TABLE Reservation (
    id_reservation SERIAL,
    date_reservation TIMESTAMP NOT NULL,
    date_debut_reservation TIMESTAMP NOT NULL,
    date_fin_reservation TIMESTAMP NOT NULL,
    id_exemplaire INT NOT NULL,
    id_adherent INT NOT NULL,
    PRIMARY KEY (id_reservation),
    FOREIGN KEY (id_exemplaire) REFERENCES Exemplaire (id_exemplaire),
    FOREIGN KEY (id_adherent) REFERENCES Adherent (id_adherent)
);

CREATE TABLE Sanction (
    id_sanction SERIAL,
    date_debut TIMESTAMP NOT NULL,
    date_fin TIMESTAMP NOT NULL,
    id_adherent INT NOT NULL,
    PRIMARY KEY (id_sanction),
    FOREIGN KEY (id_adherent) REFERENCES Adherent (id_adherent)
);

CREATE TABLE Statut_Reservation (
    id_statut_reservation SERIAL,
    date_modif TIMESTAMP NOT NULL,
    id_reservation INT NOT NULL,
    id_statut INT NOT NULL,
    PRIMARY KEY (id_statut_reservation),
    FOREIGN KEY (id_reservation) REFERENCES Reservation (id_reservation),
    FOREIGN KEY (id_statut) REFERENCES Statut (id_statut)
);

CREATE TABLE Pret (
    id_pret SERIAL,
    date_debut TIMESTAMP NOT NULL,
    date_fin TIMESTAMP NOT NULL,
    id_exemplaire INT NOT NULL,
    id_adherent INT NOT NULL,
    id_type INT NOT NULL,
    PRIMARY KEY (id_pret),
    FOREIGN KEY (id_exemplaire) REFERENCES Exemplaire (id_exemplaire),
    FOREIGN KEY (id_adherent) REFERENCES Adherent (id_adherent),
    FOREIGN KEY (id_type) REFERENCES Type (id_type)
);

CREATE TABLE Rendu (
    id_rendu SERIAL,
    date_du_rendu TIMESTAMP NOT NULL,
    id_pret INT NOT NULL,
    PRIMARY KEY (id_rendu),
    UNIQUE (id_pret),
    FOREIGN KEY (id_pret) REFERENCES Pret (id_pret)
);

CREATE TABLE Prolongement (
    id_prolongement SERIAL,
    nouveau_date_fin_pret TIMESTAMP NOT NULL,
    date_prolongement TIMESTAMP NOT NULL,
    id_pret INT NOT NULL,
    PRIMARY KEY (id_prolongement),
    FOREIGN KEY (id_pret) REFERENCES Pret (id_pret)
);

CREATE TABLE Type_Exemplaire_Pret (
    id_exemplaire INT,
    id_type INT,
    PRIMARY KEY (id_exemplaire, id_type),
    FOREIGN KEY (id_exemplaire) REFERENCES Exemplaire (id_exemplaire),
    FOREIGN KEY (id_type) REFERENCES Type (id_type)
);

CREATE TABLE Abonnement_Adherent (
    id_adherent INT,
    id_abonnement INT,
    date_de_payement TIMESTAMP NOT NULL,
    PRIMARY KEY (id_adherent, id_abonnement),
    FOREIGN KEY (id_adherent) REFERENCES Adherent (id_adherent),
    FOREIGN KEY (id_abonnement) REFERENCES Abonnement (id_abonnement)
);

-- ✅ INSERTIONS

INSERT INTO Livre (titre, auteur) VALUES
('Le Petit Prince', 'Antoine de Saint-Exupéry'),
('1984', 'George Orwell');

INSERT INTO Exemplaire (code, id_livre) VALUES
('EXP-001', 1),
('EXP-002', 2);

INSERT INTO Type (nom) VALUES
('Lecture sur place'),
('Emprunt à domicile');

INSERT INTO Regle (nb_jour_duree_pret_max, nb_livre_preter_max, nb_prolengement_pret_max, nb_jour_prolongement_max) VALUES
(15, 3, 2, 7);

INSERT INTO Statut (nom) VALUES
('Actif'),
('Terminé'),
('Annulé');

INSERT INTO Role (nom) VALUES
('ADHERENT'),
('ADMIN');

INSERT INTO Utilisateur (username, mdp, id_role) VALUES
('jdupont', 'password123', 1),
('admin', 'adminpass', 2);

INSERT INTO Profil (nom, id_regle) VALUES
('Étudiant', 1);

INSERT INTO Adherent (nom, prenom, date_de_naissance, id_utilisateur, id_profil) VALUES
('Dupont', 'Jean', '1995-04-10', 1, 1);

INSERT INTO Abonnement (mois, annee, tarif) VALUES
(7, 2025, 25.00);

INSERT INTO Abonnement_Adherent (id_adherent, id_abonnement, date_de_payement) VALUES
(1, 1, NOW());

INSERT INTO Jour_Ferie (description, date_jf) VALUES
('Fête nationale', '2025-07-14');

INSERT INTO Etat (nom) VALUES
('Neuf'),
('Usé'),
('Endommagé');

INSERT INTO Etat_Exemplaire (date_modif, id_exemplaire, id_etat) VALUES
(NOW(), 1, 1),
(NOW(), 2, 2);

INSERT INTO Penalite (nb_jour_de_penalite) VALUES
(5);

INSERT INTO Penalite_Profil (date_modif, id_penalite, id_profil) VALUES
(NOW(), 1, 1);

INSERT INTO Reservation (date_reservation, date_debut_reservation, date_fin_reservation, id_exemplaire, id_adherent) VALUES
(NOW(), NOW(), NOW() + INTERVAL '3 days', 1, 1);

INSERT INTO Statut_Reservation (date_modif, id_reservation, id_statut) VALUES
(NOW(), 1, 1);

INSERT INTO Pret (date_debut, date_fin, id_exemplaire, id_adherent, id_type) VALUES
(NOW(), NOW() + INTERVAL '10 days', 1, 1, 2);

INSERT INTO Rendu (date_du_rendu, id_pret) VALUES
(NOW(), 1);

INSERT INTO Prolongement (nouveau_date_fin_pret, date_prolongement, id_pret) VALUES
(NOW() + INTERVAL '15 days', NOW(), 1);

INSERT INTO Type_Exemplaire_Pret (id_exemplaire, id_type) VALUES
(1, 1),
(1, 2),
(2, 1);

INSERT INTO Sanction (date_debut, date_fin, date_sanction, motif, id_adherent) VALUES
(NOW(), NOW() + INTERVAL '5 days', NOW(), 'Retard de retour', 1);
