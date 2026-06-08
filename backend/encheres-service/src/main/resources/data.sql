-- data.sql : données d'exemple pour Client, Enchere et OffreManuelle

-- 1) Clients
INSERT INTO client (id, nom, prenom, email)
VALUES (1, 'Dupont', 'Jean', 'jean.dupont@example.com'),
       (2, 'Martin', 'Claire', 'claire.martin@example.com'),
       (3, 'Bernard', 'Sophie', 'sophie.bernard@exemple.com'),
       (4, 'Moreau', 'Luc', 'luc.moreau@exemple.com');


-- 2) Enchères
INSERT INTO enchere (id, description, montant_initial, montant_courant, date_debut, date_fin, statut, type)
VALUES (1, 'Vélo électrique', 100.00, 150.00, '2027-06-01 10:00:00', '2026-06-10 18:00:00', 'ACTIVE',MANUELLE),
       (2, 'Téléviseur 4K', 200.00, 220.00, '2027-05-20 09:00:00', '2026-05-25 20:00:00', 'ACTIVE',MANUELLE);


-- 3) Offres
INSERT INTO offre (id, date_creation, client_id, enchere_id, type_offre, montant)
VALUES (1, '2026-06-02 12:00:00', 1, 1, 'OFFRE_MANUELLE', 120.00),
       (2, '2026-06-03 14:30:00', 2, 1, 'OFFRE_MANUELLE', 150.00),
       (3, '2026-05-21 11:00:00', 1, 2, 'OFFRE_MANUELLE', 220.00);