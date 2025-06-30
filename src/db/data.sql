-- Insertion de 3 livres
INSERT INTO
    Livre (titre, auteur)
VALUES
    ('Le Petit Prince', 'Antoine de Saint-Exupéry'),
    ('1984', 'George Orwell'),
    ('Les Misérables', 'Victor Hugo');

-- Insertion de 3 exemplaires par livre
INSERT INTO
    Exemplaire (code, id_livre)
VALUES
    ('EX-0001', 1),
    ('EX-0002', 1),
    ('EX-0003', 1),
    ('EX-0004', 2),
    ('EX-0005', 2),
    ('EX-0006', 2),
    ('EX-0007', 3),
    ('EX-0008', 3),
    ('EX-0009', 3);

-- Insertion de 2 états possibles pour les exemplaires
INSERT INTO
    Etat (nom)
VALUES
    ('disponible'),
    ('en pret');

-- Insertion de 2 types (exemple)
INSERT INTO
    Type (nom)
VALUES
    ('Maison'),
    ('Sur place');

-- Insertion de 3 rôles d'utilisateurs
INSERT INTO
    Role (nom)
VALUES
    ('bibliothequaire'),
    ('adherent'),
    ('admin');

-- Insertion de 3 utilisateurs avec leur rôle associé
INSERT INTO
    Utilisateur (username, mdp, id_role)
VALUES
    ('user_biblio', 'mdp1', 1), -- bibliothequaire
    ('user_adherent', 'mdp2', 2), -- adherent
    ('user_admin', 'mdp3', 3);

-- admin
-- Insertion d'une règle générale pour les profils
INSERT INTO
    Regle (
        nb_jour_duree_pret_max,
        nb_livre_preter_max,
        nb_prolengement_pret_max,
        nb_jour_prolongement_max
    )
VALUES
    (
        30, -- durée max d'un prêt (en jours)
        5, -- nombre max de livres à prêter simultanément
        2, -- nombre max de prolongements par prêt
        15 -- nombre max de jours par prolongement
    );

-- Insertion de 3 profils avec la règle d'id 1
INSERT INTO
    Profil (nom, id_regle)
VALUES
    ('etudiant', 1),
    ('professeur', 1),
    ('autre', 1);

-- Insertion de 3 adhérents, liés ou non à un utilisateur et à un profil
INSERT INTO
    Adherent (
        nom,
        prenom,
        date_de_naissance,
        id_utilisateur,
        id_profil
    )
VALUES
    ('Rakoto', 'Jean', '1990-05-15', 2, 1), -- user_adherent, profil etudiant
    ('Rabe', 'Marie', '1985-09-20', NULL, 2), -- pas d'utilisateur, profil professeur
    ('Andria', 'Paul', '1975-12-30', NULL, 3);

-- pas d'utilisateur, profil autre

INSERT INTO
    Statut (nom)
VALUES
    ('en attente'),
    ('valider');