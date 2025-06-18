-- Resetear secuencias a 1 (inicia el siguiente id desde 1)
-- SELECT setval('bank_account_id_seq', 1, false);
-- SELECT setval('communities_id_seq', 1, false);
-- SELECT setval('invoice_id_seq', 1, false);
-- SELECT setval('owner_id_seq', 1, false);
-- SELECT setval('owner_invoice_id_seq', 1, false);
-- SELECT setval('property_id_seq', 1, false);

-- Insertar comunidades
INSERT INTO communities (elevator, num_flats, num_parkings, num_storage_rooms, reduced_mobility_access,address, postal_code)
VALUES 
    (true, 6, 6, 6, true,  'Calle Pequeña 1', '28010'),
    (false, 4, 4, 4, false, 'Avenida Chica 2', '28020');

-- Insertar cuentas bancarias para las dos comunidades con nombres de bancos reales
INSERT INTO bank_account (balance, account_number, bank_name)
VALUES 
    (5000.00, 'ES1000000001', 'BBVA'),
    (6000.00, 'ES1000000002', 'Santander');

-- Asignar cuenta bancaria a las comunidades ya existentes
UPDATE communities SET bank_account_id = 1 WHERE id = 1; -- Asignar BBVA a la Comunidad 1
UPDATE communities SET bank_account_id = 2 WHERE id = 2; -- Asignar Santander a la Comunidad 2



-- Propiedades Vivienda para Comunidad 1
INSERT INTO property (coefficient, square_meters, community_id, owner_id, property_type, cadastral_reference)
VALUES 
    (0.1, 80.0, 1, NULL, 'FLAT', 'REF-C1-P1-A'),
    (0.1, 75.0, 1, NULL, 'FLAT', 'REF-C1-P1-B'),
    (0.1, 70.0, 1, NULL, 'FLAT', 'REF-C1-P1-C'),
    (0.1, 85.0, 1, NULL, 'FLAT', 'REF-C1-P2-A'),
    (0.1, 90.0, 1, NULL, 'FLAT', 'REF-C1-P2-B'),
    (0.1, 95.0, 1, NULL, 'FLAT', 'REF-C1-P2-C');



-- Propiedades Parking para Comunidad 1
INSERT INTO property (coefficient, square_meters, community_id, owner_id, property_type, cadastral_reference)
VALUES 
    (0.05, 12.0, 1, NULL, 'PARKING', 'REF-C1-PARK-1'),
    (0.05, 12.0, 1, NULL, 'PARKING', 'REF-C1-PARK-2'),
    (0.05, 12.0, 1, NULL, 'PARKING', 'REF-C1-PARK-3'),
    (0.05, 12.0, 1, NULL, 'PARKING', 'REF-C1-PARK-4'),
    (0.05, 12.0, 1, NULL, 'PARKING', 'REF-C1-PARK-5'),
    (0.05, 12.0, 1, NULL, 'PARKING', 'REF-C1-PARK-6');

-- Propiedades Trastero para Comunidad 1
INSERT INTO property (coefficient, square_meters, community_id, owner_id, property_type, cadastral_reference)
VALUES 
    (0.02, 6.0, 1, NULL, 'STORAGEROOM', 'REF-C1-STORE-1'),
    (0.02, 6.0, 1, NULL, 'STORAGEROOM', 'REF-C1-STORE-2'),
    (0.02, 6.0, 1, NULL, 'STORAGEROOM', 'REF-C1-STORE-3'),
    (0.02, 6.0, 1, NULL, 'STORAGEROOM', 'REF-C1-STORE-4'),
    (0.02, 6.0, 1, NULL, 'STORAGEROOM', 'REF-C1-STORE-5'),
    (0.02, 6.0, 1, NULL, 'STORAGEROOM', 'REF-C1-STORE-6');


--Propietartios
INSERT INTO owner (birth_date, bank_account_id, dni, email, name, phone, surname)
VALUES 
    ('1980-01-01', NULL, 'DNI001', 'owner1@correo.com', 'Ana', '600000001', 'García'),
    ('1981-02-02', NULL, 'DNI002', 'owner2@correo.com', 'Luis', '600000002', 'López'),
    ('1982-03-03', NULL, 'DNI003', 'owner3@correo.com', 'Eva', '600000003', 'Martínez'),
    ('1983-04-04', NULL, 'DNI004', 'owner4@correo.com', 'Carlos', '600000004', 'Pérez'),
    ('1984-05-05', NULL, 'DNI005', 'owner5@correo.com', 'Laura', '600000005', 'Ruiz'),
    ('1985-06-06', NULL, 'DNI006', 'owner6@correo.com', 'David', '600000006', 'Sánchez');


UPDATE property SET owner_id = 1 WHERE cadastral_reference IN ('REF-C1-P1-A', 'REF-C1-PARK-1', 'REF-C1-STORE-1');
UPDATE property SET owner_id = 2 WHERE cadastral_reference IN ('REF-C1-P1-B', 'REF-C1-PARK-2', 'REF-C1-STORE-2');
UPDATE property SET owner_id = 3 WHERE cadastral_reference IN ('REF-C1-P1-C', 'REF-C1-PARK-3', 'REF-C1-STORE-3');
UPDATE property SET owner_id = 4 WHERE cadastral_reference IN ('REF-C1-P2-A', 'REF-C1-PARK-4', 'REF-C1-STORE-4');
UPDATE property SET owner_id = 5 WHERE cadastral_reference IN ('REF-C1-P2-B', 'REF-C1-PARK-5', 'REF-C1-STORE-5');
UPDATE property SET owner_id = 6 WHERE cadastral_reference IN ('REF-C1-P2-C', 'REF-C1-PARK-6', 'REF-C1-STORE-6');


--CUENTAS BANCARIAS
INSERT INTO bank_account (balance, account_number, bank_name)
VALUES 
    (2000.00, 'ES2000000001', 'BBVA'),
    (2500.00, 'ES2000000002', 'Santander'),
    (1800.00, 'ES2000000003', 'Sabadell'),
    (2200.00, 'ES2000000004', 'CaixaBank'),
    (3000.00, 'ES2000000005', 'Bankinter'),
    (2700.00, 'ES2000000006', 'Unicaja');


UPDATE owner SET bank_account_id = 3 WHERE id = 1; -- BBVA (id=3) (porque id=1 de bank_account ya se usó para comunidad)
UPDATE owner SET bank_account_id = 4 WHERE id = 2; -- Santander (id=4)
UPDATE owner SET bank_account_id = 5 WHERE id = 3; -- Sabadell (id=5)
UPDATE owner SET bank_account_id = 6 WHERE id = 4; -- CaixaBank (id=6)
UPDATE owner SET bank_account_id = 7 WHERE id = 5; -- Bankinter (id=7)
UPDATE owner SET bank_account_id = 8 WHERE id = 6; -- Unicaja (id=8)



-- Insertar flats (diferenciados por floorNumber, letter, etc.)
INSERT INTO flat (id, floor_number, letter, room_count, bathroom_count)
VALUES 
    ((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-P1-A'), 1, 'A', 3, 1),
    ((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-P1-B'), 1, 'B', 2, 1),
    ((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-P1-C'), 1, 'C', 2, 1),
    ((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-P2-A'), 2, 'A', 4, 2),
    ((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-P2-B'), 2, 'B', 3, 2),
    ((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-P2-C'), 2, 'C', 3, 2);


INSERT INTO parking (id, num)
VALUES 
    ((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-PARK-1'), 1),
    ((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-PARK-2'), 2),
    ((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-PARK-3'), 3),
    ((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-PARK-4'), 4),
    ((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-PARK-5'), 5),
    ((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-PARK-6'), 6);

INSERT INTO storage_room (id, storage_number)
VALUES 
    ((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-STORE-1'), 101),
    ((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-STORE-2'), 102),
    ((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-STORE-3'), 103),
    ((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-STORE-4'), 104),
    ((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-STORE-5'), 105),
    ((SELECT id FROM property WHERE cadastral_reference = 'REF-C1-STORE-6'), 106);


