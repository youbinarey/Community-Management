-- Resetear secuencias a 1 (inicia el siguiente id desde 1)
-- SELECT setval('bank_account_id_seq', 1, false);
-- SELECT setval('communities_id_seq', 1, false);
-- SELECT setval('invoice_id_seq', 1, false);
-- SELECT setval('owner_id_seq', 1, false);
-- SELECT setval('owner_invoice_id_seq', 1, false);
-- SELECT setval('property_id_seq', 1, false);

-- Insertar comunidades
-- Comunidades (5)
INSERT INTO communities (elevator, num_flats, num_parkings, num_storage_rooms, reduced_mobility_access, address, postal_code) VALUES
(true, 18, 18, 18, false, 'Avenida Mar Egeo 14', '28032'),
(false, 12, 12, 12, true, 'Calle Rio Lobos 22', '28042'),
(true, 24, 24, 24, false, 'Camino Robledal 9', '28055');
--(false, 16, 16, 16, true, 'Plaza Sol 7', '28021'),
--(true, 20, 20, 20, false, 'Calle Lago Azul 6', '28029');


-- 2. Crear cuentas bancarias para comunidades y asignar
-- Cuentas bancarias para comunidades
INSERT INTO bank_account (balance, account_number, bank_name) VALUES
(8100.00, 'ES100000001', 'BBVA'),
(6900.00, 'ES100000002', 'Santander'),
(9450.00, 'ES100000003', 'Bankinter'),
(7200.00, 'ES100000004', 'CaixaBank'),
(6600.00, 'ES100000005', 'Sabadell');

UPDATE communities SET bank_account_id = 1 WHERE id = 1;
UPDATE communities SET bank_account_id = 2 WHERE id = 2;
UPDATE communities SET bank_account_id = 3 WHERE id = 3;
--UPDATE communities SET bank_account_id = 4 WHERE id = 4;
--UPDATE communities SET bank_account_id = 5 WHERE id = 5;



-- Propiedades Vivienda para Comunidad 1
-- Propiedades Comunidad 1 (flats, parkings, storages)
-- Flats (6 pisos x 3 flats por piso)
-- Propiedades Comunidad 1 (flats, parkings, storages)
-- Flats (6 pisos x 3 flats por piso)
INSERT INTO property (coefficient, square_meters, community_id, owner_id, property_type, cadastral_reference) VALUES
(3.7, 88, 1, NULL, 'FLAT', 'REF-C1-P1-A'),
(3.7, 88, 1, NULL, 'FLAT', 'REF-C1-P1-B'),
(3.1, 88, 1, NULL, 'FLAT', 'REF-C1-P1-C'),
(3.1, 78, 1, NULL, 'FLAT', 'REF-C1-P2-A'),
(3.3, 78, 1, NULL, 'FLAT', 'REF-C1-P2-B'),
(3.2, 78, 1, NULL, 'FLAT', 'REF-C1-P2-C'),
(3.7, 96, 1, NULL, 'FLAT', 'REF-C1-P3-A'),
(3.5, 96, 1, NULL, 'FLAT', 'REF-C1-P3-B'),
(3.3, 96, 1, NULL, 'FLAT', 'REF-C1-P3-C'),
(3.2, 73, 1, NULL, 'FLAT', 'REF-C1-P4-A'),
(3.7, 73, 1, NULL, 'FLAT', 'REF-C1-P4-B'),
(3.7, 73, 1, NULL, 'FLAT', 'REF-C1-P4-C'),
(3.7, 86, 1, NULL, 'FLAT', 'REF-C1-P5-A'),
(3.7, 86, 1, NULL, 'FLAT', 'REF-C1-P5-B'),
(3.7, 86, 1, NULL, 'FLAT', 'REF-C1-P5-C'),
(3.7, 82, 1, NULL, 'FLAT', 'REF-C1-P6-A'),
(3.7, 82, 1, NULL, 'FLAT', 'REF-C1-P6-B'),
(3.7, 82, 1, NULL, 'FLAT', 'REF-C1-P6-C'),

-- Parkings
(2.3, 12, 1, NULL, 'PARKING', 'REF-C1-PARK-1'),
(2.3, 12, 1, NULL, 'PARKING', 'REF-C1-PARK-2'),
(2.3, 12, 1, NULL, 'PARKING', 'REF-C1-PARK-3'),
(2.3, 12, 1, NULL, 'PARKING', 'REF-C1-PARK-4'),
(2.3, 12, 1, NULL, 'PARKING', 'REF-C1-PARK-5'),
(2.3, 12, 1, NULL, 'PARKING', 'REF-C1-PARK-6'),
(2.3, 12, 1, NULL, 'PARKING', 'REF-C1-PARK-7'),
(2.3, 12, 1, NULL, 'PARKING', 'REF-C1-PARK-8'),
(2.3, 12, 1, NULL, 'PARKING', 'REF-C1-PARK-9'),
(2.3, 12, 1, NULL, 'PARKING', 'REF-C1-PARK-10'),
(2.3, 12, 1, NULL, 'PARKING', 'REF-C1-PARK-11'),
(2.3, 12, 1, NULL, 'PARKING', 'REF-C1-PARK-12'),
(2.3, 12, 1, NULL, 'PARKING', 'REF-C1-PARK-13'),
(2.3, 12, 1, NULL, 'PARKING', 'REF-C1-PARK-14'),
(2.3, 12, 1, NULL, 'PARKING', 'REF-C1-PARK-15'),
(2.3, 12, 1, NULL, 'PARKING', 'REF-C1-PARK-16'),
(2.3, 12, 1, NULL, 'PARKING', 'REF-C1-PARK-17'),
(2.3, 12, 1, NULL, 'PARKING', 'REF-C1-PARK-18'),

-- Storages
(1.30, 6, 1, NULL, 'STORAGEROOM', 'REF-C1-STORE-1'),
(1.34, 6, 1, NULL, 'STORAGEROOM', 'REF-C1-STORE-2'),
(1.34, 6, 1, NULL, 'STORAGEROOM', 'REF-C1-STORE-3'),
(1.34, 6, 1, NULL, 'STORAGEROOM', 'REF-C1-STORE-4'),
(1.34, 6, 1, NULL, 'STORAGEROOM', 'REF-C1-STORE-5'),
(1.34, 6, 1, NULL, 'STORAGEROOM', 'REF-C1-STORE-6'),
(1.34, 6, 1, NULL, 'STORAGEROOM', 'REF-C1-STORE-7'),
(1.34, 6, 1, NULL, 'STORAGEROOM', 'REF-C1-STORE-8'),
(1.34, 6, 1, NULL, 'STORAGEROOM', 'REF-C1-STORE-9'),
(1.34, 6, 1, NULL, 'STORAGEROOM', 'REF-C1-STORE-10'),
(1.34, 6, 1, NULL, 'STORAGEROOM', 'REF-C1-STORE-11'),
(1.34, 6, 1, NULL, 'STORAGEROOM', 'REF-C1-STORE-12'),
(1.34, 6, 1, NULL, 'STORAGEROOM', 'REF-C1-STORE-13'),
(1.34, 6, 1, NULL, 'STORAGEROOM', 'REF-C1-STORE-14'),
(1.34, 6, 1, NULL, 'STORAGEROOM', 'REF-C1-STORE-15'),
(1.34, 6, 1, NULL, 'STORAGEROOM', 'REF-C1-STORE-16'),
(1.34, 6, 1, NULL, 'STORAGEROOM', 'REF-C1-STORE-17'),
(1.34, 6, 1, NULL, 'STORAGEROOM', 'REF-C1-STORE-18');


--Propietartios
-- Propietarios
-- Propietarios Comunidad 1
INSERT INTO owner (birth_date, bank_account_id, dni, email, name, phone, surname) VALUES
('1982-07-13', NULL, 'DNI101', 'mario.pons@mail.com', 'Mario', '611100001', 'Pons'),
('1985-02-26', NULL, 'DNI102', 'laura.soto@mail.com', 'Laura', '611100002', 'Soto'),
('1980-11-09', NULL, 'DNI103', 'jose.blasco@mail.com', 'Jose', '611100003', 'Blasco'),
('1979-04-16', NULL, 'DNI104', 'elena.vives@mail.com', 'Elena', '611100004', 'Vives'),
('1988-01-21', NULL, 'DNI105', 'hector.morales@mail.com', 'Hector', '611100005', 'Morales'),
('1984-05-30', NULL, 'DNI106', 'carmen.torres@mail.com', 'Carmen', '611100006', 'Torres');



-- Asignacion de propiedades a propietarios Comunidad 1
UPDATE property SET owner_id = 1 WHERE cadastral_reference IN ('REF-C1-P1-A', 'REF-C1-P2-A', 'REF-C1-PARK-1', 'REF-C1-STORE-1', 'REF-C1-STORE-2');
UPDATE property SET owner_id = 2 WHERE cadastral_reference IN ('REF-C1-P1-B', 'REF-C1-P3-B', 'REF-C1-PARK-2', 'REF-C1-PARK-3', 'REF-C1-STORE-3');
UPDATE property SET owner_id = 3 WHERE cadastral_reference IN ('REF-C1-P1-C', 'REF-C1-P4-C', 'REF-C1-PARK-4', 'REF-C1-PARK-5');
UPDATE property SET owner_id = 4 WHERE cadastral_reference IN ('REF-C1-P2-B', 'REF-C1-P5-B', 'REF-C1-STORE-4', 'REF-C1-STORE-5', 'REF-C1-STORE-6');
UPDATE property SET owner_id = 5 WHERE cadastral_reference IN ('REF-C1-P3-A', 'REF-C1-P6-A', 'REF-C1-PARK-6', 'REF-C1-PARK-7');
UPDATE property SET owner_id = 6 WHERE cadastral_reference IN ('REF-C1-P4-A', 'REF-C1-P4-B', 'REF-C1-P5-A', 'REF-C1-P5-C', 'REF-C1-P6-B', 'REF-C1-P6-C');


--CUENTAS BANCARIAS
INSERT INTO bank_account (balance, account_number, bank_name)
VALUES 
    (2000.00, 'ES2000010001', 'BBVA'),
    (2500.00, 'ES2000020002', 'Santander'),
    (1800.00, 'ES2000030003', 'Sabadell'),
    (2200.00, 'ES2000040004', 'CaixaBank'),
    (3000.00, 'ES2000050005', 'Bankinter'),
    (2700.00, 'ES2000060006', 'Unicaja');




UPDATE owner SET bank_account_id = 3 WHERE id = 1; -- BBVA (id=3) (porque id=1 de bank_account ya se usó para comunidad)
UPDATE owner SET bank_account_id = 4 WHERE id = 2; -- Santander (id=4)
UPDATE owner SET bank_account_id = 5 WHERE id = 3; -- Sabadell (id=5)
UPDATE owner SET bank_account_id = 6 WHERE id = 4; -- CaixaBank (id=6)
UPDATE owner SET bank_account_id = 7 WHERE id = 5; -- Bankinter (id=7)
UPDATE owner SET bank_account_id = 8 WHERE id = 6; -- Unicaja (id=8)



-- Insertar flats (diferenciados por floorNumber, letter, etc.)
-- Flats Comunidad 1
INSERT INTO flat (id, floor_number, letter, room_count, bathroom_count) VALUES
((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-P1-A'), 1, 'A', 3, 2),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-P1-B'), 1, 'B', 2, 1),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-P1-C'), 1, 'C', 3, 1),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-P2-A'), 2, 'A', 4, 2),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-P2-B'), 2, 'B', 2, 1),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-P2-C'), 2, 'C', 3, 2),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-P3-A'), 3, 'A', 2, 1),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-P3-B'), 3, 'B', 3, 1),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-P3-C'), 3, 'C', 3, 2),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-P4-A'), 4, 'A', 2, 2),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-P4-B'), 4, 'B', 3, 1),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-P4-C'), 4, 'C', 2, 1),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-P5-A'), 5, 'A', 4, 2),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-P5-B'), 5, 'B', 2, 1),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-P5-C'), 5, 'C', 3, 1),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-P6-A'), 6, 'A', 2, 2),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-P6-B'), 6, 'B', 3, 1),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-P6-C'), 6, 'C', 3, 2);

-- Parkings Comunidad 1
INSERT INTO parking (id, num) VALUES
((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-PARK-1'), 1),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-PARK-2'), 2),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-PARK-3'), 3),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-PARK-4'), 4),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-PARK-5'), 5),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-PARK-6'), 6),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-PARK-7'), 7),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-PARK-8'), 8),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-PARK-9'), 9),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-PARK-10'), 10),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-PARK-11'), 11),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-PARK-12'), 12),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-PARK-13'), 13),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-PARK-14'), 14),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-PARK-15'), 15),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-PARK-16'), 16),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-PARK-17'), 17),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-PARK-18'), 18);

-- Storages Comunidad 1
INSERT INTO storage_room (id, storage_number) VALUES
((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-STORE-1'), 101),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-STORE-2'), 102),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-STORE-3'), 103),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-STORE-4'), 104),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-STORE-5'), 105),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-STORE-6'), 106),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-STORE-7'), 107),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-STORE-8'), 108),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-STORE-9'), 109),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-STORE-10'), 110),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-STORE-11'), 111),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-STORE-12'), 112),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-STORE-13'), 113),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-STORE-14'), 114),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-STORE-15'), 115),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-STORE-16'), 116),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-STORE-17'), 117),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-STORE-18'), 118);




-- Propiedades flats Comunidad 2
INSERT INTO property (coefficient, square_meters, community_id, owner_id, property_type, cadastral_reference) VALUES
(3.10, 83, 2, NULL, 'FLAT', 'REF-C2-P1-A'),
(2.7, 86, 2, NULL, 'FLAT', 'REF-C2-P1-B'),
(3.7094, 75, 2, NULL, 'FLAT', 'REF-C2-P1-C'),
(3.10, 83, 2, NULL, 'FLAT', 'REF-C2-P2-A'),
(2.7, 86, 2, NULL, 'FLAT', 'REF-C2-P2-B'),
(3.7094, 75, 2, NULL, 'FLAT', 'REF-C2-P2-C'),
(3.7115, 92, 2, NULL, 'FLAT', 'REF-C2-P3-A'),
(3.7101, 81, 2, NULL, 'FLAT', 'REF-C2-P3-B'),
(3.7095, 76, 2, NULL, 'FLAT', 'REF-C2-P3-C'),
(3.7110, 88, 2, NULL, 'FLAT', 'REF-C2-P4-A'),
(3.7099, 79, 2, NULL, 'FLAT', 'REF-C2-P4-B'),
(3.7094, 75, 2, NULL, 'FLAT', 'REF-C2-P4-C');

-- Parkings Comunidad 2
INSERT INTO property (coefficient, square_meters, community_id, owner_id, property_type, cadastral_reference) VALUES
(2.3, 12, 2, NULL, 'PARKING', 'REF-C2-PARK-1'),
(2.3, 12, 2, NULL, 'PARKING', 'REF-C2-PARK-2'),
(2.3, 12, 2, NULL, 'PARKING', 'REF-C2-PARK-3'),
(2.3, 12, 2, NULL, 'PARKING', 'REF-C2-PARK-4'),
(2.3, 12, 2, NULL, 'PARKING', 'REF-C2-PARK-5'),
(2.3, 12, 2, NULL, 'PARKING', 'REF-C2-PARK-6'),
(2.3, 12, 2, NULL, 'PARKING', 'REF-C2-PARK-7'),
(2.3, 12, 2, NULL, 'PARKING', 'REF-C2-PARK-8'),
(2.3, 12, 2, NULL, 'PARKING', 'REF-C2-PARK-9'),
(2.3, 12, 2, NULL, 'PARKING', 'REF-C2-PARK-10'),
(2.3, 12, 2, NULL, 'PARKING', 'REF-C2-PARK-11'),
(2.3, 12, 2, NULL, 'PARKING', 'REF-C2-PARK-12');

-- Storages Comunidad 2
INSERT INTO property (coefficient, square_meters, community_id, owner_id, property_type, cadastral_reference) VALUES
(1.34, 6, 2, NULL, 'STORAGEROOM', 'REF-C2-STORE-1'),
(1.34, 6, 2, NULL, 'STORAGEROOM', 'REF-C2-STORE-2'),
(1.34, 6, 2, NULL, 'STORAGEROOM', 'REF-C2-STORE-3'),
(1.34, 6, 2, NULL, 'STORAGEROOM', 'REF-C2-STORE-4'),
(1.34, 6, 2, NULL, 'STORAGEROOM', 'REF-C2-STORE-5'),
(1.34, 6, 2, NULL, 'STORAGEROOM', 'REF-C2-STORE-6'),
(1.34, 6, 2, NULL, 'STORAGEROOM', 'REF-C2-STORE-7'),
(1.34, 6, 2, NULL, 'STORAGEROOM', 'REF-C2-STORE-8'),
(1.34, 6, 2, NULL, 'STORAGEROOM', 'REF-C2-STORE-9'),
(1.34, 6, 2, NULL, 'STORAGEROOM', 'REF-C2-STORE-10'),
(1.34, 6, 2, NULL, 'STORAGEROOM', 'REF-C2-STORE-11'),
(1.34, 6, 2, NULL, 'STORAGEROOM', 'REF-C2-STORE-12');

-- Propietarios Comunidad 2
INSERT INTO owner (birth_date, bank_account_id, dni, email, name, phone, surname) VALUES
('1978-06-02', NULL, 'DNI107', 'lucas.mesa@mail.com', 'Lucas', '611100007', 'Mesa'),
('1990-09-23', NULL, 'DNI108', 'sonia.lopez@mail.com', 'Sonia', '611100008', 'Lopez'),
('1987-03-15', NULL, 'DNI109', 'mario.diaz@mail.com', 'Mario', '611100009', 'Diaz'),
('1984-11-08', NULL, 'DNI110', 'carla.vila@mail.com', 'Carla', '611100010', 'Vila'),
('1976-12-01', NULL, 'DNI111', 'javier.ros@mail.com', 'Javier', '611100011', 'Ros');

-- Asignacion de propiedades a propietarios Comunidad 2
UPDATE property SET owner_id = 7 WHERE cadastral_reference IN ('REF-C2-P1-A', 'REF-C2-PARK-1', 'REF-C2-STORE-1', 'REF-C2-STORE-2');
UPDATE property SET owner_id = 8 WHERE cadastral_reference IN ('REF-C2-P1-B', 'REF-C2-P1-C', 'REF-C2-PARK-2', 'REF-C2-STORE-3', 'REF-C2-STORE-4');
UPDATE property SET owner_id = 9 WHERE cadastral_reference IN ('REF-C2-P2-A', 'REF-C2-P2-B', 'REF-C2-PARK-3', 'REF-C2-PARK-4');
UPDATE property SET owner_id = 10 WHERE cadastral_reference IN ('REF-C2-P2-C', 'REF-C2-P3-A', 'REF-C2-PARK-5', 'REF-C2-PARK-6');
UPDATE property SET owner_id = 11 WHERE cadastral_reference IN ('REF-C2-P3-B', 'REF-C2-P3-C', 'REF-C2-P4-A', 'REF-C2-PARK-7', 'REF-C2-STORE-5', 'REF-C2-STORE-6');

-- Flats Comunidad 2
INSERT INTO flat (id, floor_number, letter, room_count, bathroom_count) VALUES
((SELECT id FROM property WHERE cadastral_reference = 'REF-C2-P1-A'), 1, 'A', 2, 1),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C2-P1-B'), 1, 'B', 3, 2),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C2-P1-C'), 1, 'C', 2, 1),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C2-P2-A'), 2, 'A', 3, 1),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C2-P2-B'), 2, 'B', 2, 2),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C2-P2-C'), 2, 'C', 4, 2),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C2-P3-A'), 3, 'A', 3, 1),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C2-P3-B'), 3, 'B', 2, 2),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C2-P3-C'), 3, 'C', 3, 2),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C2-P4-A'), 4, 'A', 2, 1),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C2-P4-B'), 4, 'B', 2, 2),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C2-P4-C'), 4, 'C', 3, 2);

-- Parkings Comunidad 2
INSERT INTO parking (id, num) VALUES
((SELECT id FROM property WHERE cadastral_reference = 'REF-C2-PARK-1'), 1),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C2-PARK-2'), 2),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C2-PARK-3'), 3),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C2-PARK-4'), 4),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C2-PARK-5'), 5),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C2-PARK-6'), 6),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C2-PARK-7'), 7),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C2-PARK-8'), 8),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C2-PARK-9'), 9),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C2-PARK-10'), 10),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C2-PARK-11'), 11),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C2-PARK-12'), 12);

-- Storages Comunidad 2
INSERT INTO storage_room (id, storage_number) VALUES
((SELECT id FROM property WHERE cadastral_reference = 'REF-C2-STORE-1'), 201),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C2-STORE-2'), 202),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C2-STORE-3'), 203),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C2-STORE-4'), 204),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C2-STORE-5'), 205),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C2-STORE-6'), 206),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C2-STORE-7'), 207),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C2-STORE-8'), 208),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C2-STORE-9'), 209),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C2-STORE-10'), 210),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C2-STORE-11'), 211),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C2-STORE-12'), 212);


-- Propietarios Comunidad 3 (id=12 al 19)
INSERT INTO owner (birth_date, bank_account_id, dni, email, name, phone, surname) VALUES
('1986-03-11', NULL, 'DNI112', 'raul.pardo@mail.com', 'Raul', '611100012', 'Pardo'),
('1988-05-20', NULL, 'DNI113', 'carlos.mira@mail.com', 'Carlos', '611100013', 'Mira'),
('1991-09-05', NULL, 'DNI114', 'marta.rubio@mail.com', 'Marta', '611100014', 'Rubio'),
('1979-01-23', NULL, 'DNI115', 'lorena.soler@mail.com', 'Lorena', '611100015', 'Soler'),
('1983-08-17', NULL, 'DNI116', 'jordi.rivas@mail.com', 'Jordi', '611100016', 'Rivas'),
('1992-10-14', NULL, 'DNI117', 'irene.moya@mail.com', 'Irene', '611100017', 'Moya'),
('1984-04-07', NULL, 'DNI118', 'javier.vera@mail.com', 'Javier', '611100018', 'Vera'),
('1981-12-30', NULL, 'DNI119', 'lucia.alcaraz@mail.com', 'Lucia', '611100019', 'Alcaraz');

-- Cuentas bancarias para propietarios de Comunidad 3 (id=12 al 19)
INSERT INTO bank_account (balance, account_number, bank_name) VALUES
(2100.00, 'ES2000100012', 'Openbank'),
(2200.00, 'ES2000100013', 'Sabadell'),
(2050.00, 'ES2000100014', 'ING'),
(2400.00, 'ES2000100015', 'CaixaBank'),
(2550.00, 'ES2000100016', 'Bankinter'),
(2120.00, 'ES2000100017', 'Abanca'),
(2290.00, 'ES2000100018', 'Kutxabank'),
(2480.00, 'ES2000100019', 'EvoBanco');

-- Asignar cada banco a cada propietario de Comunidad 3
UPDATE owner SET bank_account_id = 12 WHERE id = 12;
UPDATE owner SET bank_account_id = 13 WHERE id = 13;
UPDATE owner SET bank_account_id = 14 WHERE id = 14;
UPDATE owner SET bank_account_id = 15 WHERE id = 15;
UPDATE owner SET bank_account_id = 16 WHERE id = 16;
UPDATE owner SET bank_account_id = 17 WHERE id = 17;
UPDATE owner SET bank_account_id = 18 WHERE id = 18;
UPDATE owner SET bank_account_id = 19 WHERE id = 19;


-- Propiedades Comunidad 3 (24 flats, 24 parkings, 24 storages)
INSERT INTO property (coefficient, square_meters, community_id, owner_id, property_type, cadastral_reference) VALUES
-- Flats
(3.7120, 98, 3, NULL, 'FLAT', 'REF-C3-P1-A'),
(3.7112, 91, 3, NULL, 'FLAT', 'REF-C3-P1-B'),
(2.7, 87, 3, NULL, 'FLAT', 'REF-C3-P1-C'),
(3.7115, 93, 3, NULL, 'FLAT', 'REF-C3-P1-D'),
(3.7120, 98, 3, NULL, 'FLAT', 'REF-C3-P2-A'),
(3.7112, 91, 3, NULL, 'FLAT', 'REF-C3-P2-B'),
(2.7, 87, 3, NULL, 'FLAT', 'REF-C3-P2-C'),
(3.7115, 93, 3, NULL, 'FLAT', 'REF-C3-P2-D'),
(3.7121, 99, 3, NULL, 'FLAT', 'REF-C3-P3-A'),
(3.7114, 92, 3, NULL, 'FLAT', 'REF-C3-P3-B'),
(3.7109, 88, 3, NULL, 'FLAT', 'REF-C3-P3-C'),
(3.7116, 94, 3, NULL, 'FLAT', 'REF-C3-P3-D'),
(3.7123, 101, 3, NULL, 'FLAT', 'REF-C3-P4-A'),
(3.7115, 94, 3, NULL, 'FLAT', 'REF-C3-P4-B'),
(3.7110, 89, 3, NULL, 'FLAT', 'REF-C3-P4-C'),
(3.7117, 95, 3, NULL, 'FLAT', 'REF-C3-P4-D'),
(3.7124, 102, 3, NULL, 'FLAT', 'REF-C3-P5-A'),
(3.7113, 93, 3, NULL, 'FLAT', 'REF-C3-P5-B'),
(2.7, 88, 3, NULL, 'FLAT', 'REF-C3-P5-C'),
(3.7114, 92, 3, NULL, 'FLAT', 'REF-C3-P5-D'),
(3.7125, 104, 3, NULL, 'FLAT', 'REF-C3-P6-A'),
(3.7118, 96, 3, NULL, 'FLAT', 'REF-C3-P6-B'),
(3.7111, 90, 3, NULL, 'FLAT', 'REF-C3-P6-C'),
(3.7116, 94, 3, NULL, 'FLAT', 'REF-C3-P6-D'),

-- Parkings
(2.3, 12, 3, NULL, 'PARKING', 'REF-C3-PARK-1'),
(2.3, 12, 3, NULL, 'PARKING', 'REF-C3-PARK-2'),
(2.3, 12, 3, NULL, 'PARKING', 'REF-C3-PARK-3'),
(2.3, 12, 3, NULL, 'PARKING', 'REF-C3-PARK-4'),
(2.3, 12, 3, NULL, 'PARKING', 'REF-C3-PARK-5'),
(2.3, 12, 3, NULL, 'PARKING', 'REF-C3-PARK-6'),
(2.3, 12, 3, NULL, 'PARKING', 'REF-C3-PARK-7'),
(2.3, 12, 3, NULL, 'PARKING', 'REF-C3-PARK-8'),
(2.3, 12, 3, NULL, 'PARKING', 'REF-C3-PARK-9'),
(2.3, 12, 3, NULL, 'PARKING', 'REF-C3-PARK-10'),
(2.3, 12, 3, NULL, 'PARKING', 'REF-C3-PARK-11'),
(2.3, 12, 3, NULL, 'PARKING', 'REF-C3-PARK-12'),
(2.3, 12, 3, NULL, 'PARKING', 'REF-C3-PARK-13'),
(2.3, 12, 3, NULL, 'PARKING', 'REF-C3-PARK-14'),
(2.3, 12, 3, NULL, 'PARKING', 'REF-C3-PARK-15'),
(2.3, 12, 3, NULL, 'PARKING', 'REF-C3-PARK-16'),
(2.3, 12, 3, NULL, 'PARKING', 'REF-C3-PARK-17'),
(2.3, 12, 3, NULL, 'PARKING', 'REF-C3-PARK-18'),
(2.3, 12, 3, NULL, 'PARKING', 'REF-C3-PARK-19'),
(2.3, 12, 3, NULL, 'PARKING', 'REF-C3-PARK-20'),
(2.3, 12, 3, NULL, 'PARKING', 'REF-C3-PARK-21'),
(2.3, 12, 3, NULL, 'PARKING', 'REF-C3-PARK-22'),
(2.3, 12, 3, NULL, 'PARKING', 'REF-C3-PARK-23'),
(2.3, 12, 3, NULL, 'PARKING', 'REF-C3-PARK-24'),

-- Storages
(1.34, 6, 3, NULL, 'STORAGEROOM', 'REF-C3-STORE-1'),
(1.34, 6, 3, NULL, 'STORAGEROOM', 'REF-C3-STORE-2'),
(1.34, 6, 3, NULL, 'STORAGEROOM', 'REF-C3-STORE-3'),
(1.34, 6, 3, NULL, 'STORAGEROOM', 'REF-C3-STORE-4'),
(1.34, 6, 3, NULL, 'STORAGEROOM', 'REF-C3-STORE-5'),
(1.34, 6, 3, NULL, 'STORAGEROOM', 'REF-C3-STORE-6'),
(1.34, 6, 3, NULL, 'STORAGEROOM', 'REF-C3-STORE-7'),
(1.34, 6, 3, NULL, 'STORAGEROOM', 'REF-C3-STORE-8'),
(1.34, 6, 3, NULL, 'STORAGEROOM', 'REF-C3-STORE-9'),
(1.34, 6, 3, NULL, 'STORAGEROOM', 'REF-C3-STORE-10'),
(1.34, 6, 3, NULL, 'STORAGEROOM', 'REF-C3-STORE-11'),
(1.34, 6, 3, NULL, 'STORAGEROOM', 'REF-C3-STORE-12'),
(1.34, 6, 3, NULL, 'STORAGEROOM', 'REF-C3-STORE-13'),
(1.34, 6, 3, NULL, 'STORAGEROOM', 'REF-C3-STORE-14'),
(1.34, 6, 3, NULL, 'STORAGEROOM', 'REF-C3-STORE-15'),
(1.34, 6, 3, NULL, 'STORAGEROOM', 'REF-C3-STORE-16'),
(1.34, 6, 3, NULL, 'STORAGEROOM', 'REF-C3-STORE-17'),
(1.34, 6, 3, NULL, 'STORAGEROOM', 'REF-C3-STORE-18'),
(1.34, 6, 3, NULL, 'STORAGEROOM', 'REF-C3-STORE-19'),
(1.34, 6, 3, NULL, 'STORAGEROOM', 'REF-C3-STORE-20'),
(1.34, 6, 3, NULL, 'STORAGEROOM', 'REF-C3-STORE-21'),
(1.34, 6, 3, NULL, 'STORAGEROOM', 'REF-C3-STORE-22'),
(1.34, 6, 3, NULL, 'STORAGEROOM', 'REF-C3-STORE-23'),
(1.34, 6, 3, NULL, 'STORAGEROOM', 'REF-C3-STORE-24');

-- Asignaciones Comunidad 3: propietario del 12 al 19
UPDATE property SET owner_id = 12 WHERE cadastral_reference IN (
  'REF-C3-P1-A', 'REF-C3-P2-A', 'REF-C3-P3-A', 'REF-C3-PARK-1', 'REF-C3-PARK-2', 'REF-C3-STORE-1', 'REF-C3-STORE-2'
);
UPDATE property SET owner_id = 13 WHERE cadastral_reference IN (
  'REF-C3-P1-B', 'REF-C3-P2-B', 'REF-C3-P3-B', 'REF-C3-P4-B', 'REF-C3-PARK-3', 'REF-C3-PARK-4', 'REF-C3-STORE-3'
);
UPDATE property SET owner_id = 14 WHERE cadastral_reference IN (
  'REF-C3-P1-C', 'REF-C3-P4-C', 'REF-C3-P5-C', 'REF-C3-PARK-5', 'REF-C3-PARK-6', 'REF-C3-STORE-4', 'REF-C3-STORE-5', 'REF-C3-STORE-6'
);
UPDATE property SET owner_id = 15 WHERE cadastral_reference IN (
  'REF-C3-P1-D', 'REF-C3-P2-D', 'REF-C3-P3-D', 'REF-C3-P4-D', 'REF-C3-PARK-7', 'REF-C3-PARK-8', 'REF-C3-STORE-7', 'REF-C3-STORE-8'
);
UPDATE property SET owner_id = 16 WHERE cadastral_reference IN (
  'REF-C3-P4-A', 'REF-C3-P5-A', 'REF-C3-P6-A', 'REF-C3-PARK-9', 'REF-C3-PARK-10', 'REF-C3-STORE-9', 'REF-C3-STORE-10'
);
UPDATE property SET owner_id = 17 WHERE cadastral_reference IN (
  'REF-C3-P2-C', 'REF-C3-P3-C', 'REF-C3-P5-B', 'REF-C3-P6-B', 'REF-C3-PARK-11', 'REF-C3-PARK-12', 'REF-C3-STORE-11', 'REF-C3-STORE-12'
);
UPDATE property SET owner_id = 18 WHERE cadastral_reference IN (
  'REF-C3-P5-D', 'REF-C3-P6-D', 'REF-C3-PARK-13', 'REF-C3-PARK-14', 'REF-C3-STORE-13', 'REF-C3-STORE-14', 'REF-C3-STORE-15'
);
UPDATE property SET owner_id = 19 WHERE cadastral_reference IN (
  'REF-C3-P6-C', 'REF-C3-PARK-15', 'REF-C3-PARK-16', 'REF-C3-PARK-17', 'REF-C3-PARK-18', 'REF-C3-PARK-19', 'REF-C3-PARK-20',
  'REF-C3-PARK-21', 'REF-C3-PARK-22', 'REF-C3-PARK-23', 'REF-C3-PARK-24',
  'REF-C3-STORE-16', 'REF-C3-STORE-17', 'REF-C3-STORE-18', 'REF-C3-STORE-19', 'REF-C3-STORE-20', 'REF-C3-STORE-21', 'REF-C3-STORE-22', 'REF-C3-STORE-23', 'REF-C3-STORE-24'
);

-- Flats Comunidad 3
INSERT INTO flat (id, floor_number, letter, room_count, bathroom_count) VALUES
((SELECT id FROM property WHERE cadastral_reference = 'REF-C3-P1-A'), 1, 'A', 3, 2),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C3-P1-B'), 1, 'B', 2, 1),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C3-P1-C'), 1, 'C', 4, 2),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C3-P1-D'), 1, 'D', 3, 2),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C3-P2-A'), 2, 'A', 2, 1),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C3-P2-B'), 2, 'B', 3, 2),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C3-P2-C'), 2, 'C', 4, 2),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C3-P2-D'), 2, 'D', 3, 1),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C3-P3-A'), 3, 'A', 4, 2),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C3-P3-B'), 3, 'B', 2, 1),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C3-P3-C'), 3, 'C', 3, 2),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C3-P3-D'), 3, 'D', 4, 2),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C3-P4-A'), 4, 'A', 2, 1),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C3-P4-B'), 4, 'B', 4, 2),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C3-P4-C'), 4, 'C', 3, 1),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C3-P4-D'), 4, 'D', 5, 2),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C3-P5-A'), 5, 'A', 3, 2),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C3-P5-B'), 5, 'B', 2, 1),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C3-P5-C'), 5, 'C', 4, 2),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C3-P5-D'), 5, 'D', 3, 1),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C3-P6-A'), 6, 'A', 4, 2),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C3-P6-B'), 6, 'B', 2, 1),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C3-P6-C'), 6, 'C', 3, 2),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C3-P6-D'), 6, 'D', 4, 2);


-- Parkings Comunidad 3
INSERT INTO parking (id, num) VALUES
((SELECT id FROM property WHERE cadastral_reference = 'REF-C3-PARK-1'), 1),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C3-PARK-2'), 2),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C3-PARK-3'), 3),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C3-PARK-4'), 4),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C3-PARK-5'), 5),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C3-PARK-6'), 6),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C3-PARK-7'), 7),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C3-PARK-8'), 8),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C3-PARK-9'), 9),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C3-PARK-10'), 10),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C3-PARK-11'), 11),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C3-PARK-12'), 12),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C3-PARK-13'), 13),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C3-PARK-14'), 14),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C3-PARK-15'), 15),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C3-PARK-16'), 16),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C3-PARK-17'), 17),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C3-PARK-18'), 18),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C3-PARK-19'), 19),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C3-PARK-20'), 20),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C3-PARK-21'), 21),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C3-PARK-22'), 22),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C3-PARK-23'), 23),
((SELECT id FROM property WHERE cadastral_reference = 'REF-C3-PARK-24'), 24);



--FACTURAS

INSERT INTO invoice (
    date,
    electricity,
    elevator,
    maintenance,
    trash,
    water,
    community_id
) VALUES
('2025-01-01', 145.30, 65.00, 210.50, 43.10, 90.20, 1),
('2025-01-15', 147.80, 66.20, 215.00, 44.50, 92.10, 2),
('2025-02-01', 150.25, 64.50, 218.75, 45.00, 93.30, 1),
('2025-02-15', 143.90, 68.10, 205.20, 42.80, 89.60, 2),
('2025-03-01', 148.10, 67.80, 212.00, 44.00, 91.00, 1),
('2025-03-15', 151.60, 66.50, 219.90, 45.90, 94.40, 2),
('2025-04-01', 146.70, 65.80, 211.30, 43.80, 90.90, 1),
('2025-04-15', 149.50, 67.20, 216.40, 44.70, 92.70, 2),
('2025-05-01', 152.80, 68.00, 220.10, 46.20, 95.20, 1),
('2025-06-01', 130.80, 89.71, 330.10, 42.20, 103.20, 1),
('2025-05-15', 144.20, 66.90, 208.60, 43.30, 90.50, 2);
