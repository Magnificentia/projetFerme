-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`fournisseur`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`fournisseur` (
  `idFournisseur` INT(11) NOT NULL AUTO_INCREMENT,
  `NomFournisseur` VARCHAR(45) NOT NULL,
  `Adresse` VARCHAR(45) NULL DEFAULT NULL,
  `Tel` INT(11) NULL DEFAULT NULL,
  `Fournisseurcol` VARCHAR(45) NULL DEFAULT NULL,
  `Email` VARCHAR(45) NULL DEFAULT NULL,
  `Site_web` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idFournisseur`),
  UNIQUE INDEX `NomFournisseur_UNIQUE` (`NomFournisseur` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`stockaliment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`stockaliment` (
  `idStockAliment` INT(11) NOT NULL AUTO_INCREMENT,
  `Quantite` INT(11) NOT NULL,
  `dateArrivage` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Fournisseur_idFournisseur` INT(11) NOT NULL,
  PRIMARY KEY (`idStockAliment`),
  INDEX `fk_StockAliment_Fournisseur1_idx` (`Fournisseur_idFournisseur` ASC) VISIBLE,
  CONSTRAINT `fk_StockAliment_Fournisseur1`
    FOREIGN KEY (`Fournisseur_idFournisseur`)
    REFERENCES `mydb`.`fournisseur` (`idFournisseur`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`aliment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`aliment` (
  `idAliment` INT(11) NOT NULL AUTO_INCREMENT,
  `nomAli` VARCHAR(45) NOT NULL,
  `Description` BLOB NULL DEFAULT NULL,
  `StockAliment_idStockAliment` INT(11) NOT NULL,
  PRIMARY KEY (`idAliment`),
  UNIQUE INDEX `nomAli_UNIQUE` (`nomAli` ASC) VISIBLE,
  UNIQUE INDEX `idAliment_UNIQUE` (`idAliment` ASC) VISIBLE,
  INDEX `fk_Aliment_StockAliment1_idx` (`StockAliment_idStockAliment` ASC) VISIBLE,
  CONSTRAINT `fk_Aliment_StockAliment1`
    FOREIGN KEY (`StockAliment_idStockAliment`)
    REFERENCES `mydb`.`stockaliment` (`idStockAliment`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`batiment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`batiment` (
  `idBatiment` INT(11) NOT NULL AUTO_INCREMENT,
  `Surface` DECIMAL(10,0) NOT NULL,
  `NomBatiment` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idBatiment`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`race`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`race` (
  `idRace` INT(11) NOT NULL AUTO_INCREMENT,
  `Nom` VARCHAR(45) NOT NULL,
  `Description` BLOB NULL DEFAULT NULL,
  PRIMARY KEY (`idRace`),
  UNIQUE INDEX `Nom_UNIQUE` (`Nom` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`bande`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`bande` (
  `idBande` INT(11) NOT NULL AUTO_INCREMENT,
  `Quantite` INT(10) UNSIGNED NOT NULL,
  `Date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Age` INT(10) UNSIGNED NOT NULL,
  `Batiment_idBatiment` INT(11) NOT NULL,
  `Race_idRace` INT(11) NOT NULL,
  `Fournisseur_idFournisseur` INT(11) NOT NULL,
  PRIMARY KEY (`idBande`),
  INDEX `fk_Bande_Batiment1_idx` (`Batiment_idBatiment` ASC) VISIBLE,
  INDEX `fk_Bande_Race1_idx` (`Race_idRace` ASC) VISIBLE,
  INDEX `fk_Bande_Fournisseur1_idx` (`Fournisseur_idFournisseur` ASC) VISIBLE,
  CONSTRAINT `fk_Bande_Batiment1`
    FOREIGN KEY (`Batiment_idBatiment`)
    REFERENCES `mydb`.`batiment` (`idBatiment`),
  CONSTRAINT `fk_Bande_Fournisseur1`
    FOREIGN KEY (`Fournisseur_idFournisseur`)
    REFERENCES `mydb`.`fournisseur` (`idFournisseur`),
  CONSTRAINT `fk_Bande_Race1`
    FOREIGN KEY (`Race_idRace`)
    REFERENCES `mydb`.`race` (`idRace`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`client` (
  `idClient` INT(11) NOT NULL,
  `nomClient` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idClient`),
  UNIQUE INDEX `nomClient_UNIQUE` (`nomClient` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`collecteoeuf`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`collecteoeuf` (
  `idCollecteOeuf` INT(11) NOT NULL AUTO_INCREMENT,
  `Quantite` INT(11) NOT NULL,
  `Date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Bande_idBande` INT(11) NOT NULL,
  PRIMARY KEY (`idCollecteOeuf`),
  INDEX `fk_CollecteOeuf_Bande1_idx` (`Bande_idBande` ASC) VISIBLE,
  CONSTRAINT `fk_CollecteOeuf_Bande1`
    FOREIGN KEY (`Bande_idBande`)
    REFERENCES `mydb`.`bande` (`idBande`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`incubation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`incubation` (
  `idIncubation` INT(11) NOT NULL AUTO_INCREMENT,
  `dateIncubation` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idIncubation`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`oeuf`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`oeuf` (
  `Incubation_idIncubation` INT(11) NOT NULL,
  `CollecteOeuf_idCollecteOeuf` INT(11) NOT NULL,
  `Quantite` INT(11) NOT NULL,
  `Taux` DECIMAL(2,2) NOT NULL,
  PRIMARY KEY (`Incubation_idIncubation`, `CollecteOeuf_idCollecteOeuf`),
  INDEX `fk_Incubation_has_CollecteOeuf_CollecteOeuf1_idx` (`CollecteOeuf_idCollecteOeuf` ASC) VISIBLE,
  INDEX `fk_Incubation_has_CollecteOeuf_Incubation1_idx` (`Incubation_idIncubation` ASC) VISIBLE,
  CONSTRAINT `fk_Incubation_has_CollecteOeuf_CollecteOeuf1`
    FOREIGN KEY (`CollecteOeuf_idCollecteOeuf`)
    REFERENCES `mydb`.`collecteoeuf` (`idCollecteOeuf`),
  CONSTRAINT `fk_Incubation_has_CollecteOeuf_Incubation1`
    FOREIGN KEY (`Incubation_idIncubation`)
    REFERENCES `mydb`.`incubation` (`idIncubation`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`ration`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`ration` (
  `Bande_idBande` INT(11) NOT NULL,
  `Aliment_idAliment` INT(11) NOT NULL,
  `Date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Quantite` INT(11) NOT NULL,
  `Eau` INT(11) NOT NULL,
  PRIMARY KEY (`Bande_idBande`, `Aliment_idAliment`),
  INDEX `fk_Bande_has_Aliment_Aliment1_idx` (`Aliment_idAliment` ASC) VISIBLE,
  INDEX `fk_Bande_has_Aliment_Bande1_idx` (`Bande_idBande` ASC) VISIBLE,
  CONSTRAINT `fk_Bande_has_Aliment_Aliment1`
    FOREIGN KEY (`Aliment_idAliment`)
    REFERENCES `mydb`.`aliment` (`idAliment`),
  CONSTRAINT `fk_Bande_has_Aliment_Bande1`
    FOREIGN KEY (`Bande_idBande`)
    REFERENCES `mydb`.`bande` (`idBande`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`vendu`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`vendu` (
  `Bande_idBande` INT(11) NOT NULL,
  `Client_idClient` INT(11) NOT NULL,
  `Date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Prix` DECIMAL(10,0) NOT NULL,
  `Quantite` INT(11) NOT NULL,
  PRIMARY KEY (`Bande_idBande`, `Client_idClient`),
  INDEX `fk_Bande_has_Client_Client1_idx` (`Client_idClient` ASC) VISIBLE,
  INDEX `fk_Bande_has_Client_Bande1_idx` (`Bande_idBande` ASC) VISIBLE,
  CONSTRAINT `fk_Bande_has_Client_Bande1`
    FOREIGN KEY (`Bande_idBande`)
    REFERENCES `mydb`.`bande` (`idBande`),
  CONSTRAINT `fk_Bande_has_Client_Client1`
    FOREIGN KEY (`Client_idClient`)
    REFERENCES `mydb`.`client` (`idClient`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`venteoeuf`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`venteoeuf` (
  `CollecteOeuf_idCollecteOeuf` INT(11) NOT NULL,
  `Client_idClient` INT(11) NOT NULL,
  `Quantite` INT(11) NOT NULL,
  `DateVente` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`CollecteOeuf_idCollecteOeuf`, `Client_idClient`),
  INDEX `fk_CollecteOeuf_has_Client_Client1_idx` (`Client_idClient` ASC) VISIBLE,
  INDEX `fk_CollecteOeuf_has_Client_CollecteOeuf1_idx` (`CollecteOeuf_idCollecteOeuf` ASC) VISIBLE,
  CONSTRAINT `fk_CollecteOeuf_has_Client_Client1`
    FOREIGN KEY (`Client_idClient`)
    REFERENCES `mydb`.`client` (`idClient`),
  CONSTRAINT `fk_CollecteOeuf_has_Client_CollecteOeuf1`
    FOREIGN KEY (`CollecteOeuf_idCollecteOeuf`)
    REFERENCES `mydb`.`collecteoeuf` (`idCollecteOeuf`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
