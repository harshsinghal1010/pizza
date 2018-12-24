-- MySQL dump 10.13  Distrib 5.7.22-ndb-7.6.6, for Linux (x86_64)
--
-- Host: localhost    Database: pizzeria
-- ------------------------------------------------------
-- Server version	5.7.22-ndb-7.6.6

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `crust`
--

DROP TABLE IF EXISTS `crust`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `crust` (
  `crustId` int(11) NOT NULL AUTO_INCREMENT,
  `crust_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`crustId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `crust`
--

LOCK TABLES `crust` WRITE;
/*!40000 ALTER TABLE `crust` DISABLE KEYS */;
INSERT INTO `crust` VALUES (1,'FRESH_PAN'),(2,'THIN'),(3,'CHEESE_BURST');
/*!40000 ALTER TABLE `crust` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `orderId` int(11) NOT NULL AUTO_INCREMENT,
  `order_name` varchar(255) DEFAULT NULL,
  `pizza` int(11) DEFAULT NULL,
  PRIMARY KEY (`orderId`),
  KEY `FK5ydmgklchj1aj01e205yqx5en` (`pizza`),
  CONSTRAINT `FK5ydmgklchj1aj01e205yqx5en` FOREIGN KEY (`pizza`) REFERENCES `pizza` (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pizza`
--

DROP TABLE IF EXISTS `pizza`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pizza` (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `pizzaName` varchar(255) DEFAULT NULL,
  `pizza_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pizza`
--

LOCK TABLES `pizza` WRITE;
/*!40000 ALTER TABLE `pizza` DISABLE KEYS */;
INSERT INTO `pizza` VALUES (1,'Chilli Garlic','INDIAN'),(2,'Mozzerilla','ITALIAN');
/*!40000 ALTER TABLE `pizza` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pizza_crust`
--

DROP TABLE IF EXISTS `pizza_crust`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pizza_crust` (
  `piz_id` int(11) NOT NULL,
  `crust_id` int(11) NOT NULL,
  KEY `FKagtoepn4l5wt6jt4x0de8jo8u` (`crust_id`),
  KEY `FKe5jpqoq8ayeq73m4hcv964qvd` (`piz_id`),
  CONSTRAINT `FKagtoepn4l5wt6jt4x0de8jo8u` FOREIGN KEY (`crust_id`) REFERENCES `crust` (`crustId`),
  CONSTRAINT `FKe5jpqoq8ayeq73m4hcv964qvd` FOREIGN KEY (`piz_id`) REFERENCES `pizza` (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pizza_crust`
--

LOCK TABLES `pizza_crust` WRITE;
/*!40000 ALTER TABLE `pizza_crust` DISABLE KEYS */;
INSERT INTO `pizza_crust` VALUES (1,1),(1,2),(1,3),(2,1),(2,2),(2,3);
/*!40000 ALTER TABLE `pizza_crust` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pizza_topping`
--

DROP TABLE IF EXISTS `pizza_topping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pizza_topping` (
  `pizza_id` int(11) NOT NULL,
  `toppings_id` int(11) NOT NULL,
  KEY `FKsgietulr8xyc6m5ohqkcb6h56` (`toppings_id`),
  KEY `FKdrsqb61c24ogsdowf37v9yu5` (`pizza_id`),
  CONSTRAINT `FKdrsqb61c24ogsdowf37v9yu5` FOREIGN KEY (`pizza_id`) REFERENCES `pizza` (`pid`),
  CONSTRAINT `FKsgietulr8xyc6m5ohqkcb6h56` FOREIGN KEY (`toppings_id`) REFERENCES `topping` (`topping_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pizza_topping`
--

LOCK TABLES `pizza_topping` WRITE;
/*!40000 ALTER TABLE `pizza_topping` DISABLE KEYS */;
INSERT INTO `pizza_topping` VALUES (1,1),(1,2),(1,3),(1,5),(1,6),(2,1),(2,2),(2,3);
/*!40000 ALTER TABLE `pizza_topping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topping`
--

DROP TABLE IF EXISTS `topping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `topping` (
  `topping_id` int(11) NOT NULL AUTO_INCREMENT,
  `topping_name` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  PRIMARY KEY (`topping_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topping`
--

LOCK TABLES `topping` WRITE;
/*!40000 ALTER TABLE `topping` DISABLE KEYS */;
INSERT INTO `topping` VALUES (1,'RED_PEPPER',64),(2,'PANEER',74),(3,'OLIVES',94),(5,'PEPPERONI',73),(6,'MUSHROOMS',50);
/*!40000 ALTER TABLE `topping` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-08-09 13:31:09
