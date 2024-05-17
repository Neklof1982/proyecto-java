DROP DATABASE IF EXISTS Proyecto;
CREATE DATABASE IF NOT EXISTS Proyecto
    CHARACTER SET utf8 COLLATE utf8_spanish2_ci;
USE Proyecto;

-- Tablas

DROP TABLE IF EXISTS notas;
DROP TABLE IF EXISTS usuario;

-- Creamos la tabla usuario
CREATE TABLE usuario (
                         id_usuario INT AUTO_INCREMENT,
                         id_nombre VARCHAR(30) NOT NULL ,
                         apellidos VARCHAR(50) NOT NULL,
                         contrasenia VARCHAR(255) NOT NULL,
                         PRIMARY KEY (id_nombre),
                         Unique (id_usuario)
);

-- Creamos la tabla notas
CREATE TABLE notas (
                       id_nota INT AUTO_INCREMENT PRIMARY KEY,
                       titulo VARCHAR(30) NOT NULL DEFAULT 'Nota Nueva',
                       fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       contenido TEXT NULL,
                       tamanioTexto INT NOT NULL ,
                       id_nombreFK VARCHAR(30) NOT NULL,
                       FOREIGN KEY (id_nombreFK) REFERENCES usuario(id_nombre)
);

-- Creamos la tabla de registro de actividad
CREATE TABLE registro_actividad (
                                    id_actividad INT AUTO_INCREMENT PRIMARY KEY,
                                    id_nombreFK VARCHAR(30) NOT NULL,
                                    accion VARCHAR(100),
                                    fecha_hora TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                    FOREIGN KEY (id_nombreFK) REFERENCES usuario(id_nombre)
);

alter table notas add constraint fk_nombreN foreign key (id_nombreFK)
    references usuario (id_nombre) on delete cascade on update cascade;

alter table registro_actividad  add constraint fk_nombreRA foreign key (id_nombreFK)
    references usuario (id_nombre) on delete cascade on update cascade;

-- TRIGGERS --

DROP TRIGGER IF EXISTS set_default_contenido

DELIMITER //
CREATE TRIGGER set_default_contenido
    BEFORE INSERT ON notas
    FOR EACH ROW
BEGIN
    IF NEW.contenido IS NULL THEN
        SET NEW.contenido = '';
    END IF;
END;
//
DELIMITER ;


DROP TRIGGER IF EXISTS delete_all_notas  -- Revisar con procedure --

DELIMITER //
CREATE TRIGGER delete_all_notas
    BEFORE DELETE ON usuario
    FOR EACH ROW
BEGIN
    DELETE FROM notas WHERE id_nombreFK = OLD.id_nombre;
END;
//
DELIMITER ;

-- TRIGGERS DE REGISTRO ACTIVIDAD --

-- USUARIO --

DROP TRIGGER IF EXISTS insert_nuevo_usuario_registro_actividad

DELIMITER //
CREATE TRIGGER insert_nuevo_usuario_registro_actividad
    AFTER INSERT ON usuario
    FOR EACH ROW
BEGIN
    INSERT INTO registro_actividad (id_nombreFK, accion)
    VALUES (NEW.id_nombre, CONCAT('Se insertó un nuevo usuario con ID: ', NEW.id_usuario));
END;
//
DELIMITER ;

DROP TRIGGER IF EXISTS delete_usuario_registro_actividad

DELIMITER //
CREATE TRIGGER delete_usuario_registro_actividad
    BEFORE DELETE ON usuario
    FOR EACH ROW
BEGIN
    INSERT INTO registro_actividad (id_nombreFK, accion)
    VALUES (OLD.id_nombre, CONCAT('Se eliminó el usuario con ID: ', OLD.id_usuario));
END;
//
DELIMITER ;

DROP TRIGGER IF EXISTS update_nombre_usuario_registro_actividad;

DELIMITER //
CREATE TRIGGER update_nombre_usuario_registro_actividad
    AFTER UPDATE ON usuario
    FOR EACH ROW
BEGIN
    IF OLD.id_nombre != NEW.id_nombre THEN
        INSERT INTO registro_actividad (id_nombreFK, accion)
        VALUES (NEW.id_nombre, CONCAT('Se actualizó el nombre de usuario con ID: ', NEW.id_usuario));
    END IF;
END;
//
DELIMITER ;


DROP TRIGGER IF EXISTS update_apellidos_registro_actividad;

DELIMITER //
CREATE TRIGGER update_apellidos_registro_actividad
    AFTER UPDATE ON usuario
    FOR EACH ROW
BEGIN
    IF OLD.apellidos != NEW.apellidos THEN
        INSERT INTO registro_actividad (id_nombreFK, accion)
        VALUES (NEW.id_nombre, CONCAT('Se actualizó los apellidos del usuario con ID: ', NEW.id_usuario));
    END IF;
END;
//
DELIMITER ;

DROP TRIGGER IF EXISTS update_contrasenia_registro_actividad;

DELIMITER //
CREATE TRIGGER update_contrasenia_registro_actividad
    AFTER UPDATE ON usuario
    FOR EACH ROW
BEGIN
    IF OLD.contrasenia != NEW.contrasenia THEN
        INSERT INTO registro_actividad (id_nombreFK, accion)
        VALUES (NEW.id_nombre, CONCAT('Se actualizó la contraseña del usuario con ID: ', NEW.id_usuario));
    END IF;
END;
//
DELIMITER ;

-- NOTAS --

DROP TRIGGER IF EXISTS insert_nota_registro_actividad

DELIMITER //
CREATE TRIGGER insert_nota_registro_actividad
    AFTER INSERT ON notas
    FOR EACH ROW
BEGIN
    INSERT INTO registro_actividad (id_nombreFK, accion)
    VALUES (NEW.id_nombreFK, CONCAT('Se insertó una nueva nota con ID: ', NEW.id_nota));
END;
//
DELIMITER ;

DROP TRIGGER IF EXISTS update_nota_registro_actividad

DELIMITER //
CREATE TRIGGER update_nota_registro_actividad
    AFTER UPDATE ON notas
    FOR EACH ROW
BEGIN
    INSERT INTO registro_actividad (id_nombreFK, accion)
    VALUES (NEW.id_nombreFK, CONCAT('Se actualizó la nota con ID: ', NEW.id_nota));
END;
//
DELIMITER ;

DROP TRIGGER IF EXISTS delete_nota_registro_actividad

DELIMITER //
CREATE TRIGGER delete_nota_registro_actividad
    AFTER DELETE ON notas
    FOR EACH ROW
BEGIN
    INSERT INTO registro_actividad (id_nombreFK, accion)
    VALUES (OLD.id_nombreFK, CONCAT('Se eliminó la nota con ID: ', OLD.id_nota));
END;
//
DELIMITER ;

DROP TRIGGER IF EXISTS update_titulo_nota_registro_actividad;

DELIMITER //
CREATE TRIGGER update_titulo_nota_registro_actividad
    AFTER UPDATE ON notas
    FOR EACH ROW
BEGIN
    IF OLD.titulo != NEW.titulo THEN
        INSERT INTO registro_actividad (id_nombreFK, accion)
        VALUES (NEW.id_nombreFK, CONCAT('Se actualizó el título de la nota con ID: ', NEW.id_nota));
    END IF;
END;
//
DELIMITER ;


DROP TRIGGER IF EXISTS delete_titulo_nota_registro_actividad;

DELIMITER //
CREATE TRIGGER delete_titulo_nota_registro_actividad
    AFTER UPDATE ON notas
    FOR EACH ROW
BEGIN
    IF OLD.titulo IS NOT NULL AND NEW.titulo IS NULL THEN
        INSERT INTO registro_actividad (id_nombreFK, accion)
        VALUES (NEW.id_nombreFK, CONCAT('Se eliminó el título de la nota con ID: ', NEW.id_nota));
    END IF;
END;
//
DELIMITER ;

DROP TRIGGER IF EXISTS update_contenido_nota_registro_actividad;

DELIMITER //
CREATE TRIGGER update_contenido_nota_registro_actividad
    AFTER UPDATE ON notas
    FOR EACH ROW
BEGIN
    IF OLD.contenido != NEW.contenido THEN
        INSERT INTO registro_actividad (id_nombreFK, accion)
        VALUES (NEW.id_nombreFK, CONCAT('Se actualizó el contenido de la nota con ID: ', NEW.id_nota));
    END IF;
END;
//
DELIMITER ;

DROP TRIGGER IF EXISTS delete_contenido_nota_registro_actividad;
DELIMITER //

CREATE TRIGGER delete_contenido_nota_registro_actividad

    AFTER UPDATE ON notas
    FOR EACH ROW
BEGIN
    IF OLD.contenido IS NOT NULL AND NEW.contenido IS NULL THEN
        INSERT INTO registro_actividad (id_nombreFK, accion)
        VALUES (NEW.id_nombreFK, CONCAT('Se eliminó el contenido de la nota con ID: ', NEW.id_nota));
    END IF;
END;
//
DELIMITER ;


-- Insertamos datos en la tabla usuario
INSERT INTO usuario (id_nombre, apellidos, contrasenia)
VALUES
    ('Usuario1', 'Apellidos1', 'Contraseña1'),
    ('Usuario2', 'Apellidos2', 'Contraseña2'),
    ('Usuario3', 'Apellidos2', 'Contraseña3'),
    ('alvaro', 'martin', '123');


-- Insertamos datos en la tabla notas
INSERT INTO notas (titulo, contenido,tamanioTexto,id_nombreFK)
VALUES
    ('nota alvaro', 'esta es la nota de alvaro', 20 ,'alvaro'),
    ('Titulo1', 'Texto1', 20 ,'Usuario1'),
    (DEFAULT, 'Texto2',20,'Usuario2'),
    ('Titulo3', NULL,20,'Usuario3');


-- Seleccionamos todos los registros de las tablas
SELECT * FROM usuario;
SELECT * FROM notas;
SELECT * FROM registro_actividad;

delete from usuario where id_nombre = 'Usuario1';

SELECT * FROM usuario;
SELECT * FROM notas;
SELECT * FROM registro_actividad;