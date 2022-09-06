CREATE TABLE Motorvogn
(
    id INTEGER IDENTITY NOT NULL,
    personnr VARCHAR(20) NOT NULL,
    navn VARCHAR(255) NOT NULL,
    adresse VARCHAR(255) NOT NULL,
    kjennetegn VARCHAR(20) NOT NULL,
    merke VARCHAR(255) NOT NULL,
    type VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE Bil
(
    id INTEGER IDENTITY NOT NULL,
    merke VARCHAR(255) NOT NULL,
    type VARCHAR(255) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE Kunde(
    id INTEGER IDENTITY NOT NULL,
    fornavn VARCHAR(255) NOT NULL,
    passord VARCHAR(255) NOT NULL,
    PRIMARY KEY(id)
);

INSERT INTO Bil(merke, type)
VALUES ('Volvo','V30'),
('Volvo','V70'),
('Volvo','V91'),
('Audi','A8'),
('Audi','Q7'),
('Audi','Q8');