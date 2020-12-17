create database dagda;

use dagda;
CREATE TABLE Empresa(
	idEmpresa INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(45),
    rua  VARCHAR(40),
    numero VARCHAR(5),
    bairro VARCHAR(50),
    cidade VARCHAR(20),
    estado char(2),
    cnpj CHAR(14),
    cep CHAR(7),
    pais varchar(30),
    telefone CHAR(10),
    representante VARCHAR(100)
);

CREATE TABLE Usuario(
	idUsuario INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100),
    email VARCHAR(30),
    senha VARCHAR(45),
    fkEmpresa INT,
    FOREIGN KEY (fkEmpresa) references Empresa(idEmpresa)
);

CREATE TABLE Maquina(
	idMaquina INT PRIMARY KEY auto_increment,
    nome VARCHAR(35),
    fkEmpresa INT,
    FOREIGN KEY (fkEmpresa) references Empresa(idEmpresa)
);

CREATE TABLE Componente(
	idComponente INT PRIMARY KEY AUTO_INCREMENT,
    tipoComponente VARCHAR(20),
    metrica varchar(6)
);

create table MaqComp(
	idMaqComp INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(45),
    alerta decimal,
    fkMaquina INT,
    fkComponente INT,
    FOREIGN KEY(fkMaquina) REFERENCES Maquina(idMaquina),
    FOREIGN KEY(fkComponente) REFERENCES Componente(idComponente)
);
create table Leituras(
	idLeitura INT PRIMARY KEY AUTO_INCREMENT,
    leitura decimal,
    horaDeLeitura time,
    fkMaqComp int,
    foreign key(fkMaqComp) references MaqComp(idMaqComp)
);