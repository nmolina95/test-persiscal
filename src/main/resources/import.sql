INSERT INTO PRODUCTOS (id, nombre, descripcion, precio, cantidad) VALUES (1, 'Computador', 'Macbook', 20.0, 12);
INSERT INTO PRODUCTOS (id, nombre, descripcion, precio, cantidad) VALUES (2, 'Microondas', NULL, 20.0, 12);
INSERT INTO PRODUCTOS (id, nombre, descripcion, precio, cantidad) VALUES (3, 'Televisor', 'Samsung', 20.0, 12);
INSERT INTO PRODUCTOS (id, nombre, descripcion, precio, cantidad) VALUES (4, 'Horno', '', 20.0, 12);

ALTER TABLE PRODUCTOS ALTER COLUMN id RESTART WITH 5;