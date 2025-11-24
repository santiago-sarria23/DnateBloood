-- Creación de la base de datos
CREATE DATABASE DonateBlood;
USE DonateBlood;

-- Tabla de donantes
CREATE TABLE Donante (
    id_donante INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    direccion VARCHAR(150),
    telefono VARCHAR(20),
    tipo_sangre VARCHAR(5) NOT NULL
);


-- Tabla de donaciones
CREATE TABLE Donacion (
    id_donacion INT AUTO_INCREMENT PRIMARY KEY,
    tipo_sangre VARCHAR(5) NOT NULL,
    fecha DATE NOT NULL,
    id_donante INT NOT NULL,
    FOREIGN KEY (id_donante) REFERENCES Donante(id_donante)
        ON UPDATE CASCADE ON DELETE CASCADE
);


-- Tabla de sangre
CREATE TABLE Sangre (
    id_sangre INT AUTO_INCREMENT PRIMARY KEY,
    tipo_sangre VARCHAR(5) NOT NULL,
    id_donante INT,
    FOREIGN KEY (id_donante) REFERENCES Donante(id_donante)
        ON UPDATE CASCADE ON DELETE SET NULL
);


-- Tabla de Hospitales
CREATE TABLE Hospital (
    id_hospital INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    direccion VARCHAR(150),
    telefono VARCHAR(20)
);


-- Tabla de pacientes
CREATE TABLE Paciente (
    id_paciente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    direccion VARCHAR(150),
    tipo_sangre VARCHAR(5) NOT NULL,
    telefono VARCHAR(20)
);


-- Tabla de transfusiones
CREATE TABLE Transfusion (
    id_transfusion INT AUTO_INCREMENT PRIMARY KEY,
    id_paciente INT NOT NULL,
    id_donante INT NOT NULL,
    id_donacion INT NOT NULL,
    FOREIGN KEY (id_paciente) REFERENCES Paciente(id_paciente)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (id_donante) REFERENCES Donante(id_donante)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (id_donacion) REFERENCES Donacion(id_donacion)
        ON UPDATE CASCADE ON DELETE CASCADE
);


-- Tabla de citas
CREATE TABLE Cita (
    id_cita INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE NOT NULL,
    lugar VARCHAR(100),
    id_transfusion INT,
    FOREIGN KEY (id_transfusion) REFERENCES Transfusion(id_transfusion)
        ON UPDATE CASCADE ON DELETE SET NULL
);


-- Tabla de Banco de Sangre
CREATE TABLE Banco_Sangre (
    id_banco_sangre INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    ubicacion VARCHAR(150),
    sangre_disponible INT,
    FOREIGN KEY (sangre_disponible) REFERENCES Sangre(id_sangre)
        ON UPDATE CASCADE ON DELETE SET NULL
);


-- Tabla de encargos
CREATE TABLE Encargos (
    id_encargo INT AUTO_INCREMENT PRIMARY KEY,
    cantidad INT NOT NULL CHECK (cantidad > 0),
    tipo_sangre VARCHAR(5) NOT NULL,
    destino VARCHAR(100),
    origen VARCHAR(100),
    estado VARCHAR(50) DEFAULT 'Pendiente'
);


-- Insersión de datos de pruebas
INSERT INTO Donante (nombre, direccion, telefono, tipo_sangre) VALUES
('Carlos Pérez', 'Calle 10 #5-20', '3001234567', 'O+'),
('Ana Gómez', 'Av. Libertad 25', '3019876543', 'A+'),
('Luis Torres', 'Cra 7 #20-45', '3024567890', 'B-');

INSERT INTO Donacion (tipo_sangre, fecha, id_donante) VALUES
('O+', '2025-10-01', 1),
('A+', '2025-10-03', 2),
('B-', '2025-10-05', 3);

INSERT INTO Sangre (tipo_sangre, id_donante) VALUES
('O+', 1),
('A+', 2),
('B-', 3);

INSERT INTO Hospital (nombre, direccion, telefono) VALUES
('Hospital Central', 'Cra 12 #45-30', '3051122334'),
('Clínica del Mar', 'Calle 22 #10-15', '3042233445'),
('San José', 'Av. Bolívar 55', '3033344556');

INSERT INTO Paciente (nombre, direccion, tipo_sangre, telefono) VALUES
('María López', 'Calle 9 #14-20', 'O+', '3109988776'),
('Pedro Díaz', 'Av. 3 #6-25', 'A+', '3118877665'),
('Sofía Ríos', 'Cra 45 #22-10', 'B-', '3127766554');

INSERT INTO Transfusion (id_paciente, id_donante, id_donacion) VALUES
(1, 1, 1),
(2, 2, 2),
(3, 3, 3);

INSERT INTO Cita (fecha, lugar, id_transfusion) VALUES
('2025-10-10', 'Hospital Central', 1),
('2025-10-12', 'Clínica del Mar', 2),
('2025-10-14', 'San José', 3);

INSERT INTO Banco_Sangre (nombre, ubicacion, sangre_disponible) VALUES
('Banco Nacional', 'Centro', 1),
('Banco Vida', 'Norte', 2),
('Banco Esperanza', 'Sur', 3);

INSERT INTO Encargos (cantidad, tipo_sangre, destino, origen, estado) VALUES
(2, 'O+', 'Hospital Central', 'Banco Nacional', 'Completado'),
(3, 'A+', 'Clínica del Mar', 'Banco Vida', 'En proceso'),
(1, 'B-', 'San José', 'Banco Esperanza', 'Pendiente');


-- Consultas para validación
-- Mostrar todas las donaciones con datos del donante
SELECT d.id_donacion, d.fecha, dn.nombre AS Donante, dn.tipo_sangre
FROM Donacion d
JOIN Donante dn ON d.id_donante = dn.id_donante;

-- Mostrar pacientes con sus transfusiones y donantes asociados
SELECT p.nombre AS Paciente, dn.nombre AS Donante, d.tipo_sangre, d.fecha
FROM Transfusion t
JOIN Paciente p ON t.id_paciente = p.id_paciente
JOIN Donante dn ON t.id_donante = dn.id_donante
JOIN Donacion d ON t.id_donacion = d.id_donacion;

-- Consultar bancos de sangre y su disponibilidad
SELECT bs.nombre AS Banco, s.tipo_sangre
FROM Banco_Sangre bs
JOIN Sangre s ON bs.sangre_disponible = s.id_sangre;

INSERT INTO Cita (fecha, lugar, id_transfusion)
SELECT
    DATE_ADD('2023-01-01', INTERVAL FLOOR(RAND()*730) DAY), -- Rango de dos años (aproximadamente)
    ELT(FLOOR(RAND()*3)+1, 'Hospital Central', 'Clínica del Mar', 'San José'),
    FLOOR(1 + RAND()*3)
FROM
(
    SELECT 1 FROM
        (SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5
         UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9 UNION SELECT 10) t1,
        (SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5
         UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9 UNION SELECT 10) t2,
        (SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5
         UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9 UNION SELECT 10) t3
) AS generator
LIMIT 1000;


CREATE VIEW vista_citas_completas AS
SELECT DISTINCT
    c.id_cita,
    c.fecha AS Fecha_Cita,
    c.lugar AS Lugar_Cita,
    t.id_transfusion,
    p.nombre AS Nombre_Paciente,
    p.tipo_sangre AS Tipo_Sangre_Paciente,
    d.nombre AS Nombre_Donante,
    d.tipo_sangre AS Tipo_Sangre_Donante
FROM Cita c
JOIN Transfusion t ON c.id_transfusion = t.id_transfusion
JOIN Paciente p ON t.id_paciente = p.id_paciente
JOIN Donante d ON t.id_donante = d.id_donante;

SELECT * FROM vista_citas_completas;





