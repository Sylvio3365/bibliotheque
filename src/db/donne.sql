INSERT INTO
    Role (nom)
VALUES
    ('bibliothecaire'),
    ('adherent'),
    ('admin');

INSERT INTO
    Utilisateur (username, mdp, id_role)
VALUES
    ('user_biblio', 'mdp1', 1), -- bibliothequaire
    ('user_adherent', 'mdp2', 2), -- adherent
    ('user_admin', 'mdp3', 3);

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

INSERT INTO
    statut_adherent (
        id_statut_adherent,
        date_debut,
        date_fin,
        nom,
        id_adherent
    )
VALUES
    (1, '2025-01-01', '2025-10-31', 'Actif', 13),
    (2, '2025-01-01', '2025-12-31', 'Inactif', 14),
    (3, '2025-06-01', NULL, 'Suspendu', 15);