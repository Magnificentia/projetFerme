-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: volailledor
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `aliment`
--

DROP TABLE IF EXISTS `aliment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `aliment` (
  `idAli` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nomAli` varchar(15) NOT NULL,
  `description` text NOT NULL,
  `prix` decimal(8,2) NOT NULL,
  PRIMARY KEY (`idAli`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aliment`
--

LOCK TABLES `aliment` WRITE;
/*!40000 ALTER TABLE `aliment` DISABLE KEYS */;
INSERT INTO `aliment` VALUES (1,'mais','du mais',500.00),(2,'mais','du mais',500.00),(3,'orge','de l\'orge',500.00),(4,'blé','du blé',500.00),(5,'mil','du mil',500.00);
/*!40000 ALTER TABLE `aliment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `appointment`
--

DROP TABLE IF EXISTS `appointment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `appointment` (
  `idappoint` int(11) NOT NULL AUTO_INCREMENT,
  `starttime` datetime DEFAULT NULL,
  `endtime` datetime DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idappoint`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointment`
--

LOCK TABLES `appointment` WRITE;
/*!40000 ALTER TABLE `appointment` DISABLE KEYS */;
INSERT INTO `appointment` VALUES (1,'2020-02-02 06:55:00','2020-02-02 10:55:00','programation'),(2,'2020-02-02 16:55:00','2020-02-02 17:55:00','unit testing'),(4,'2020-02-07 13:36:11','2020-02-07 17:36:25','jjkjkkkj'),(6,'2020-02-04 15:10:00','2020-02-04 18:45:00',NULL),(7,'2020-02-05 07:55:00','2020-02-05 12:20:00',NULL),(9,'2020-02-06 02:10:00','2020-02-06 07:45:00','test modif');
/*!40000 ALTER TABLE `appointment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bande`
--

DROP TABLE IF EXISTS `bande`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `bande` (
  `idBande` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `qte` int(10) unsigned NOT NULL,
  `age` int(10) unsigned NOT NULL,
  `race_id` int(10) unsigned DEFAULT NULL,
  `prix_achat` decimal(10,2) DEFAULT NULL,
  `prix_vente` decimal(10,2) DEFAULT NULL,
  `dateDemarrage` datetime DEFAULT NULL,
  `fourn_id` int(10) unsigned DEFAULT NULL,
  `bat_id` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`idBande`),
  KEY `fk_race_id` (`race_id`),
  KEY `fk_bande_race_id` (`fourn_id`),
  KEY `fk_bat_id` (`bat_id`),
  CONSTRAINT `fk_bande_race_id` FOREIGN KEY (`fourn_id`) REFERENCES `fournisseur` (`idfourn`) ON DELETE SET NULL,
  CONSTRAINT `fk_bat_id` FOREIGN KEY (`bat_id`) REFERENCES `batiment` (`idbat`) ON DELETE SET NULL,
  CONSTRAINT `fk_race_id` FOREIGN KEY (`race_id`) REFERENCES `race` (`idrace`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bande`
--

LOCK TABLES `bande` WRITE;
/*!40000 ALTER TABLE `bande` DISABLE KEYS */;
INSERT INTO `bande` VALUES (6,4,8,1,29.00,3544.00,'2018-09-01 00:00:00',1,11),(14,7,8,2,9.00,NULL,'2020-01-24 00:00:00',2,4),(16,9,6,3,8.00,NULL,'2019-04-12 00:00:00',3,11),(19,888,0,2,88.00,NULL,'2020-01-01 00:00:00',2,2);
/*!40000 ALTER TABLE `bande` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bandemalade`
--

DROP TABLE IF EXISTS `bandemalade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `bandemalade` (
  `idBandeMalade` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `maladie_id` int(10) unsigned DEFAULT NULL,
  `bande_id` int(10) unsigned DEFAULT NULL,
  `qteMalade` int(10) unsigned DEFAULT NULL,
  `qtePrise` int(10) unsigned DEFAULT NULL,
  `dateM` datetime DEFAULT NULL,
  `totalMort` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`idBandeMalade`),
  UNIQUE KEY `ind_uni_bande_maladie_id` (`bande_id`,`maladie_id`),
  KEY `fk_bandemalade_maladie_id` (`maladie_id`),
  CONSTRAINT `fk_bandemalade_bande_id` FOREIGN KEY (`bande_id`) REFERENCES `bande` (`idbande`) ON DELETE SET NULL,
  CONSTRAINT `fk_bandemalade_maladie_id` FOREIGN KEY (`maladie_id`) REFERENCES `maladie` (`idm`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bandemalade`
--

LOCK TABLES `bandemalade` WRITE;
/*!40000 ALTER TABLE `bandemalade` DISABLE KEYS */;
/*!40000 ALTER TABLE `bandemalade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bandevaccine`
--

DROP TABLE IF EXISTS `bandevaccine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `bandevaccine` (
  `idBandeVaccine` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `bande_id` int(10) unsigned DEFAULT NULL,
  `vaccin_id` int(10) unsigned DEFAULT NULL,
  `dateVac` datetime DEFAULT NULL,
  PRIMARY KEY (`idBandeVaccine`),
  UNIQUE KEY `ind_uni_vacc_datevac_id` (`bande_id`,`vaccin_id`),
  KEY `fk_bandevaccine_vaccin_id` (`vaccin_id`),
  CONSTRAINT `fk_bandevaccine_bande_id` FOREIGN KEY (`bande_id`) REFERENCES `bande` (`idbande`) ON DELETE SET NULL,
  CONSTRAINT `fk_bandevaccine_vaccin_id` FOREIGN KEY (`vaccin_id`) REFERENCES `vaccin` (`idvac`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bandevaccine`
--

LOCK TABLES `bandevaccine` WRITE;
/*!40000 ALTER TABLE `bandevaccine` DISABLE KEYS */;
INSERT INTO `bandevaccine` VALUES (1,NULL,2,'2019-04-02 00:00:00'),(2,6,1,'2019-04-02 00:00:00');
/*!40000 ALTER TABLE `bandevaccine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `bandevaccineview`
--

DROP TABLE IF EXISTS `bandevaccineview`;
/*!50001 DROP VIEW IF EXISTS `bandevaccineview`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `bandevaccineview` AS SELECT 
 1 AS `idbandevaccine`,
 1 AS `nomVaccination`,
 1 AS `nomvac`,
 1 AS `bande_id`,
 1 AS `nombande`,
 1 AS `datevac`,
 1 AS `vaccin_id`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `bandeview`
--

DROP TABLE IF EXISTS `bandeview`;
/*!50001 DROP VIEW IF EXISTS `bandeview`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `bandeview` AS SELECT 
 1 AS `idbande`,
 1 AS `nombande`,
 1 AS `qte`,
 1 AS `datedemarrage`,
 1 AS `age`,
 1 AS `prix_achat`,
 1 AS `race_id`,
 1 AS `nom`,
 1 AS `bat_id`,
 1 AS `nombat`,
 1 AS `fourn_id`,
 1 AS `nomfourn`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `batiment`
--

DROP TABLE IF EXISTS `batiment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `batiment` (
  `idBat` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `surface` decimal(5,2) NOT NULL,
  `nomBat` varchar(10) NOT NULL,
  PRIMARY KEY (`idBat`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `batiment`
--

LOCK TABLES `batiment` WRITE;
/*!40000 ALTER TABLE `batiment` DISABLE KEYS */;
INSERT INTO `batiment` VALUES (1,400.52,'BatimentA'),(2,500.25,'BatimentB'),(3,700.50,'BatimentC'),(4,700.85,'BatimentD'),(9,34.00,'batimentA'),(10,588.00,'grange'),(11,888.00,'terrain'),(12,44.00,'jardin');
/*!40000 ALTER TABLE `batiment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `client` (
  `idClient` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `adresse` varchar(15) DEFAULT NULL,
  `tel` int(10) unsigned DEFAULT NULL,
  `nomClient` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`idClient`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (1,'symbock',794843,'willy'),(2,'symbock',794843,'willy2'),(3,'symbock',67,'nnane'),(4,'uuuu',78,'uuuu'),(5,'yyyyy',90,'uuuuu'),(6,'oooooo',90,'oooooo'),(7,'pppp',8,'ppppp'),(8,'yyyyyyy',78,'yyyyyyy'),(9,'llllllll',0,'llllllll'),(10,'pppppp',9,'ppppppppp'),(11,'mmmmmm',0,'mmmmmm'),(12,'yyyyyyyyy',5,'yyyyyyyy'),(13,'ppppppp',888,'ppppppppp'),(14,'uuuuuuuuu',9,'uuuuuuuuu'),(15,'iii',0,'uuu'),(16,'iiiiiiiiii',0,'uuuu'),(17,'p',0,'pp'),(18,'uuuu',0,'uuuu'),(19,'uuuu',0,'uuu'),(20,'uuuu',9,'uuu'),(21,'uuuu',89,'uuu'),(22,'pp',0,'pp'),(23,'pp',0,'ppp'),(24,'p',9,'p'),(25,'ooo',8,'iii'),(26,'ooo',99,'ppppppppppp'),(27,'dfsd',4,'dsfsd'),(28,'dfsd',4,'dsfd'),(29,'ddsd',4,'dsfd'),(30,'sdf',4,'sdfsd'),(31,'sdf',4,'sdfsd'),(32,'sdfd',4,'sdfsd'),(33,'y',8,'y'),(34,'i',7,'u'),(35,'o',8,'o'),(36,'fsd',4,'sfdsf'),(37,'uu',9,'uu');
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `collecteoeuf`
--

DROP TABLE IF EXISTS `collecteoeuf`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `collecteoeuf` (
  `idCollect` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `qte` int(10) unsigned NOT NULL,
  `dateCollect` datetime DEFAULT NULL,
  `incubation` int(1) DEFAULT NULL,
  `bande_id` int(10) unsigned DEFAULT NULL,
  `prix_alveole` decimal(9,2) DEFAULT NULL,
  `qteCasse` int(10) unsigned DEFAULT NULL,
  `typeOeuf_id` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`idCollect`),
  KEY `ind_date_collect` (`dateCollect`),
  KEY `fk_collect_bande_id` (`bande_id`),
  KEY `fk_collecteOeuf_typeOeuf_id` (`typeOeuf_id`),
  CONSTRAINT `fk_collect_bande_id` FOREIGN KEY (`bande_id`) REFERENCES `bande` (`idbande`) ON DELETE SET NULL,
  CONSTRAINT `fk_collecteOeuf_typeOeuf_id` FOREIGN KEY (`typeOeuf_id`) REFERENCES `typeoeuf` (`idtypeoeuf`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `collecteoeuf`
--

LOCK TABLES `collecteoeuf` WRITE;
/*!40000 ALTER TABLE `collecteoeuf` DISABLE KEYS */;
INSERT INTO `collecteoeuf` VALUES (1,3,'2017-09-07 00:00:00',1,NULL,2300.00,1,1),(2,3,'2017-09-07 00:00:00',1,NULL,2300.00,1,1),(3,7,'2017-09-07 00:00:00',1,NULL,2300.00,1,1),(4,17,'2017-09-07 00:00:00',1,NULL,2300.00,2,1);
/*!40000 ALTER TABLE `collecteoeuf` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `collecteoeufview`
--

DROP TABLE IF EXISTS `collecteoeufview`;
/*!50001 DROP VIEW IF EXISTS `collecteoeufview`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `collecteoeufview` AS SELECT 
 1 AS `idcollect`,
 1 AS `qte`,
 1 AS `datecollect`,
 1 AS `incubation`,
 1 AS `bande_id`,
 1 AS `prix_alveole`,
 1 AS `qtecasse`,
 1 AS `typeoeuf_id`,
 1 AS `nomtf`,
 1 AS `nombande`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `employes`
--

DROP TABLE IF EXISTS `employes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `employes` (
  `idEm` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nom` varchar(15) DEFAULT NULL,
  `user` varchar(15) DEFAULT NULL,
  `login` varchar(20) DEFAULT NULL,
  `typeem` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`idEm`),
  UNIQUE KEY `ind_uni_user` (`user`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employes`
--

LOCK TABLES `employes` WRITE;
/*!40000 ALTER TABLE `employes` DISABLE KEYS */;
INSERT INTO `employes` VALUES (1,'nnane','nprime','nprime','ingenieur'),(2,'mouen','mouen','mouen','ingenieur');
/*!40000 ALTER TABLE `employes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fournisseur`
--

DROP TABLE IF EXISTS `fournisseur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `fournisseur` (
  `idFourn` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nomFourn` varchar(20) NOT NULL,
  `adresse` varchar(20) NOT NULL,
  `tel` int(10) unsigned NOT NULL,
  `email` varchar(20) DEFAULT NULL,
  `siteweb` varchar(20) DEFAULT NULL,
  `typeFourn` int(1) DEFAULT NULL,
  PRIMARY KEY (`idFourn`),
  UNIQUE KEY `ind_uni_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fournisseur`
--

LOCK TABLES `fournisseur` WRITE;
/*!40000 ALTER TABLE `fournisseur` DISABLE KEYS */;
INSERT INTO `fournisseur` VALUES (1,'nnane','douala',6788888,'nnane@gmail.com',NULL,1),(2,'mouen','baff',6788888,'mouen@gmail.com',NULL,1),(3,'simo','yde',6788888,'simo@gmail.com',NULL,1),(4,'cimencam','douala',566,'cim@gmail.com','cim@gmail.com',1),(5,'megasoft','douala',8,'m@gmail','m@gmail',1),(6,'dfad','dfaedsafsd',78,'dfad','dfad',1);
/*!40000 ALTER TABLE `fournisseur` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `incubation`
--

DROP TABLE IF EXISTS `incubation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `incubation` (
  `idInc` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `dateInc` datetime DEFAULT NULL,
  `ProduirePoussin_id` int(10) unsigned DEFAULT NULL,
  `collect_id` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`idInc`),
  KEY `ind_date_inc` (`dateInc`),
  KEY `fk_incubation_collect_id` (`collect_id`),
  CONSTRAINT `fk_incubation_collect_id` FOREIGN KEY (`collect_id`) REFERENCES `collecteoeuf` (`idcollect`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `incubation`
--

LOCK TABLES `incubation` WRITE;
/*!40000 ALTER TABLE `incubation` DISABLE KEYS */;
INSERT INTO `incubation` VALUES (1,'2021-11-26 19:45:50',2,3),(2,'2021-11-26 19:45:50',2,4);
/*!40000 ALTER TABLE `incubation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `maladie`
--

DROP TABLE IF EXISTS `maladie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `maladie` (
  `idM` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nomM` varchar(20) NOT NULL,
  `descriptionTraitement` text,
  `descriptionMaladie` text,
  `duree` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`idM`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `maladie`
--

LOCK TABLES `maladie` WRITE;
/*!40000 ALTER TABLE `maladie` DISABLE KEYS */;
INSERT INTO `maladie` VALUES (1,'grippe aviaire',NULL,NULL,'2 jours'),(2,'fievre',NULL,NULL,'1 semaine'),(3,'fievre jaune',NULL,NULL,'1 mois'),(4,'paludisme',NULL,NULL,'2 semaines');
/*!40000 ALTER TABLE `maladie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produirepoussin`
--

DROP TABLE IF EXISTS `produirepoussin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `produirepoussin` (
  `idProduirePoussin` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `qte` int(10) unsigned NOT NULL,
  `taux` decimal(4,2) DEFAULT NULL,
  `incubation_id` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`idProduirePoussin`),
  KEY `fk_produirePoussin_incubation_id` (`incubation_id`),
  CONSTRAINT `fk_produirePoussin_incubation_id` FOREIGN KEY (`incubation_id`) REFERENCES `incubation` (`idinc`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produirepoussin`
--

LOCK TABLES `produirepoussin` WRITE;
/*!40000 ALTER TABLE `produirepoussin` DISABLE KEYS */;
INSERT INTO `produirepoussin` VALUES (1,45,50.00,2),(3,45,50.00,2);
/*!40000 ALTER TABLE `produirepoussin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `race`
--

DROP TABLE IF EXISTS `race`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `race` (
  `idRace` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nom` varchar(15) NOT NULL,
  `description` text NOT NULL,
  `prix_race` decimal(7,2) DEFAULT NULL,
  PRIMARY KEY (`idRace`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `race`
--

LOCK TABLES `race` WRITE;
/*!40000 ALTER TABLE `race` DISABLE KEYS */;
INSERT INTO `race` VALUES (1,'poule blanche','blanche',459.00),(2,'poule rouge','rouge',4459.00),(3,'dindon','dindon',4459.00);
/*!40000 ALTER TABLE `race` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ration`
--

DROP TABLE IF EXISTS `ration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `ration` (
  `idRation` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ali_id` int(10) unsigned DEFAULT NULL,
  `bande_id` int(10) unsigned DEFAULT NULL,
  `dateRation` datetime NOT NULL,
  `qte` decimal(5,2) NOT NULL,
  `eau` decimal(5,2) NOT NULL,
  PRIMARY KEY (`idRation`),
  UNIQUE KEY `ind_uni_ali_bande_id` (`ali_id`,`bande_id`),
  KEY `fk_ration_bande_id` (`bande_id`),
  CONSTRAINT `fk_ration_ali_id` FOREIGN KEY (`ali_id`) REFERENCES `aliment` (`idali`) ON DELETE SET NULL,
  CONSTRAINT `fk_ration_bande_id` FOREIGN KEY (`bande_id`) REFERENCES `bande` (`idbande`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ration`
--

LOCK TABLES `ration` WRITE;
/*!40000 ALTER TABLE `ration` DISABLE KEYS */;
INSERT INTO `ration` VALUES (1,1,NULL,'2017-09-01 00:00:00',20.00,10.00),(2,2,NULL,'2017-09-01 00:00:00',20.00,10.00),(3,1,NULL,'2017-09-01 00:00:00',20.00,10.00),(4,1,6,'2017-10-01 00:00:00',2.00,16.00),(5,2,6,'2017-10-01 00:00:00',2.00,16.00);
/*!40000 ALTER TABLE `ration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `rationview`
--

DROP TABLE IF EXISTS `rationview`;
/*!50001 DROP VIEW IF EXISTS `rationview`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `rationview` AS SELECT 
 1 AS `idration`,
 1 AS `nomration`,
 1 AS `bande_id`,
 1 AS `nombande`,
 1 AS `ali_id`,
 1 AS `nomali`,
 1 AS `dateration`,
 1 AS `qte`,
 1 AS `eau`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `stockaliment`
--

DROP TABLE IF EXISTS `stockaliment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `stockaliment` (
  `idStock` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `qte` int(10) unsigned NOT NULL,
  `dateArrivage` datetime DEFAULT NULL,
  `ali_id` int(10) unsigned DEFAULT NULL,
  `employe_id` int(10) unsigned DEFAULT NULL,
  `fourn_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`idStock`),
  KEY `fk_stock_ali_id` (`ali_id`),
  KEY `fk_stock_employe_id` (`employe_id`),
  CONSTRAINT `fk_stock_ali_id` FOREIGN KEY (`ali_id`) REFERENCES `aliment` (`idali`) ON DELETE SET NULL,
  CONSTRAINT `fk_stock_employe_id` FOREIGN KEY (`employe_id`) REFERENCES `employes` (`idem`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stockaliment`
--

LOCK TABLES `stockaliment` WRITE;
/*!40000 ALTER TABLE `stockaliment` DISABLE KEYS */;
INSERT INTO `stockaliment` VALUES (4,1200,'2020-01-01 00:00:00',1,1,1),(5,120,'2020-02-01 00:00:00',1,1,1),(6,1320,'2019-12-24 00:00:00',1,1,1),(7,5,'2020-01-01 00:00:00',1,NULL,2),(8,5,'2020-01-01 00:00:00',1,NULL,2),(9,5,'2020-01-01 00:00:00',1,NULL,2),(10,5,'2020-01-01 00:00:00',1,NULL,2),(11,5,'2020-01-01 00:00:00',1,NULL,2),(12,7,'2020-01-01 00:00:00',1,NULL,1),(13,79999,'2020-01-01 00:00:00',1,NULL,1),(14,6,'2020-01-01 00:00:00',1,NULL,2),(15,77,'2020-01-01 00:00:00',2,NULL,1);
/*!40000 ALTER TABLE `stockaliment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `stockalimentview`
--

DROP TABLE IF EXISTS `stockalimentview`;
/*!50001 DROP VIEW IF EXISTS `stockalimentview`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `stockalimentview` AS SELECT 
 1 AS `idstock`,
 1 AS `nomstock`,
 1 AS `qte`,
 1 AS `datearrivage`,
 1 AS `ali_id`,
 1 AS `nomali`,
 1 AS `employe_id`,
 1 AS `nom`,
 1 AS `fourn_id`,
 1 AS `nomfourn`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `typeoeuf`
--

DROP TABLE IF EXISTS `typeoeuf`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `typeoeuf` (
  `idTypeOeuf` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nomTf` varchar(10) DEFAULT NULL,
  `prix_alveole` decimal(9,2) DEFAULT NULL,
  PRIMARY KEY (`idTypeOeuf`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `typeoeuf`
--

LOCK TABLES `typeoeuf` WRITE;
/*!40000 ALTER TABLE `typeoeuf` DISABLE KEYS */;
INSERT INTO `typeoeuf` VALUES (1,'oeuf noir',34.00),(2,'oeuf blanc',34.00),(3,'oeuf mar',34.00);
/*!40000 ALTER TABLE `typeoeuf` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vaccin`
--

DROP TABLE IF EXISTS `vaccin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `vaccin` (
  `idVac` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nomVac` varchar(15) NOT NULL,
  `periode` int(11) DEFAULT NULL,
  `qteVac` int(11) NOT NULL,
  `qtePoule` int(11) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `prix` decimal(8,2) DEFAULT NULL,
  PRIMARY KEY (`idVac`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vaccin`
--

LOCK TABLES `vaccin` WRITE;
/*!40000 ALTER TABLE `vaccin` DISABLE KEYS */;
INSERT INTO `vaccin` VALUES (1,'HX020',10,1,5,'anti tuberculose',10000.00),(2,'HX021',10,1,5,'anti tuberculose 2',10000.00),(3,'dsfdffdf',10,0,0,NULL,4.00),(4,'jjj',10,0,0,NULL,44.00),(5,'swere',10,0,0,NULL,67.00),(6,'rr',10,0,0,NULL,5.00),(7,'j',10,0,0,NULL,6.00);
/*!40000 ALTER TABLE `vaccin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vendu`
--

DROP TABLE IF EXISTS `vendu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `vendu` (
  `idVente` int(10) unsigned NOT NULL,
  `client_id` int(10) unsigned DEFAULT NULL,
  `bande_id` int(10) unsigned DEFAULT NULL,
  `dateVente` datetime DEFAULT CURRENT_TIMESTAMP,
  `total_prix` decimal(8,2) NOT NULL,
  `qte` int(10) unsigned DEFAULT NULL,
  `employe_id` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`idVente`),
  UNIQUE KEY `ind_uni_client_bande_id` (`client_id`,`bande_id`),
  KEY `ind_datevente` (`dateVente`),
  KEY `fk_vendu_bande_id` (`bande_id`),
  KEY `fk_vendu_employe_id` (`employe_id`),
  CONSTRAINT `fk_vendu_bande_id` FOREIGN KEY (`bande_id`) REFERENCES `bande` (`idbande`) ON DELETE SET NULL,
  CONSTRAINT `fk_vendu_client_id` FOREIGN KEY (`client_id`) REFERENCES `client` (`idclient`) ON DELETE SET NULL,
  CONSTRAINT `fk_vendu_employe_id` FOREIGN KEY (`employe_id`) REFERENCES `employes` (`idem`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendu`
--

LOCK TABLES `vendu` WRITE;
/*!40000 ALTER TABLE `vendu` DISABLE KEYS */;
/*!40000 ALTER TABLE `vendu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venduoeuf`
--

DROP TABLE IF EXISTS `venduoeuf`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `venduoeuf` (
  `idVenduOeuf` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `collect_id` int(10) unsigned DEFAULT NULL,
  `client_id` int(10) unsigned DEFAULT NULL,
  `dateVente` datetime DEFAULT NULL,
  `total_prix` decimal(8,2) DEFAULT NULL,
  `qte` int(10) unsigned NOT NULL,
  `employe_id` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`idVenduOeuf`),
  UNIQUE KEY `ind_uni_collect_client_id` (`collect_id`,`client_id`),
  KEY `ind_date_vente_oeuf` (`dateVente`),
  KEY `fk_venduoeuf_client_id` (`client_id`),
  KEY `fk_venduoeuf_employe_id` (`employe_id`),
  CONSTRAINT `fk_venduoeuf_client_id` FOREIGN KEY (`client_id`) REFERENCES `client` (`idclient`) ON DELETE SET NULL,
  CONSTRAINT `fk_venduoeuf_collect_id` FOREIGN KEY (`collect_id`) REFERENCES `collecteoeuf` (`idcollect`) ON DELETE SET NULL,
  CONSTRAINT `fk_venduoeuf_employe_id` FOREIGN KEY (`employe_id`) REFERENCES `employes` (`idem`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venduoeuf`
--

LOCK TABLES `venduoeuf` WRITE;
/*!40000 ALTER TABLE `venduoeuf` DISABLE KEYS */;
INSERT INTO `venduoeuf` VALUES (1,1,1,'0201-08-01 00:00:00',20000.00,45,1),(2,2,1,'0201-08-01 00:00:00',20300.00,45,1);
/*!40000 ALTER TABLE `venduoeuf` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `bandevaccineview`
--

/*!50001 DROP VIEW IF EXISTS `bandevaccineview`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = cp850 */;
/*!50001 SET character_set_results     = cp850 */;
/*!50001 SET collation_connection      = cp850_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `bandevaccineview` AS select `bandevaccine`.`idBandeVaccine` AS `idbandevaccine`,concat('vaccination',`bandevaccine`.`idBandeVaccine`) AS `nomVaccination`,`vaccin`.`nomVac` AS `nomvac`,`bandevaccine`.`bande_id` AS `bande_id`,concat('bande',`bandevaccine`.`bande_id`) AS `nombande`,`bandevaccine`.`dateVac` AS `datevac`,`bandevaccine`.`vaccin_id` AS `vaccin_id` from ((`bandevaccine` join `vaccin` on((`bandevaccine`.`vaccin_id` = `vaccin`.`idVac`))) join `bande` on((`bandevaccine`.`bande_id` = `bande`.`idBande`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `bandeview`
--

/*!50001 DROP VIEW IF EXISTS `bandeview`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = cp850 */;
/*!50001 SET character_set_results     = cp850 */;
/*!50001 SET collation_connection      = cp850_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `bandeview` AS select `bande`.`idBande` AS `idbande`,concat('bande',`bande`.`idBande`) AS `nombande`,`bande`.`qte` AS `qte`,`bande`.`dateDemarrage` AS `datedemarrage`,`bande`.`age` AS `age`,`bande`.`prix_achat` AS `prix_achat`,`bande`.`race_id` AS `race_id`,`race`.`nom` AS `nom`,`bande`.`bat_id` AS `bat_id`,`batiment`.`nomBat` AS `nombat`,`bande`.`fourn_id` AS `fourn_id`,`fournisseur`.`nomFourn` AS `nomfourn` from (((`bande` join `race` on((`bande`.`race_id` = `race`.`idRace`))) join `batiment` on((`bande`.`bat_id` = `batiment`.`idBat`))) join `fournisseur` on((`bande`.`fourn_id` = `fournisseur`.`idFourn`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `collecteoeufview`
--

/*!50001 DROP VIEW IF EXISTS `collecteoeufview`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = cp850 */;
/*!50001 SET character_set_results     = cp850 */;
/*!50001 SET collation_connection      = cp850_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `collecteoeufview` AS select `collecteoeuf`.`idCollect` AS `idcollect`,`collecteoeuf`.`qte` AS `qte`,`collecteoeuf`.`dateCollect` AS `datecollect`,`collecteoeuf`.`incubation` AS `incubation`,`collecteoeuf`.`bande_id` AS `bande_id`,`collecteoeuf`.`prix_alveole` AS `prix_alveole`,`collecteoeuf`.`qteCasse` AS `qtecasse`,`collecteoeuf`.`typeOeuf_id` AS `typeoeuf_id`,`typeoeuf`.`nomTf` AS `nomtf`,concat('bande',`bande`.`idBande`) AS `nombande` from ((`collecteoeuf` join `bande` on((`collecteoeuf`.`bande_id` = `bande`.`idBande`))) join `typeoeuf` on((`collecteoeuf`.`typeOeuf_id` = `typeoeuf`.`idTypeOeuf`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `rationview`
--

/*!50001 DROP VIEW IF EXISTS `rationview`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = cp850 */;
/*!50001 SET character_set_results     = cp850 */;
/*!50001 SET collation_connection      = cp850_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `rationview` AS select `ration`.`idRation` AS `idration`,concat('ration',`ration`.`idRation`) AS `nomration`,`ration`.`bande_id` AS `bande_id`,concat('bande',`ration`.`bande_id`) AS `nombande`,`ration`.`ali_id` AS `ali_id`,`aliment`.`nomAli` AS `nomali`,`ration`.`dateRation` AS `dateration`,`ration`.`qte` AS `qte`,`ration`.`eau` AS `eau` from (`ration` join `aliment` on((`ration`.`ali_id` = `aliment`.`idAli`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `stockalimentview`
--

/*!50001 DROP VIEW IF EXISTS `stockalimentview`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = cp850 */;
/*!50001 SET character_set_results     = cp850 */;
/*!50001 SET collation_connection      = cp850_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `stockalimentview` AS select `stockaliment`.`idStock` AS `idstock`,concat('stock',`stockaliment`.`idStock`) AS `nomstock`,`stockaliment`.`qte` AS `qte`,`stockaliment`.`dateArrivage` AS `datearrivage`,`stockaliment`.`ali_id` AS `ali_id`,`aliment`.`nomAli` AS `nomali`,`stockaliment`.`employe_id` AS `employe_id`,`employes`.`nom` AS `nom`,`stockaliment`.`fourn_id` AS `fourn_id`,`fournisseur`.`nomFourn` AS `nomfourn` from (((`stockaliment` join `aliment` on((`stockaliment`.`ali_id` = `aliment`.`idAli`))) join `employes` on((`employes`.`idEm` = `stockaliment`.`employe_id`))) join `fournisseur` on((`fournisseur`.`idFourn` = `stockaliment`.`fourn_id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-06 14:57:18
