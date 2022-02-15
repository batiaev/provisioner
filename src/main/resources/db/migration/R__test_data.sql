DELETE FROM virtual_machines    WHERE id = 'a109c8c5-1111-1111-1111-a2a8b280f527';
DELETE FROM users        WHERE id = 'c8fe58ed-1111-1111-1111-021aafbb0e48';

INSERT INTO users
VALUES ('c8fe58ed-1111-1111-1111-021aafbb0e48', 'Anton', 'anton@batiaev.com', '+447555555555', 'Password123!', 'MASTER');

INSERT INTO virtual_machines
VALUES ('a109c8c5-1111-1111-1111-a2a8b280f527',
        'c8fe58ed-1111-1111-1111-021aafbb0e48',
        'MACOS 11.6.2', 4294967296, 'SDD 137438953472', 4);
