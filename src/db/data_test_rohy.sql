
INSERT INTO Pret (date_debut, date_fin, id_exemplaire, id_adherent, id_type) VALUES
-- id_adherent = 2
('2025-07-01', '2025-07-10', 1, 2, 1),
('2025-07-02', '2025-07-11', 2, 2, 2),
('2025-07-03', '2025-07-12', 3, 2, 1),
('2025-07-04', '2025-07-13', 4, 2, 2),
('2025-07-05', '2025-07-14', 5, 2, 1),
('2025-07-06', '2025-07-15', 6, 2, 2);


INSERT INTO Date_Prevue_Rendu (id_pret, date_prevue) VALUES
-- id_adherent 2 (pret id 10 à 19)
(10, '2025-07-10 00:00:00'),
(11, '2025-07-11 00:00:00'),
(12, '2025-07-12 00:00:00'),
(13, '2025-07-13 00:00:00'),
(14, '2025-07-14 00:00:00'),
(15, '2025-07-15 00:00:00');


INSERT INTO Rendu (id_pret, date_du_rendu) VALUES
-- id_adherent 2 (id_pret 10 à 15)
(10, '2025-07-10 09:00:00'),  -- à l'heure (même date que prévue)
(11, '2025-07-10 15:00:00'),  -- en avance (avant la date prévue 11)
(12, '2025-07-11 08:00:00'),  -- en avance (avant la date prévue 12)
(13, '2025-07-15 10:00:00'),  -- retard (après la date prévue 13)
(14, '2025-07-16 10:00:00'),  -- retard (après la date prévue 14)
(15, '2025-07-17 10:00:00');  -- retard (après la date prévue 15)
