
DROP SEQUENCE seq_equipos;
DROP SEQUENCE seq_jugadores;
DROP SEQUENCE seq_contratos;

DROP TABLE  jugadores CASCADE ;
DROP TABLE  equipos CASCADE ;
DROP TABLE contratos CASCADE ;


CREATE TABLE equipos
(
    id                VARCHAR(9),
    nombre            VARCHAR(100) ,
    estadio           VARCHAR(100),
    ciudad            VARCHAR(50),
    fundacion         VARCHAR(4),
    presupuesto       NUMERIC(12, 2),
    escudo            VARCHAR(200),

    CONSTRAINT "PK_EQUIPOS" PRIMARY KEY (id),
    CONSTRAINT "UQ_EQUIPOS_NOMBRE" UNIQUE (nombre),
    CONSTRAINT "NN_EQUIPOS_NOMBRE" CHECK (nombre IS NOT NULL ),
    CONSTRAINT "NN_EQUIPOS_ESTADIO" CHECK (estadio IS NOT NULL ),
    CONSTRAINT "NN_EQUIPOS_CIUDAD" CHECK (ciudad IS NOT NULL ),
    CONSTRAINT "NN_EQUIPOS_FUNDACION" CHECK (fundacion IS NOT NULL ),
    CONSTRAINT "NN_EQUIPOS_PRESUPUESTO" CHECK (presupuesto IS NOT NULL ),
    CONSTRAINT "NN_EQUIPOS_ESCUDO" CHECK (escudo IS NOT NULL )
);

CREATE TABLE jugadores
(
    id             VARCHAR(9),
    nombre           VARCHAR(100),
    nacionalidad     VARCHAR(50),
    foto             VARCHAR(200),
    valor_mercado    VARCHAR(10),
    CONSTRAINT "PK_JUGADORES" PRIMARY KEY (id),
    CONSTRAINT "NN_JUGADORES_NOMBRE" CHECK (nombre IS NOT NULL),
    CONSTRAINT "UQ_JUGADORES_NOMBRE" UNIQUE (nombre),
    CONSTRAINT "NN_JUGADORES_NACIONALIDAD" CHECK (nacionalidad IS NOT NULL),
    CONSTRAINT "NN_JUGADORES_VALOR_MERCADO" CHECK (valor_mercado IS NOT NULL)

);

CREATE TABLE contratos
(
    id VARCHAR(9),
    id_jugador VARCHAR(9),
    id_equipo VARCHAR(9),
    fin_contrato VARCHAR(4),
    CONSTRAINT "PK_CONTRATOS" PRIMARY KEY (id),
    CONSTRAINT "FK_CONTRATOS_EQUIPOS"  FOREIGN KEY (id_equipo)  REFERENCES equipos(id),
    CONSTRAINT "FK_CONTRATOS_JUGADORES"  FOREIGN KEY (id_jugador)  REFERENCES jugadores(id),
    CONSTRAINT "NN_FIN_CONTRATO" CHECK ( fin_contrato IS NOT NULL)


);

CREATE SEQUENCE seq_equipos MINVALUE 1 MAXVALUE 999999999 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_jugadores MINVALUE 1 MAXVALUE 999999999 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_contratos MINVALUE 1 MAXVALUE 999999999 START WITH 1 INCREMENT BY 1;


INSERT INTO equipos (id, nombre, estadio, ciudad, fundacion, presupuesto, escudo)
VALUES
    (nextval('seq_equipos'), 'Real Madrid', 'Santiago Bernabéu', 'Madrid', '1902', 800000000.0, 'https://tmssl.akamaized.net//images/wappen/head/418.png?lm=1729684474'),
    (nextval('seq_equipos'), 'FC Barcelona', 'Spotify Camp Nou', 'Barcelona', '1899', 700000000.0, 'https://tmssl.akamaized.net//images/wappen/head/131.png?lm=1406739548'),
    (nextval('seq_equipos'), 'Atlético de Madrid', 'Cívitas Metropolitano', 'Madrid', '1903', 400000000.0, 'https://tmssl.akamaized.net//images/wappen/head/13.png?lm=1719915566'),
    (nextval('seq_equipos'), 'Sevilla FC', 'Ramón Sánchez-Pizjuán', 'Sevilla', '1890', 200000000.0, 'https://tmssl.akamaized.net//images/wappen/head/368.png?lm=1730896593'),
    (nextval('seq_equipos'), 'Real Sociedad', 'Reale Arena', 'San Sebastián', '1909', 180000000.0, 'https://tmssl.akamaized.net//images/wappen/head/681.png?lm=1614795530'),
    (nextval('seq_equipos'), 'Villarreal CF', 'Estadio de la Cerámica', 'Villarreal', '1923', 150000000.0, 'https://tmssl.akamaized.net//images/wappen/head/1050.png?lm=1408655310'),
    (nextval('seq_equipos'), 'Real Betis', 'Benito Villamarín', 'Sevilla', '1907', 160000000.0, 'https://tmssl.akamaized.net//images/wappen/head/150.png?lm=1663358526'),
    (nextval('seq_equipos'), 'Athletic Club', 'San Mamés', 'Bilbao', '1898', 120000000.0, 'https://tmssl.akamaized.net//images/wappen/head/621.png?lm=1695069038'),
    (nextval('seq_equipos'), 'Valencia CF', 'Mestalla', 'Valencia', '1919', 140000000.0, 'https://tmssl.akamaized.net//images/wappen/head/1049.png?lm=1406966320'),
    (nextval('seq_equipos'), 'Celta de Vigo', 'Balaídos', 'Vigo', '1923', 90000000.0, 'https://tmssl.akamaized.net//images/wappen/head/940.png?lm=1406966406');

INSERT INTO jugadores (id, nombre, nacionalidad, foto, valor_mercado)
VALUES
    (nextval('seq_jugadores'), 'Vinicius Junior', 'Brasil', 'https://img.a.transfermarkt.technology/portrait/header/371998-1664869583.jpg?lm=1', '150M €'),
    (nextval('seq_jugadores'), 'Jude Bellingham', 'Inglaterra', 'https://img.a.transfermarkt.technology/portrait/header/581678-1693987944.jpg?lm=1', '180M €'),
    (nextval('seq_jugadores'), 'Robert Lewandowski', 'Polonia', 'https://img.a.transfermarkt.technology/portrait/header/38253-1701118759.jpg?lm=1', '30M €'),
    (nextval('seq_jugadores'), 'Pedri', 'España', 'https://img.a.transfermarkt.technology/portrait/header/683840-1744278342.jpg?lm=1', '100M €'),
    (nextval('seq_jugadores'), 'Antoine Griezmann', 'Francia', 'https://img.a.transfermarkt.technology/portrait/header/125781-1719928503.jpg?lm=1', '25M €'),
    (nextval('seq_jugadores'), 'Jan Oblak', 'Eslovenia', 'https://img.a.transfermarkt.technology/portrait/header/121483-1719350076.jpg?lm=1', '35M €'),
    (nextval('seq_jugadores'), 'Ejuke', 'Nigeria', 'https://img.a.transfermarkt.technology/portrait/header/500067-1706264992.jpg?lm=1', '6M €'),
    (nextval('seq_jugadores'), 'Isaac Romero', 'España', 'https://img.a.transfermarkt.technology/portrait/header/687609-1704970459.jpg?lm=1', '10M €'),
    (nextval('seq_jugadores'), 'Mikel Oyarzabal', 'España', 'https://img.a.transfermarkt.technology/portrait/header/351478-1599987229.jpg?lm=1', '50M €'),
    (nextval('seq_jugadores'), 'Takefusa Kubo', 'Japón', 'https://img.a.transfermarkt.technology/portrait/header/405398-1697110254.jpg?lm=1', '60M €'),
    (nextval('seq_jugadores'), 'Álex Baena', 'España', 'https://img.a.transfermarkt.technology/portrait/header/548111-1714487266.jpg?lm=1', '40M €'),
    (nextval('seq_jugadores'), 'Gerard Moreno', 'España', 'https://img.a.transfermarkt.technology/portrait/header/177467-1726231623.jpg?lm=1', '15M €'),
    (nextval('seq_jugadores'), 'Isco', 'España', 'https://img.a.transfermarkt.technology/portrait/header/85288-1744375744.jpg?lm=1', '10M €'),
    (nextval('seq_jugadores'), 'Cedric Bakambu', 'República Democrática del Congo', 'https://img.a.transfermarkt.technology/portrait/header/127048-1563441766.jpg?lm=1', '8M €'),
    (nextval('seq_jugadores'), 'Nico Williams', 'España', 'https://img.a.transfermarkt.technology/portrait/header/709187-1709676169.png?lm=1', '50M €'),
    (nextval('seq_jugadores'), 'Unai Simón', 'España', 'https://img.a.transfermarkt.technology/portrait/header/262396-1709674901.png?lm=1', '25M €'),
    (nextval('seq_jugadores'), 'Hugo Duro', 'España', 'https://img.a.transfermarkt.technology/portrait/header/573775-1683213249.jpg?lm=1', '15M €'),
    (nextval('seq_jugadores'), 'Jose Gayà', 'España', 'https://img.a.transfermarkt.technology/portrait/header/221322-1605193468.jpg?lm=1', '20M €'),
    (nextval('seq_jugadores'), 'Iago Aspas', 'España', 'https://img.a.transfermarkt.technology/portrait/header/72047-1653042948.jpg?lm=1', '8M €'),
    (nextval('seq_jugadores'), 'Óscar Mingueza', 'España.', 'https://img.a.transfermarkt.technology/portrait/header/331505-1698852962.jpg?lm=1', '20M €');

INSERT INTO contratos (id, id_jugador, id_equipo, fin_contrato)
VALUES
    (nextval('seq_contratos'), '1', '1', '2027'),
    (nextval('seq_contratos'), '2', '1', '2029'),
    (nextval('seq_contratos'), '3', '2', '2026'),
    (nextval('seq_contratos'), '4', '2', '2026'),
    (nextval('seq_contratos'), '5', '3', '2026'),
    (nextval('seq_contratos'), '6', '3', '2028'),
    (nextval('seq_contratos'), '7', '4', '2025'),
    (nextval('seq_contratos'), '8', '4', '2027'),
    (nextval('seq_contratos'), '9', '5', '2028'),
    (nextval('seq_contratos'), '10', '5', '2027'),
    (nextval('seq_contratos'), '11', '6', '2028'),
    (nextval('seq_contratos'), '12', '6', '2026'),
    (nextval('seq_contratos'), '13', '7', '2026'),
    (nextval('seq_contratos'), '14', '7', '2025'),
    (nextval('seq_contratos'), '15', '8', '2027'),
    (nextval('seq_contratos'), '16', '8', '2025'),
    (nextval('seq_contratos'), '17', '9', '2026'),
    (nextval('seq_contratos'), '18', '9', '2027'),
    (nextval('seq_contratos'), '19', '10', '2025'),
    (nextval('seq_contratos'), '20', '10', '2027');


COMMIT;