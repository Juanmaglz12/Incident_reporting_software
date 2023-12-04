-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: inc_rep_soft
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client` (
  `idClient` bigint NOT NULL AUTO_INCREMENT,
  `businessName` varchar(255) NOT NULL,
  `cuit` varchar(255) NOT NULL,
  `mail` varchar(255) NOT NULL,
  PRIMARY KEY (`idClient`),
  UNIQUE KEY `UK_ewpy7kvb2mtjfmyqpgjv813v` (`cuit`),
  UNIQUE KEY `UK_7saae44x6o7o8l9kmloawbbwx` (`mail`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (1,'CONSTRUCCIONES SA','30567890123','CONSTRUCCIONES@EXAMPLE.COM'),(2,'TRANSPORTES ARG','40912345678','TRANSPORTESARG@EXAMPLE.COM'),(3,'TECNOLOGIA GLOBAL','51345678901','TECGLOBAL@EXAMPLE.COM'),(4,'ALIMENTOS DELICIOSOS','61789012345','ALIMENTOSDELI@EXAMPLE.COM'),(5,'MODA Y ESTILO','72123456789','MODAYESTILO@EXAMPLE.COM'),(6,'SERVICIOS RAPIDOS SA','82456789012','SERVICIOSRAPIDOS@EXAMPLE.COM'),(7,'TELECOMUNICACIONES MAX','93567890123','TELECOMMAX@EXAMPLE.COM'),(8,'INDUSTRIA CREATIVA','10456789012','INDUSTRIACREATIVA@EXAMPLE.COM'),(9,'ENERGIA SOSTENIBLE','21567890123','ENERGIASOSTENIBLE@EXAMPLE.COM'),(10,'EDUCACION AVANZADA','32678901234','EDUCACIONAVANZADA@EXAMPLE.COM'),(11,'SOFTWARE INNOVADOR','43789012345','SOFTWAREINNOVADOR@EXAMPLE.COM'),(12,'LOGISTICA EXPRESS','54890123456','LOGISTICAEXPRESS@EXAMPLE.COM'),(13,'AGRICULTURA PRODUCTIVA','65901234567','AGRICULTURAPROD@EXAMPLE.COM'),(14,'CONSULTORIA ESTRATEGICA','76012345678','CONSULTORIAESTRA@EXAMPLE.COM'),(15,'MODERNOS ESPACIOS','87123456789','MODERNOS@EXAMPLE.COM'),(16,'ORO VERDE','665577333','OROROJO@ORO.COM');
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client_service`
--

DROP TABLE IF EXISTS `client_service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client_service` (
  `id_cliente` bigint NOT NULL,
  `id_service` bigint NOT NULL,
  KEY `FKqwdmn21dlcnv8xehjityqjn27` (`id_service`),
  KEY `FKlrujviid9xbegr2i6l8sehv57` (`id_cliente`),
  CONSTRAINT `FKlrujviid9xbegr2i6l8sehv57` FOREIGN KEY (`id_cliente`) REFERENCES `client` (`idClient`),
  CONSTRAINT `FKqwdmn21dlcnv8xehjityqjn27` FOREIGN KEY (`id_service`) REFERENCES `offeredservice` (`idOfferedService`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client_service`
--

LOCK TABLES `client_service` WRITE;
/*!40000 ALTER TABLE `client_service` DISABLE KEYS */;
INSERT INTO `client_service` VALUES (1,1),(1,2),(2,3),(2,2),(3,1),(3,5),(4,4),(4,3),(5,1),(5,2),(6,5),(6,4),(7,3),(7,2),(8,1),(8,5),(9,4),(9,3),(10,2),(10,1),(11,5),(11,4),(12,3),(12,2),(13,1),(13,5),(14,4),(14,3),(15,2),(15,1),(16,1);
/*!40000 ALTER TABLE `client_service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comercialuser`
--

DROP TABLE IF EXISTS `comercialuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comercialuser` (
  `idUser` bigint NOT NULL AUTO_INCREMENT,
  `alta` bit(1) NOT NULL,
  `email` varchar(255) NOT NULL,
  `lastName` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `userName` varchar(255) NOT NULL,
  PRIMARY KEY (`idUser`),
  UNIQUE KEY `UK_toac864vt7lvh7g7jlnqok5bu` (`email`),
  UNIQUE KEY `UK_fnja8uc4r4wdxsuf78talth8n` (`userName`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comercialuser`
--

LOCK TABLES `comercialuser` WRITE;
/*!40000 ALTER TABLE `comercialuser` DISABLE KEYS */;
INSERT INTO `comercialuser` VALUES (1,_binary '','ACRUZ@CONSULTING.COM','CRUZ','ANDREA','ACRUZ'),(2,_binary '','RGOMEZ@CONSULTING.COM','GOMEZ','ROBERTO','RGOMEZ'),(3,_binary '','SLOPEZ@CONSULTING.COM','LOPEZ','SILVIA','SLOPEZ'),(4,_binary '','FPEREZ@CONSULTING.COM','PEREZ','FEDERICO','FPEREZ'),(5,_binary '','AMARTIN@CONSULTING.COM','MARTIN','ANA','AMARTIN'),(6,_binary '','GMOLINA@CONSULTING.COM','MOLINA','GABRIEL','GMOLINA'),(7,_binary '','LRODRIGUEZ@CONSULTING.COM','RODRIGUEZ','LUISA','LRODRIGUEZ'),(8,_binary '','JGUTIERREZ@CONSULTING.COM','GUTIERREZ','JAVIER','JGUTIERREZ'),(9,_binary '','VLUNA@CONSULTING.COM','LUNA','VALERIA','VLUNA'),(10,_binary '','DMENDEZ@CONSULTING.COM','MENDEZ','DIEGO','DMENDEZ'),(11,_binary '','MPEREZ@CONSULTING.COM','PEREZ','MARTIN','MPEREZ');
/*!40000 ALTER TABLE `comercialuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `helpdeskuser`
--

DROP TABLE IF EXISTS `helpdeskuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `helpdeskuser` (
  `idUser` bigint NOT NULL AUTO_INCREMENT,
  `alta` bit(1) NOT NULL,
  `email` varchar(255) NOT NULL,
  `lastName` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `userName` varchar(255) NOT NULL,
  PRIMARY KEY (`idUser`),
  UNIQUE KEY `UK_l4rqffe26loucfci8j098b9e1` (`email`),
  UNIQUE KEY `UK_c97fd0nt1b1tjjcrleqlpyf8x` (`userName`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `helpdeskuser`
--

LOCK TABLES `helpdeskuser` WRITE;
/*!40000 ALTER TABLE `helpdeskuser` DISABLE KEYS */;
INSERT INTO `helpdeskuser` VALUES (1,_binary '','MMARTINEZ@CONSULTING.COM','MARTINEZ','MARIANO','MMARTINEZ'),(2,_binary '','PGONZALEZ@CONSULTING.COM','GONZALEZ','PAULA','PGONZALEZ'),(3,_binary '','JLOPEZ@CONSULTING.COM','LOPEZ','JUAN','JLOPEZ'),(4,_binary '','FSUAREZ@CONSULTING.COM','SUAREZ','FLORENCIA','FSUAREZ'),(5,_binary '','GMENDOZA@CONSULTING.COM','MENDOZA','GASTON','GMENDOZA'),(6,_binary '','AMOLINA@CONSULTING.COM','MOLINA','ANA','AMOLINA'),(7,_binary '','RCRUZ@CONSULTING.COM','CRUZ','ROBERTO','RCRUZ'),(8,_binary '','VGUTIERREZ@CONSULTING.COM','GUTIERREZ','VICTORIA','VGUTIERREZ'),(9,_binary '','DMARTIN@CONSULTING.COM','MARTIN','DANIEL','DMARTIN'),(10,_binary '','EMENDOZA@CONSULTING.COM','MENDOZA','ERIKA','EMENDOZA'),(11,_binary '','LIBANEZ@CONSULTING.COM','IBANEZ','LUCIA','LIBANEZ');
/*!40000 ALTER TABLE `helpdeskuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `incident`
--

DROP TABLE IF EXISTS `incident`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `incident` (
  `idIncident` bigint NOT NULL AUTO_INCREMENT,
  `descriptionProblem` varchar(255) NOT NULL,
  `incidentDate` date NOT NULL,
  `incidentState` varchar(255) NOT NULL,
  `resolutionDate` date DEFAULT NULL,
  `specialistConsiderations` varchar(255) DEFAULT NULL,
  `id_client` bigint NOT NULL,
  `id_offered_service` bigint NOT NULL,
  `id_specialist` bigint NOT NULL,
  `id_type_problem` bigint NOT NULL,
  `incidentcol` varchar(45) NOT NULL,
  PRIMARY KEY (`idIncident`),
  KEY `FK3h2sncus6u6ys7o04u2wdako6` (`id_offered_service`),
  KEY `FK4ngb76qfb3y9p0xpewuxd64b4` (`id_client`),
  KEY `FKhywtdos1b4n9rt8shrk34wfne` (`id_type_problem`),
  KEY `FKjgs6nbi2qg5c1dlsb07s8jwcd` (`id_specialist`),
  CONSTRAINT `FK3h2sncus6u6ys7o04u2wdako6` FOREIGN KEY (`id_offered_service`) REFERENCES `offeredservice` (`idOfferedService`),
  CONSTRAINT `FK4ngb76qfb3y9p0xpewuxd64b4` FOREIGN KEY (`id_client`) REFERENCES `client` (`idClient`),
  CONSTRAINT `FKhywtdos1b4n9rt8shrk34wfne` FOREIGN KEY (`id_type_problem`) REFERENCES `problemtype` (`idTypeProblem`),
  CONSTRAINT `FKjgs6nbi2qg5c1dlsb07s8jwcd` FOREIGN KEY (`id_specialist`) REFERENCES `specialistuser` (`idUser`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `incident`
--

LOCK TABLES `incident` WRITE;
/*!40000 ALTER TABLE `incident` DISABLE KEYS */;
INSERT INTO `incident` VALUES (1,'Problema de red en la conexión SAP','2023-10-05','RESUELTO','2023-10-23','una especificacion del especialista',1,1,1,1,''),(2,'Error al cargar datos en SAP','2023-10-06','RESUELTO','2023-10-24','una especificacion del especialista',2,1,2,2,''),(3,'Problema de rendimiento en SAP','2023-10-07','RESUELTO','2023-10-25','una especificacion del especialista',3,1,3,3,''),(4,'Error de conexión en Tango','2023-10-08','RESUELTO','2023-10-26','una especificacion del especialista',4,2,4,4,''),(5,'Tango se cierra inesperadamente','2023-10-09','RESUELTO','2023-10-27','una especificacion del especialista',5,2,5,5,''),(6,'Problema de configuración en Windows','2023-10-10','RESUELTO','2023-10-28','una especificacion del especialista',6,3,6,6,''),(7,'Pantalla azul en Windows','2023-10-11','RESUELTO','2023-10-29','una especificacion del especialista',7,3,7,7,''),(8,'Problema de rendimiento en macOS','2023-10-12','RESUELTO','2023-10-30','una especificacion del especialista',8,4,8,8,''),(9,'Error de configuración en Linux','2023-10-13','RESUELTO','2023-10-31','una especificacion del especialista',9,5,9,9,''),(10,'Problema de red en Linux','2023-10-14','RESUELTO','2023-11-01','una especificacion del especialista',10,5,10,10,''),(11,'Problema de configuración en Linux','2023-10-15','RESUELTO','2023-11-02','una especificacion del especialista',11,5,5,11,''),(12,'Error de configuración en macOS','2023-10-16','RESUELTO','2023-11-03','una especificacion del especialista',12,4,5,12,''),(13,'Error de actualización en Windows','2023-10-17','RESUELTO','2023-11-04','una especificacion del especialista',13,3,5,13,''),(14,'Error de rendimiento en Windows','2023-10-18','RESUELTO','2023-11-05','una especificacion del especialista',14,3,5,14,''),(15,'Error al ejecutar informe en Tango','2023-10-19','RESUELTO','2023-11-06','una especificacion del especialista',15,2,5,15,''),(16,'Problema de impresión en Tango','2023-10-20','RESUELTO','2023-11-07','una especificacion del especialista',1,2,5,16,''),(17,'Problema de compatibilidad en macOS','2023-10-21','RESUELTO','2023-11-08','una especificacion del especialista',2,4,5,17,''),(18,'Error de instalación en Linux','2023-10-22','RESUELTO','2023-11-09','una especificacion del especialista',3,5,5,18,''),(19,'Problema de red en macOS','2023-10-23','RESUELTO','2023-11-10','una especificacion del especialista',4,4,5,19,''),(20,'Problema de compatibilidad en Linux','2023-10-24','RESUELTO','2023-11-11','una especificacion del especialista',5,5,5,20,''),(21,'Error crítico en la conexión SAP','2023-10-25','RESUELTO','2023-11-12','una especificacion del especialista',6,1,5,1,''),(22,'Fallo en la actualización de datos en SAP','2023-10-26','RESUELTO','2023-11-13','una especificacion del especialista',7,1,5,2,''),(23,'Problema de rendimiento crítico en SAP','2023-10-27','RESUELTO','2023-11-14','una especificacion del especialista',8,1,5,3,''),(24,'Error grave de conexión en Tango','2023-10-28','RESUELTO','2023-11-15','una especificacion del especialista',9,2,6,4,''),(25,'Tango se cierra inesperadamente durante operaciones críticas','2023-10-29','RESUELTO','2023-11-16','una especificacion del especialista',10,2,6,5,''),(26,'Problema de configuración avanzada en Windows','2023-10-30','RESUELTO','2023-11-17','una especificacion del especialista',11,3,6,6,''),(27,'Windows experimenta pantalla azul en situaciones específicas','2023-10-31','RESUELTO','2023-11-18','una especificacion del especialista',12,4,6,7,''),(28,'MacOS muestra rendimiento inesperado en ciertos contextos','2023-11-01','RESUELTO','2023-11-19','una especificacion del especialista',13,4,6,8,''),(29,'Error crítico de configuración en Linux','2023-11-02','RESUELTO','2023-11-20','una especificacion del especialista',14,5,7,9,''),(30,'Problema de red grave en Linux','2023-11-03','RESUELTO','2023-11-21','una especificacion del especialista',15,5,7,10,''),(31,'Problema de compatibilidad avanzada en Linux','2023-11-04','RESUELTO','2023-11-22','una especificacion del especialista',1,5,7,11,''),(32,'Error crítico de configuración en macOS','2023-11-05','RESUELTO','2023-11-23','una especificacion del especialista',2,4,7,12,''),(33,'Fallo crítico en la actualización de macOS','2023-11-06','RESUELTO','2023-11-24','una especificacion del especialista',3,4,6,13,''),(34,'Error de rendimiento grave en Windows','2023-11-07','RESUELTO','2023-11-25','una especificacion del especialista',4,3,8,14,''),(35,'Fallo en la generación de informe en Tango','2023-11-08','RESUELTO','2023-11-26','una especificacion del especialista',1,2,8,15,''),(36,'Problema de impresión crítico en Tango','2023-11-09','RESUELTO','2023-11-27','una especificacion del especialista',2,4,5,16,''),(37,'Incompatibilidad avanzada en macOS','2023-11-10','RESUELTO','2023-11-28','una especificacion del especialista',3,4,8,17,''),(38,'Error grave de instalación en Linux','2023-11-11','RESUELTO','2023-11-29','una especificacion del especialista',4,5,4,18,''),(39,'Problema crítico de red en macOS','2023-11-12','RESUELTO','2023-11-30','una especificacion del especialista',1,4,4,19,''),(40,'Incompatibilidad avanzada en Linux','2023-11-13','RESUELTO','2023-12-01','una especificacion del especialista',2,5,4,20,''),(41,'Problema de conexión SAP en horario pico','2023-11-14','RESUELTO','2023-12-02','una especificacion del especialista',3,1,5,1,''),(42,'Error de carga de datos en SAP durante operación crucial','2023-11-15','RESUELTO','2023-12-03','una especificacion del especialista',4,1,6,2,''),(43,'Rendimiento inusualmente bajo en SAP','2023-11-16','RESUELTO','2023-12-04','una especificacion del especialista',5,1,7,3,''),(44,'Falla crítica en la conexión Tango','2023-11-17','RECIBIDO','2023-12-05','una especificacion del especialista',6,2,5,4,''),(45,'Tango se cierra inesperadamente al abrir múltiples ventanas','2023-11-18','RECIBIDO','2023-12-06','una especificacion del especialista',7,2,5,5,''),(46,'Problema de configuración específica en Windows','2023-11-19','RECIBIDO','2023-12-07','una especificacion del especialista',8,3,4,6,''),(47,'Error recurrente de pantalla azul en Windows','2023-11-20','RECIBIDO','2023-12-08','una especificacion del especialista',1,3,7,7,''),(48,'MacOS muestra lentitud en el rendimiento en ciertos escenarios','2023-11-21','RECIBIDO','2023-12-09','una especificacion del especialista',2,4,7,8,''),(49,'Configuración avanzada con errores en Linux','2023-11-22','RECIBIDO','2023-12-10','una especificacion del especialista',3,5,7,9,''),(50,'Problema de red persistente en Linux','2023-11-23','RECIBIDO','2023-12-11','una especificacion del especialista',3,5,7,10,''),(51,'Incompatibilidad de software avanzada en Linux','2023-11-24','RECIBIDO','2023-12-12','una especificacion del especialista',3,5,7,11,''),(52,'Error crítico de configuración en macOS','2023-11-25','RECIBIDO','2023-12-13','una especificacion del especialista',3,4,7,12,''),(53,'Fallo crítico en la actualización de macOS','2023-11-26','RECIBIDO','2023-12-14','una especificacion del especialista',3,4,7,13,''),(54,'Problema de rendimiento persistente en Windows','2023-11-27','RECIBIDO','2023-12-15','una especificacion del especialista',3,3,7,14,''),(55,'Fallo en la generación de informes críticos en Tango','2023-11-28','RECIBIDO','2023-12-16','una especificacion del especialista',4,2,7,15,''),(56,'Problema de impresión persistente en Tango','2023-11-29','RECIBIDO','2023-12-17','una especificacion del especialista',4,2,7,16,''),(57,'Incompatibilidad avanzada en macOS','2023-11-30','RECIBIDO','2023-12-18','una especificacion del especialista',4,4,7,17,''),(58,'Error grave de instalación en Linux','2023-12-01','RECIBIDO','2023-12-19','una especificacion del especialista',4,5,7,18,''),(59,'Problema crítico de red en macOS','2023-12-02','RECIBIDO','2023-12-20','una especificacion del especialista',4,4,7,19,''),(60,'Incompatibilidad de software avanzada en Linux','2023-12-03','RECIBIDO','2023-12-21','una especificacion del especialista',4,5,7,20,'');
/*!40000 ALTER TABLE `incident` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `offeredservice`
--

DROP TABLE IF EXISTS `offeredservice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `offeredservice` (
  `idOfferedService` bigint NOT NULL AUTO_INCREMENT,
  `offeredServiceName` varchar(255) NOT NULL,
  PRIMARY KEY (`idOfferedService`),
  UNIQUE KEY `UK_1ckx0sjmjrw782u4f3ouwtcmx` (`offeredServiceName`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offeredservice`
--

LOCK TABLES `offeredservice` WRITE;
/*!40000 ALTER TABLE `offeredservice` DISABLE KEYS */;
INSERT INTO `offeredservice` VALUES (5,'Linux Ubuntu'),(4,'MacOs'),(1,'SAP'),(2,'Tango'),(3,'Windows');
/*!40000 ALTER TABLE `offeredservice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `problemtype`
--

DROP TABLE IF EXISTS `problemtype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `problemtype` (
  `idTypeProblem` bigint NOT NULL AUTO_INCREMENT,
  `daysToResolution` int DEFAULT NULL,
  `problemTypeName` varchar(255) NOT NULL,
  `id_offered_service` bigint NOT NULL,
  PRIMARY KEY (`idTypeProblem`),
  KEY `FKt0f41kxba9pqe0c3hc122gidr` (`id_offered_service`),
  CONSTRAINT `FKt0f41kxba9pqe0c3hc122gidr` FOREIGN KEY (`id_offered_service`) REFERENCES `offeredservice` (`idOfferedService`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `problemtype`
--

LOCK TABLES `problemtype` WRITE;
/*!40000 ALTER TABLE `problemtype` DISABLE KEYS */;
INSERT INTO `problemtype` VALUES (1,5,'Problema con SAP en la red',1),(2,3,'Error de conexión en SAP',1),(3,4,'Problema de rendimiento en SAP',1),(4,2,'Error al cargar datos en SAP',1),(5,6,'Problema de autenticación en SAP',1),(6,4,'Error en Tango al ejecutar informe',2),(7,3,'Problema de impresión en Tango',2),(8,5,'Tango se cierra inesperadamente',2),(9,2,'Error de conexión en Tango',2),(10,6,'Problema de configuración en Tango',2),(11,2,'Problema de compatibilidad en Windows',3),(12,5,'Error de actualización en Windows',3),(13,3,'Problema de red en Windows',3),(14,4,'Pantalla azul en Windows',3),(15,6,'Problema de rendimiento en Windows',3),(16,4,'Problema de configuración en macOS',4),(17,3,'Error de actualización en macOS',4),(18,5,'Problema de rendimiento en macOS',4),(19,2,'Problema de red en macOS',4),(20,6,'Problema de compatibilidad en macOS',4),(21,6,'Error de instalación en Linux',5),(22,4,'Problema de red en Linux',5),(23,3,'Problema de compatibilidad en Linux',5),(24,5,'Error de configuración en Linux',5),(25,2,'Problema de rendimiento en Linux',5);
/*!40000 ALTER TABLE `problemtype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rrhhuser`
--

DROP TABLE IF EXISTS `rrhhuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rrhhuser` (
  `idUser` bigint NOT NULL AUTO_INCREMENT,
  `alta` bit(1) NOT NULL,
  `email` varchar(255) NOT NULL,
  `lastName` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `userName` varchar(255) NOT NULL,
  PRIMARY KEY (`idUser`),
  UNIQUE KEY `UK_o5uox7c7bxrco7ujcyj7u8bvx` (`email`),
  UNIQUE KEY `UK_ddw1ad35eu10gvtfglvnslxry` (`userName`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rrhhuser`
--

LOCK TABLES `rrhhuser` WRITE;
/*!40000 ALTER TABLE `rrhhuser` DISABLE KEYS */;
INSERT INTO `rrhhuser` VALUES (1,_binary '','JLOPEZ@CONSULTING.COM','LOPEZ','JOSE','JLOPEZ'),(2,_binary '','SGARCIA@CONSULTING.COM','GARCIA','SOFIA','SGARCIA'),(3,_binary '','HRODRIGUEZ@CONSULTING.COM','RODRIGUEZ','HUGO','HRODRIGUEZ'),(4,_binary '','PMARTINEZ@CONSULTING.COM','MARTINEZ','PAOLA','PMARTINEZ'),(5,_binary '','FGOMEZ@CONSULTING.COM','GOMEZ','FEDERICO','FGOMEZ'),(6,_binary '','LMOLINA@CONSULTING.COM','MOLINA','LAURA','LMOLINA'),(7,_binary '\0','RCRUZ@CONSULTING.COM','CRUZ','ROBERTO','RCRUZ'),(8,_binary '','VGUTIERREZ@CONSULTING.COM','GUTIERREZ','VICTORIA','VGUTIERREZ'),(9,_binary '','DMARTIN@CONSULTING.COM','MARTIN','DANIEL','DMARTIN'),(10,_binary '','EMENDOZA@CONSULTING.COM','MENDOZA','ERIKA','EMENDOZA'),(11,_binary '','DGIMENEZ@CONSULTING.COM','GIMENEZ','DAMIAN','DGIMENEZ'),(12,_binary '\0','IHUDSON@CONSULTING.COM','HUDSON','ISMAEL DAMIAN','IHUDSON');
/*!40000 ALTER TABLE `rrhhuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `specialist_offeredservice`
--

DROP TABLE IF EXISTS `specialist_offeredservice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `specialist_offeredservice` (
  `id_user` bigint NOT NULL,
  `id_offered_service` bigint NOT NULL,
  KEY `FK6fgwx7a4udt8fbm570iyml4e1` (`id_offered_service`),
  KEY `FKgq5e5tq6ia9rc2f37w2hjuwmu` (`id_user`),
  CONSTRAINT `FK6fgwx7a4udt8fbm570iyml4e1` FOREIGN KEY (`id_offered_service`) REFERENCES `offeredservice` (`idOfferedService`),
  CONSTRAINT `FKgq5e5tq6ia9rc2f37w2hjuwmu` FOREIGN KEY (`id_user`) REFERENCES `specialistuser` (`idUser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `specialist_offeredservice`
--

LOCK TABLES `specialist_offeredservice` WRITE;
/*!40000 ALTER TABLE `specialist_offeredservice` DISABLE KEYS */;
INSERT INTO `specialist_offeredservice` VALUES (1,1),(1,2),(2,2),(2,3),(3,3),(3,4),(4,5),(4,1),(5,1),(5,2),(6,3),(6,4),(7,5),(7,1),(8,2),(8,3),(9,4),(9,5),(10,1),(10,2),(11,1),(11,3);
/*!40000 ALTER TABLE `specialist_offeredservice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `specialistuser`
--

DROP TABLE IF EXISTS `specialistuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `specialistuser` (
  `idUser` bigint NOT NULL AUTO_INCREMENT,
  `alta` bit(1) NOT NULL,
  `email` varchar(255) NOT NULL,
  `lastName` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `userName` varchar(255) NOT NULL,
  PRIMARY KEY (`idUser`),
  UNIQUE KEY `UK_ibgc6shveo235m5vr4u4v1xit` (`email`),
  UNIQUE KEY `UK_8x83nvpheut8a872haxidl0yh` (`userName`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `specialistuser`
--

LOCK TABLES `specialistuser` WRITE;
/*!40000 ALTER TABLE `specialistuser` DISABLE KEYS */;
INSERT INTO `specialistuser` VALUES (1,_binary '','AMARTINEZ@CONSULTING.COM','MARTINEZ','ALEJANDRO','AMARTINEZ'),(2,_binary '','LGONZALEZ@CONSULTING.COM','GONZALEZ','LAURA','LGONZALEZ'),(3,_binary '','JRODRIGUEZ@CONSULTING.COM','RODRIGUEZ','JUAN','JRODRIGUEZ'),(4,_binary '','FSUAREZ@CONSULTING.COM','SUAREZ','FERNANDA','FSUAREZ'),(5,_binary '','MMENDOZA@CONSULTING.COM','MENDOZA','MIGUEL','MMENDOZA'),(6,_binary '','PCASTRO@CONSULTING.COM','CASTRO','PAULA','PCASTRO'),(7,_binary '','RHERRERA@CONSULTING.COM','HERRERA','RAMIRO','RHERRERA'),(8,_binary '','VLOPEZ@CONSULTING.COM','LOPEZ','VALENTINA','VLOPEZ'),(9,_binary '','JFERNANDEZ@CONSULTING.COM','FERNANDEZ','JAVIER','JFERNANDEZ'),(10,_binary '','SRAMIREZ@CONSULTING.COM','RAMIREZ','SOFIA','SRAMIREZ'),(11,_binary '','JMGONZALEZ@CONSULTING.COM','GONZALEZ','JUAN MANUEL','JMGONZALEZ');
/*!40000 ALTER TABLE `specialistuser` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-04 19:29:31
