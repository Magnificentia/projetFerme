﻿CREATE TABLE RACE(
	idRace INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	nom VARCHAR(15) NOT NULL,
	description TEXT NOT NULL,
	prix_race DECIMAL(7,2)

);


CREATE TABLE Bande(
	idBande INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	qte INT UNSIGNED NOT NULL,
	age INT UNSIGNED NOT NULL,
	race_id INT UNSIGNED,
	prix_achat DECIMAL(10,2),
	prix_vente DECIMAL(10,2), 
	dateDemarrage DATETIME,
	fourn_id INT UNSIGNED,
	bat_id INT UNSIGNED
);


CREATE TABLE Fournisseur(
	idFourn INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	nomFourn VARCHAR(20) NOT NULL,
	adresse VARCHAR(20) NOT NULL,
	tel INT UNSIGNED NOT NULL,
	email VARCHAR(20) UNIQUE,
	siteweb VARCHAR(20),
	typeFourn INT(1)
);



CREATE TABLE Aliment(
	idAli INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	nomAli VARCHAR(15) NOT NULL,
	description TEXT NOT NULL,
	prix DECIMAL(8,2) NOT NULL,
);



CREATE TABLE Ration(
	idRation INT UNSIGNED AUTO_INCREMENT,
	ali_id INT UNSIGNED,
	bande_id INT UNSIGNED,
	dateRation DATETIME NOT NULL,
	qte DECIMAL(5,2) NOT NULL,
	eau DECIMAL(5,2) NOT NULL,
	PRIMARY KEY(idRation),
	UNIQUE ind_uni_ali_bande_id (ali_id,bande_id)
);



CREATE TABLE StockAliment(
	idStock INT UNSIGNED AUTO_INCREMENT,
	qte INT UNSIGNED NOT NULL,
	dateArrivage DATETIME,
	ali_id INT UNSIGNED,
	employe_id INT UNSIGNED,
	fourn_id INT UNSIGNED,
	PRIMARY KEY(idStock)
)
;


CREATE TABLE Vendu(
	idVente INT UNSIGNED,
	client_id INT UNSIGNED,
	bande_id INT UNSIGNED,
	dateVente DATETIME DEFAULT NOW(),
	total_prix DECIMAL(8,2) NOT NULL,
	qte INT UNSIGNED,
	employe_id INT UNSIGNED,
	PRIMARY KEY(idVente),
	CHECK(total_prix>0),
	INDEX ind_datevente(dateVente),
	UNIQUE ind_uni_client_bande_id (client_id,bande_id)
);


CREATE TABLE Client(
	idClient INT UNSIGNED AUTO_INCREMENT,
	adresse VARCHAR(15),
	tel INT UNSIGNED,
	PRIMARY KEY(idClient)
);



CREATE TABLE Batiment(
	idBat INT UNSIGNED AUTO_INCREMENT,
	surface DECIMAL(5,2) NOT NULL,
	nomBat VARCHAR(10) NOT NULL,
	PRIMARY KEY(idBat)
);



CREATE TABLE CollecteOeuf(
	idCollect INT UNSIGNED AUTO_INCREMENT,
	qte INT UNSIGNED NOT NULL,
	dateCollect DATETIME,
	incubation INT(1),
	bande_id INT UNSIGNED,
	prix_alveole DECIMAL(9,2),
	qteCasse INT UNSIGNED,
	typeOeuf_id INT UNSIGNED,
	PRIMARY KEY(idCollect),
	CHECK(incubation=0 OR incubation=1),
	INDEX ind_date_collect(dateCollect)

);


CREATE TABLE TypeOeuf(
	idTypeOeuf INT UNSIGNED AUTO_INCREMENT,
	nomTf VARCHAR(10),
	prix_alveole DECIMAL(9,2),
	PRIMARY KEY(idTypeOeuf)
);


CREATE TABLE VenduOeuf(
	idVenduOeuf INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	collect_id INT UNSIGNED,
	client_id INT UNSIGNED,
	dateVente DATETIME,
	total_prix DECIMAL(8,2),
	qte INT UNSIGNED NOT NULL,
	employe_id INT UNSIGNED
);



CREATE TABLE incubation(
	idInc INT UNSIGNED AUTO_INCREMENT PRIMARY KEY, 
	dateInc DATETIME,
	ProduirePoussin_id INT UNSIGNED,
	collect_id INT UNSIGNED
);


CREATE TABLE ProduirePoussin(
	idProduirePoussin INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	qte INT UNSIGNED NOT NULL,
	taux DECIMAL(4,2),
	incubation_id INT UNSIGNED,
	
);



CREATE TABLE Maladie(
	idM INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	nomM VARCHAR(20) NOT NULL,
	descriptionTraitement TEXT ,
	descriptionMaladie TEXT
);


CREATE TABLE BandeMalade(
	idBandeMalade INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	maladie_id INT UNSIGNED,
	bande_id INT UNSIGNED,
	qteMalade INT UNSIGNED,
	qtePrise INT UNSIGNED,
	dateM DATETIME,
	totalMort INT UNSIGNED
);


CREATE TABLE Vaccin(
	idVac INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	nomVac VARCHAR(15) NOT NULL,
	periode VARCHAR(15) NOT NULL,
	qteVac INT NOT NULL,
	qtePoule INT NOT NULL,
	description VARCHAR(200),
	prix DECIMAL(8,2)
);



CREATE TABLE BandeVaccine(
	idBandeVaccine INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	bande_id INT UNSIGNED,
	vaccin_id INT UNSIGNED,
	dateVac DATETIME

);



CREATE TABLE Employes(
	idEm INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	nom VARCHAR(15),
	user VARCHAR(15),
	login VARCHAR(20),
	typeEm VARCHAR(4)
);





ALTER TABLE Bande ADD CONSTRAINT fk_race_id FOREIGN KEY (race_id) REFERENCES RACE(idRace) ON DELETE SET NULL;
ALTER TABLE Bande ADD CONSTRAINT fk_bande_race_id FOREIGN KEY (fourn_id) REFERENCES Fournisseur(idFourn) ON DELETE SET NULL;
ALTER TABLE Bande ADD CONSTRAINT fk_bat_id FOREIGN KEY (bat_id) REFERENCES Batiment(idBat) ON DELETE SET NULL;
ALTER TABLE Ration ADD CONSTRAINT fk_ration_ali_id FOREIGN KEY (ali_id) REFERENCES Aliment(idAli) ON DELETE SET NULL;
ALTER TABLE Ration ADD CONSTRAINT fk_ration_bande_id FOREIGN KEY (bande_id) REFERENCES Bande(idBande) ON DELETE SET NULL;
ALTER TABLE StockAliment ADD CONSTRAINT fk_stock_ali_id FOREIGN KEY (ali_id) REFERENCES Aliment(idAli) ON DELETE SET NULL;
ALTER TABLE StockAliment ADD CONSTRAINT fk_stock_fourn_id FOREIGN KEY (fourn_id) REFERENCES Fournisseur(idFourn) ON DELETE SET NULL;
ALTER TABLE Vendu ADD CONSTRAINT fk_vendu_client_id FOREIGN KEY (client_id) REFERENCES Client(idClient) ON DELETE SET NULL;
ALTER TABLE Vendu ADD CONSTRAINT fk_vendu_bande_id FOREIGN KEY (bande_id) REFERENCES Bande(idBande) ON DELETE SET NULL;
ALTER TABLE CollecteOeuf ADD CONSTRAINT fk_collect_bande_id FOREIGN KEY (bande_id) REFERENCES Bande(idBande) ON DELETE SET NULL;
ALTER TABLE VenduOeuf ADD CONSTRAINT fk_venduoeuf_collect_id FOREIGN KEY (collect_id) REFERENCES CollecteOeuf(idCollect) ON DELETE SET NULL;
ALTER TABLE VenduOeuf ADD CONSTRAINT fk_venduoeuf_client_id FOREIGN KEY (client_id) REFERENCES Client(idClient) ON DELETE SET NULL;
ALTER table ProduirePoussin ADD CONSTRAINT fk_produirePoussin_incubation_id FOREIGN KEY(incubation_id) REFERENCES Incubation(idInc);
ALTER TABLE Incubation ADD CONSTRAINT fk_incubation_collect_id FOREIGN KEY (collect_id) REFERENCES CollecteOeuf(idCollect) ON DELETE SET NULL;
ALTER TABLE BandeMalade ADD CONSTRAINT fk_bandemalade_maladie_id FOREIGN KEY (maladie_id) REFERENCES Maladie(idM) ON DELETE SET NULL;
ALTER TABLE BandeMalade ADD CONSTRAINT fk_bandemalade_bande_id FOREIGN KEY (bande_id) REFERENCES Bande(idBande) ON DELETE SET NULL;
ALTER TABLE BandeVaccine ADD CONSTRAINT fk_bandevaccine_bande_id FOREIGN KEY (bande_id) REFERENCES Bande(idBande) ON DELETE SET NULL;
ALTER TABLE BandeVaccine ADD CONSTRAINT fk_bandevaccine_vaccin_id FOREIGN KEY (vaccin_id) REFERENCES Vaccin(idVac) ON DELETE SET NULL;
ALTER TABLE Vendu ADD CONSTRAINT fk_vendu_employe_id FOREIGN KEY (employe_id) REFERENCES Employes(idEm) ON DELETE SET NULL;
ALTER TABLE VenduOeuf ADD CONSTRAINT fk_venduoeuf_employe_id FOREIGN KEY (employe_id) REFERENCES Employes(idEm) ON DELETE SET NULL;
ALTER TABLE StockAliment ADD CONSTRAINT fk_stock_employe_id FOREIGN KEY (employe_id) REFERENCES Employes(idEm) ON DELETE SET NULL;
ALTER TABLE CollecteOeuf ADD CONSTRAINT fk_collecteOeuf_typeOeuf_id FOREIGN KEY (typeOeuf_id) REFERENCES TypeOeuf(idTypeOeuf) ON DELETE SET NULL;
