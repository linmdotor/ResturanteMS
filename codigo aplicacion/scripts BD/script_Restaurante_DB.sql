-- Generación de las tablas de la base de datos de Restaurante2013
-- Se puede añadir ENGINE=InnoDB; al final de cada tabla para que funcioenes las constraints de las relacioens

-- mySQL 



-- --------------------------------------------------------
--
-- Base de Datos Restaurante_DB
--
DROP DATABASE IF EXISTS Restaurante_DB;
CREATE DATABASE Restaurante_DB;
USE Restaurante_DB;



-- --------------------------------------------------------
--
-- Estructura de la tabla Plato
--
DROP TABLE IF EXISTS `Plato`;
CREATE TABLE IF NOT EXISTS `Plato`(
	`ID_Plato` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT, 
	`Nombre` VARCHAR(30) NOT NULL, 
	`Precio` FLOAT, 
	`Stock` INTEGER NOT NULL DEFAULT '0',
	
	PRIMARY KEY (ID_Plato)	
) ENGINE=INNODB;

--
-- Inserción de datos para la tabla `Plato`
--
INSERT INTO `Plato` (`Nombre`, `Precio`, `Stock`) VALUES
('Pan', 1.2, 25),
('Aceitunas', 2.0, 15),
('Sopa de Ajo', 4.5, 10),
('Patatas Asadas', 6.0, 15),
('Ensalada Mixta', 5.20, 3),
('Filete de Ternera', 8.0, 5),
('Cocido', 12.10, 10),
('Lubina al Horno', 9.95, 8),
('Tarta', 3.5, 10),
('Flan', 93.5, 10),

('Agua', 1.25, 20),
('Coca Cola', 2.0, 10),
('Fanta', 2.0, 10),
('Zumo Naranja', 1.60, 15),
('Café con Leche', 1.5, 10),
('Café Solo', 1.0, 10),
('Vino de la Casa', 1.45, 13),
('Cerveza', 2.2, 10),
('Cubata', 4.5, 10),
('Chupito', 1.5, 25);

-- --------------------------------------------------------
--
-- Estructura de la tabla Plato_Comida
--
DROP TABLE IF EXISTS `Plato_Comida`;
CREATE TABLE IF NOT EXISTS `Plato_Comida`(
	`ID_Plato_Comida` INTEGER UNSIGNED NOT NULL,
	`Tipo` ENUM('Entrante', 'Primero', 'Segundo', 'Postre'),
	
	PRIMARY KEY (`ID_Plato_Comida`), 
	FOREIGN KEY (`ID_Plato_Comida`) REFERENCES `Plato`(`ID_Plato`) ON DELETE CASCADE
) ENGINE=INNODB;

--
-- Inserción de datos para la tabla `Plato`
--
INSERT INTO `Plato_Comida` (`ID_Plato_Comida`, `Tipo`) VALUES
(1, 'Entrante'),
(2, 'Entrante'),
(3, 'Primero'),
(4, 'Primero'),
(5, 'Primero'),
(6, 'Segundo'),
(7, 'Segundo'),
(8, 'Segundo'),
(9, 'Postre'),
(10, 'Postre');

-- --------------------------------------------------------
--
-- Estructura de la tabla Plato_Bebida
--
DROP TABLE IF EXISTS `Plato_Bebida`;
CREATE TABLE IF NOT EXISTS `Plato_Bebida`(
	`ID_Plato_Bebida` INTEGER UNSIGNED NOT NULL,
	`Alcoholica` TINYINT NOT NULL DEFAULT '0' , -- True=0 ; False=1
	
    	PRIMARY KEY (`ID_Plato_Bebida`), 
	FOREIGN KEY (`ID_Plato_Bebida`) REFERENCES `Plato`(`ID_Plato`) ON DELETE CASCADE
) ENGINE=INNODB;

--
-- Inserción de datos para la tabla `Plato`
--
INSERT INTO `Plato_Bebida` (`ID_Plato_Bebida`, `Alcoholica`) VALUES
(11, 0),
(12, 0),
(13, 0),
(14, 0),
(15, 0),
(16, 0),
(17, 1),
(18, 1),
(19, 1),
(20, 1);

-- --------------------------------------------------------
--
-- Estructura de la tabla Reserva
--
DROP TABLE IF EXISTS `Reserva`;
CREATE TABLE IF NOT EXISTS `Reserva`(
	`ID_Reserva` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT, 
	`DNI` VARCHAR(10), 
	`Nombre` VARCHAR(25) NOT NULL, 
	`Fecha` DATE NOT NULL,
	`Hora` TIME NOT NULL,
	`Telefono` VARCHAR(10), 
	`N_Comensales` SMALLINT UNSIGNED NOT NULL DEFAULT 2, 
	
	PRIMARY KEY (`ID_Reserva`)
) ENGINE=INNODB;

--
-- Inserción de datos para la tabla `Reserva`
--
INSERT INTO `Reserva` (`DNI`, `Nombre`, `Fecha`, `Hora`, `Telefono`, `N_Comensales`) VALUES
('50228223H', 'Pepe', '2013-10-15', '20:30:00', '91469658', 2),
('51246322E', 'Juan', '2013-10-25', '19:00:00', '', 8),
('', 'Alberto', '2013-12-31', '21:30:00', '', 2),
('50326235G', 'Juan', '2013-11-12', '19:30:00', '', 6),
('', 'Pepe', '2013-11-12', '20:00:00', '655424242', 2),
('', 'Rigoberto', '2013-12-12', '18:00:00', '655342150', 2);

-- --------------------------------------------------------
--
-- Estructura de la tabla Factura
--
DROP TABLE IF EXISTS `Factura`;
CREATE TABLE IF NOT EXISTS `Factura`(
	`ID_Factura` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT, 
	`ID_Reserva` INTEGER UNSIGNED REFERENCES `Reserva`(`ID_Reserva`), 
	`Fecha` DATE NOT NULL,
	`Hora` TIME NOT NULL, 
	`NIF_Empresa` VARCHAR(10) NOT NULL DEFAULT '51321321',
	`Nombre_Empresa` VARCHAR(25) NOT NULL DEFAULT 'Restaurante 2013',
	`Dir_Empresa` VARCHAR(50) NOT NULL DEFAULT 'Calle Falsa, 123',
	`NIF_Cliente` VARCHAR(10), 
	`Nombre_Cliente` VARCHAR(25), 
	`Dir_Cliente` VARCHAR(50), 
	`IVA` TINYINT NOT NULL DEFAULT '10', 
	`Tipo_Servicio` VARCHAR(20) NOT NULL DEFAULT 'Restauración', 

    	PRIMARY KEY (`ID_Factura`),
	FOREIGN KEY (`ID_Reserva`) REFERENCES `Reserva`(`ID_Reserva`) ON DELETE SET NULL
) ENGINE=INNODB;

--
-- Inserción de datos para la tabla `Plato`
--
INSERT INTO `Factura` (`ID_Reserva`, `Fecha`, `Hora`) VALUES
(1, '2013-10-15', '22:35:12'),
(1, '2013-10-15', '22:36:50'),
(1, '2013-10-15', '22:37:12'),
(2, '2013-10-25', '20:35:18'),
(4, '2013-11-12', '21:36:50'),
(5, '2013-11-12', '23:45:02');

-- --------------------------------------------------------
--
-- Estructura de la tabla Factura_Plato
--
DROP TABLE IF EXISTS `Factura_Plato`;
CREATE TABLE IF NOT EXISTS `Factura_Plato`(
	`ID_Factura` INTEGER UNSIGNED NOT NULL REFERENCES `Factura`(`ID_Factura`), 
	`ID_Plato` INTEGER UNSIGNED NOT NULL REFERENCES `Plato`(`ID_Plato`), 
	`Precio` FLOAT NOT NULL, 
	`Cantidad` TINYINT NOT NULL, 
	
	PRIMARY KEY (`ID_Factura`, `ID_Plato`), 
	FOREIGN KEY (`ID_Factura`) REFERENCES `Factura`(`ID_Factura`) ON DELETE CASCADE, 
	FOREIGN KEY (`ID_Plato`) REFERENCES `Plato`(`ID_Plato`) ON DELETE NO ACTION -- es lo mismo que ON DELETE RESTRICT (no permite la eliminación del padre)
) ENGINE=INNODB;

--
-- Inserción de datos para la tabla `Factura_Plato`
--
INSERT INTO `Factura_Plato` (`ID_Factura`, `ID_Plato`, `Precio`, `Cantidad`) VALUES
(1, 2, 10.20, 4),
(1, 3, 1.00, 2),
(1, 4, 1.45, 2),
(1, 8, 1.0, 1),
(1, 12, 1.45, 8),
(2, 1, 10.2, 3),
(2, 6, 1.0, 1),
(2, 13, 1.45, 2),
(3, 11, 10.2, 2),
(3, 8, 1.0, 2),
(3, 7, 1.45, 1),
(4, 1, 10.2, 1),
(4, 9, 1.0, 1),
(4, 16, 1.45, 2),
(5, 17, 10.2, 1),
(5, 18, 1.0, 2),
(5, 19, 1.45, 2),
(6, 10, 10.20, 2),
(6, 8, 1.00, 2),
(6, 5, 1.25, 1);