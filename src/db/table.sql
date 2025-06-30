CREATE TABLE
    Livre (
        id_livre SERIAL PRIMARY KEY,
        titre VARCHAR(50) NOT NULL,
        auteur VARCHAR(50)
    );

CREATE TABLE
    Exemplaire (
        id_exemplaire SERIAL PRIMARY KEY,
        code VARCHAR(250) UNIQUE,
        id_livre INT NOT NULL,
        FOREIGN KEY (id_livre) REFERENCES Livre (id_livre)
    );

CREATE TABLE
    Type (
        id_type SERIAL PRIMARY KEY,
        nom VARCHAR(50) NOT NULL
    );

CREATE TABLE
    Regle (
        id_regle SERIAL PRIMARY KEY,
        nb_jour_duree_pret_max INT NOT NULL,
        nb_livre_preter_max INT NOT NULL,
        nb_prolengement_pret_max INT NOT NULL,
        nb_jour_prolongement_max INT NOT NULL
    );

CREATE TABLE
    Statut (
        id_statut SERIAL PRIMARY KEY,
        nom VARCHAR(50) NOT NULL UNIQUE
    );

CREATE TABLE
    Role (
        id_role SERIAL PRIMARY KEY,
        nom VARCHAR(50) NOT NULL UNIQUE
    );

CREATE TABLE
    Abonnement (
        id_abonnement SERIAL PRIMARY KEY,
        mois INT NOT NULL,
        annee INT NOT NULL,
        tarif DECIMAL(25, 2) NOT NULL
    );

CREATE TABLE
    Jour_Ferie (
        id_jour_ferie SERIAL PRIMARY KEY,
        description VARCHAR(50),
        date_jf DATE NOT NULL
    );

CREATE TABLE
    Regle_Jour_Ferie (
        id_regle_jour_ferie SERIAL PRIMARY KEY,
        comportement_ INT NOT NULL,
        date_modif TIMESTAMP NOT NULL
    );

CREATE TABLE
    Etat (
        id_etat SERIAL PRIMARY KEY,
        nom VARCHAR(50) NOT NULL
    );

CREATE TABLE
    Etat_Exemplaire (
        id_etat_exemplaire SERIAL PRIMARY KEY,
        date_modif TIMESTAMP NOT NULL,
        id_exemplaire INT NOT NULL,
        id_etat INT NOT NULL,
        FOREIGN KEY (id_exemplaire) REFERENCES Exemplaire (id_exemplaire),
        FOREIGN KEY (id_etat) REFERENCES Etat (id_etat)
    );

CREATE TABLE
    Profil (
        id_profil SERIAL PRIMARY KEY,
        nom VARCHAR(50) NOT NULL,
        id_regle INT NOT NULL,
        FOREIGN KEY (id_regle) REFERENCES Regle (id_regle)
    );

CREATE TABLE
    Utilisateur (
        id_utilisateur SERIAL PRIMARY KEY,
        username VARCHAR(50) NOT NULL UNIQUE,
        mdp VARCHAR(50) NOT NULL,
        id_role INT NOT NULL,
        FOREIGN KEY (id_role) REFERENCES Role (id_role)
    );

CREATE TABLE
    Adherent (
        id_adherent SERIAL PRIMARY KEY,
        nom VARCHAR(50) NOT NULL,
        prenom VARCHAR(50) NOT NULL,
        date_de_naissance DATE NOT NULL,
        id_utilisateur INT UNIQUE,
        id_profil INT NOT NULL,
        FOREIGN KEY (id_utilisateur) REFERENCES Utilisateur (id_utilisateur),
        FOREIGN KEY (id_profil) REFERENCES Profil (id_profil)
    );

CREATE TABLE
    Reservation (
        id_reservation SERIAL PRIMARY KEY,
        date_reservation TIMESTAMP NOT NULL,
        date_debut_reservation TIMESTAMP NOT NULL,
        date_fin_reservation TIMESTAMP NOT NULL,
        id_exemplaire INT NOT NULL,
        id_adherent INT NOT NULL,
        FOREIGN KEY (id_exemplaire) REFERENCES Exemplaire (id_exemplaire),
        FOREIGN KEY (id_adherent) REFERENCES Adherent (id_adherent)
    );

CREATE TABLE
    Sanction (
        id_sanction SERIAL PRIMARY KEY,
        date_debut TIMESTAMP NOT NULL,
        date_fin TIMESTAMP NOT NULL,
        date_sanction TIMESTAMP NOT NULL,
        motif VARCHAR(50),
        id_adherent INT NOT NULL,
        FOREIGN KEY (id_adherent) REFERENCES Adherent (id_adherent)
    );

CREATE TABLE
    Statut_Reservation (
        id_statut_reservation SERIAL PRIMARY KEY,
        date_modif TIMESTAMP NOT NULL,
        id_reservation INT NOT NULL,
        id_statut INT NOT NULL,
        FOREIGN KEY (id_reservation) REFERENCES Reservation (id_reservation),
        FOREIGN KEY (id_statut) REFERENCES Statut (id_statut)
    );

CREATE TABLE
    Pret (
        id_pret SERIAL PRIMARY KEY,
        date_debut TIMESTAMP NOT NULL,
        date_fin TIMESTAMP NOT NULL,
        id_exemplaire INT NOT NULL,
        id_adherent INT NOT NULL,
        id_type INT NOT NULL,
        FOREIGN KEY (id_exemplaire) REFERENCES Exemplaire (id_exemplaire),
        FOREIGN KEY (id_adherent) REFERENCES Adherent (id_adherent),
        FOREIGN KEY (id_type) REFERENCES Type (id_type)
    );

CREATE TABLE
    Rendu (
        id_rendu SERIAL PRIMARY KEY,
        date_du_rendu TIMESTAMP NOT NULL,
        id_pret INT NOT NULL UNIQUE,
        FOREIGN KEY (id_pret) REFERENCES Pret (id_pret)
    );

CREATE TABLE
    Prolongement (
        id_prolongement SERIAL PRIMARY KEY,
        nouveau_date_fin_pret TIMESTAMP NOT NULL,
        date_prolongement TIMESTAMP NOT NULL,
        id_pret INT NOT NULL,
        FOREIGN KEY (id_pret) REFERENCES Pret (id_pret)
    );

CREATE TABLE
    Type_Exemplaire_Pret (
        id_exemplaire INT,
        id_type INT,
        PRIMARY KEY (id_exemplaire, id_type),
        FOREIGN KEY (id_exemplaire) REFERENCES Exemplaire (id_exemplaire),
        FOREIGN KEY (id_type) REFERENCES Type (id_type)
    );

CREATE TABLE
    Abonnement_Adherent (
        id_adherent INT,
        id_abonnement INT,
        date_de_payement TIMESTAMP NOT NULL,
        PRIMARY KEY (id_adherent, id_abonnement),
        FOREIGN KEY (id_adherent) REFERENCES Adherent (id_adherent),
        FOREIGN KEY (id_abonnement) REFERENCES Abonnement (id_abonnement)
    );