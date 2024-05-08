DROP DATABASE IF EXISTS Proyecto;
CREATE DATABASE IF NOT EXISTS Proyecto
CHARACTER SET utf8 COLLATE utf8_spanish2_ci;
USE Proyecto;
-- Tablas

DROP TABLE IF EXISTS notas;
DROP TABLE IF EXISTS usuario;

-- Creamos la tabla usuario
CREATE TABLE usuario (
  id_nombre VARCHAR(30) NOT NULL unique,
  apellidos VARCHAR(50) NOT NULL,
  contraseña VARCHAR(30) NOT NULL,
  PRIMARY KEY (id_nombre)
);

-- Creamos la tabla notas
CREATE TABLE notas (
  id_nota INT AUTO_INCREMENT PRIMARY KEY,
  titulo VARCHAR(30) NULL DEFAULT 'Nueva Nota',
  fecha_creacion DATE,
  contenido TEXT NULL,
  id_nombreFK VARCHAR(30) NOT NULL,
  FOREIGN KEY (id_nombreFK) REFERENCES usuario(id_nombre)
);

-- Insertamos datos en la tabla usuario
INSERT INTO usuario (id_nombre, apellidos, contraseña)
VALUES
    ('alvaro', 'Apellidos1', '123'),
    ('oscar', 'Apellidos2', '123'),
    ('juan', 'Apellidos2', '123');

-- Insertamos datos en la tabla notas
INSERT INTO notas (titulo, fecha_creacion, contenido, id_nombreFK)
VALUES
    ('Titulo1', '2024-05-05', 'Hola soy Óscar', 'oscar'),
    (DEFAULT, '2024-05-06', 'Hola soy Álvaro', 'alvaro'),
    ('Titulo3', '2024-05-07', 'hola soy Juan', 'juan');

-- Seleccionamos todos los registros de las tablas
SELECT * FROM usuario;
SELECT * FROM notas;