DROP DATABASE if exists bibliotheque;
CREATE DATABASE bibliotheque;
\c bibliotheque;

CREATE TABLE follows (
  following_user_id integer,
  followed_user_id integer,
  created_at timestamp
);

CREATE TABLE users (
  id integer PRIMARY KEY,
  username varchar,
  role varchar,
  created_at timestamp
);

CREATE TABLE posts (
  id integer PRIMARY KEY,
  title varchar,
  body text,
  user_id integer NOT NULL,
  status varchar,
  created_at timestamp
);

CREATE TABLE Restriction (
  id_restriction SERIAL PRIMARY KEY,
  age_min INT NOT NULL
);

CREATE TABLE Livre (
  id_livre SERIAL PRIMARY KEY,
  titre VARCHAR(50) NOT NULL,
  auteur VARCHAR(50),
  id_restriction INT UNIQUE
);

CREATE TABLE Exemplaire (
  id_exemplaire SERIAL,
  code VARCHAR(250),
  id_livre INT NOT NULL,
  PRIMARY KEY (id_exemplaire)
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
  PRIMARY KEY (id_statut)
);

CREATE TABLE Role (
  id_role SERIAL,
  nom VARCHAR(50) NOT NULL,
  PRIMARY KEY (id_role)
);

CREATE TABLE Abonnement (
  id_abonnement SERIAL,
  mois INT NOT NULL,
  annee INT NOT NULL,
  tarif DECIMAL(25,2) NOT NULL,
  PRIMARY KEY (id_abonnement)
);

CREATE TABLE Jour_Ferie (
  id_jour_ferie SERIAL,
  description VARCHAR(50),
  date_jf DATE NOT NULL,
  PRIMARY KEY (id_jour_ferie)
);

CREATE TABLE Regle_Jour_Ferie (
  id_regle_jour_ferie SERIAL,
  comportement_ INT NOT NULL,
  date_modif TIMESTAMP NOT NULL,
  PRIMARY KEY (id_regle_jour_ferie)
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
  PRIMARY KEY (id_etat_exemplaire)
);

CREATE TABLE Penalite (
  id_penalite SERIAL,
  nb_jour_de_penalite INT NOT NULL,
  motif VARCHAR(50),
  PRIMARY KEY (id_penalite)
);

CREATE TABLE Profil (
  id_profil SERIAL,
  nom VARCHAR(50) NOT NULL,
  id_regle INT NOT NULL,
  PRIMARY KEY (id_profil)
);

CREATE TABLE Utilisateur (
  id_utilisateur SERIAL,
  username VARCHAR(50) NOT NULL,
  mdp VARCHAR(50) NOT NULL,
  id_role INT NOT NULL,
  PRIMARY KEY (id_utilisateur)
);

CREATE TABLE Penalite_Profil (
  id_penalite_profil SERIAL,
  date_modif TIMESTAMP NOT NULL,
  id_penalite INT NOT NULL,
  id_profil INT NOT NULL,
  PRIMARY KEY (id_penalite_profil)
);

CREATE TABLE Adherent (
  id_adherent SERIAL PRIMARY KEY,
  nom VARCHAR(50) NOT NULL,
  prenom VARCHAR(50) NOT NULL,
  date_de_naissance DATE NOT NULL,
  id_utilisateur INT UNIQUE,
  id_profil INT NOT NULL
);

CREATE TABLE Statut_Adherent (
  id_statut_adherent SERIAL PRIMARY KEY,
  id_adherent INT NOT NULL,
  nom VARCHAR(20) NOT NULL,
  date_debut DATE NOT NULL,
  date_fin DATE
);

CREATE TABLE Reservation (
  id_reservation SERIAL,
  date_reservation TIMESTAMP NOT NULL,
  date_debut_reservation TIMESTAMP NOT NULL,
  date_fin_reservation TIMESTAMP NOT NULL,
  id_exemplaire INT NOT NULL,
  id_adherent INT NOT NULL,
  PRIMARY KEY (id_reservation)
);

CREATE TABLE Sanction (
  id_sanction SERIAL,
  date_debut TIMESTAMP NOT NULL,
  date_fin TIMESTAMP NOT NULL,
  date_sanction TIMESTAMP NOT NULL,
  id_adherent INT NOT NULL,
  PRIMARY KEY (id_sanction)
);

CREATE TABLE Statut_Reservation (
  id_statut_reservation SERIAL,
  date_modif TIMESTAMP NOT NULL,
  id_reservation INT NOT NULL,
  id_statut INT NOT NULL,
  PRIMARY KEY (id_statut_reservation)
);

CREATE TABLE Pret (
  id_pret SERIAL,
  date_debut TIMESTAMP NOT NULL,
  date_fin TIMESTAMP NOT NULL,
  id_exemplaire INT NOT NULL,
  id_adherent INT NOT NULL,
  id_type INT NOT NULL,
  PRIMARY KEY (id_pret)
);

CREATE TABLE Rendu (
  id_rendu SERIAL,
  date_du_rendu TIMESTAMP NOT NULL,
  id_pret INT NOT NULL,
  PRIMARY KEY (id_rendu)
);

CREATE TABLE Prolongement (
  id_prolongement SERIAL,
  nouveau_date_fin_pret TIMESTAMP NOT NULL,
  date_prolongement TIMESTAMP NOT NULL,
  id_pret INT NOT NULL,
  status INT NOT NULL,
  PRIMARY KEY (id_prolongement)
);

CREATE TABLE Type_Exemplaire_Pret (
  id_exemplaire INT,
  id_type INT,
  PRIMARY KEY (id_exemplaire, id_type)
);

CREATE TABLE Abonnement_Adherent (
  id_adherent INT,
  id_abonnement INT,
  date_de_payement TIMESTAMP NOT NULL,
  PRIMARY KEY (id_adherent, id_abonnement)
);

CREATE UNIQUE INDEX ON Exemplaire (code);

CREATE UNIQUE INDEX ON Statut (nom);

CREATE UNIQUE INDEX ON Role (nom);

CREATE UNIQUE INDEX ON Utilisateur (username);

CREATE UNIQUE INDEX ON Rendu (id_pret);

COMMENT ON COLUMN posts.body IS 'Content of the post';

ALTER TABLE posts ADD CONSTRAINT user_posts FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE follows ADD FOREIGN KEY (following_user_id) REFERENCES users (id);

ALTER TABLE follows ADD FOREIGN KEY (followed_user_id) REFERENCES users (id);

ALTER TABLE Livre ADD FOREIGN KEY (id_restriction) REFERENCES Restriction (id_restriction);

ALTER TABLE Exemplaire ADD FOREIGN KEY (id_livre) REFERENCES Livre (id_livre);

ALTER TABLE Etat_Exemplaire ADD FOREIGN KEY (id_exemplaire) REFERENCES Exemplaire (id_exemplaire);

ALTER TABLE Etat_Exemplaire ADD FOREIGN KEY (id_etat) REFERENCES Etat (id_etat);

ALTER TABLE Profil ADD FOREIGN KEY (id_regle) REFERENCES Regle (id_regle);

ALTER TABLE Utilisateur ADD FOREIGN KEY (id_role) REFERENCES Role (id_role);

ALTER TABLE Penalite_Profil ADD FOREIGN KEY (id_penalite) REFERENCES Penalite (id_penalite);

ALTER TABLE Penalite_Profil ADD FOREIGN KEY (id_profil) REFERENCES Profil (id_profil);

ALTER TABLE Adherent ADD FOREIGN KEY (id_utilisateur) REFERENCES Utilisateur (id_utilisateur);

ALTER TABLE Adherent ADD FOREIGN KEY (id_profil) REFERENCES Profil (id_profil);

ALTER TABLE Statut_Adherent ADD FOREIGN KEY (id_adherent) REFERENCES Adherent (id_adherent);

ALTER TABLE Reservation ADD FOREIGN KEY (id_exemplaire) REFERENCES Exemplaire (id_exemplaire);

ALTER TABLE Reservation ADD FOREIGN KEY (id_adherent) REFERENCES Adherent (id_adherent);

ALTER TABLE Sanction ADD FOREIGN KEY (id_adherent) REFERENCES Adherent (id_adherent);

ALTER TABLE Statut_Reservation ADD FOREIGN KEY (id_reservation) REFERENCES Reservation (id_reservation);

ALTER TABLE Statut_Reservation ADD FOREIGN KEY (id_statut) REFERENCES Statut (id_statut);

ALTER TABLE Pret ADD FOREIGN KEY (id_exemplaire) REFERENCES Exemplaire (id_exemplaire);

ALTER TABLE Pret ADD FOREIGN KEY (id_adherent) REFERENCES Adherent (id_adherent);

ALTER TABLE Pret ADD FOREIGN KEY (id_type) REFERENCES Type (id_type);

ALTER TABLE Rendu ADD FOREIGN KEY (id_pret) REFERENCES Pret (id_pret);

ALTER TABLE Prolongement ADD FOREIGN KEY (id_pret) REFERENCES Pret (id_pret);

ALTER TABLE Type_Exemplaire_Pret ADD FOREIGN KEY (id_exemplaire) REFERENCES Exemplaire (id_exemplaire);

ALTER TABLE Type_Exemplaire_Pret ADD FOREIGN KEY (id_type) REFERENCES Type (id_type);

ALTER TABLE Abonnement_Adherent ADD FOREIGN KEY (id_adherent) REFERENCES Adherent (id_adherent);

ALTER TABLE Abonnement_Adherent ADD FOREIGN KEY (id_abonnement) REFERENCES Abonnement (id_abonnement);
