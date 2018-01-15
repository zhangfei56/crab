-- MySQL dump 10.13  Distrib 5.7.20, for Win64 (x86_64)
--
-- Host: localhost    Database: crab
-- ------------------------------------------------------
-- Server version	5.7.20

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
-- Current Database: `crab`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `crab` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `crab`;

--
-- Table structure for table `courier_company`
--

DROP TABLE IF EXISTS `courier_company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `courier_company` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `common` bit(1) NOT NULL,
  `number` varchar(255) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courier_company`
--

LOCK TABLES `courier_company` WRITE;
/*!40000 ALTER TABLE `courier_company` DISABLE KEYS */;
INSERT INTO `courier_company` VALUES (1,'Aramex',NULL,'\0','30288063886','400-631-8388','ARAMEX'),(2,'安能快递',NULL,'\0','30000014025846','','ANEEX'),(3,'安捷',NULL,'\0','AJ80862351','400-619-7776','ANJELEX'),(4,'安能',NULL,'\0','220014594841','40010-40088','ANE'),(5,'澳邮',NULL,'\0','ZA02610043077','','AUEXPRESS'),(6,'AAE',NULL,'\0','131685763','400-6100-400 021-51008888','AAEWEB'),(7,'安信达',NULL,'\0','','021-54224681','ANXINDA'),(8,'百福东方',NULL,'\0','','400-706-0609','EES'),(9,'蓝天',NULL,'\0','BSD211640','0061-3-94950283','BLUESKY'),(10,'黑狗',NULL,'\0','1002868661','400-106-1234','BLACKDOG'),(11,'百世快运',NULL,'\0','10288328982','','BSKY'),(12,'秦远物流',NULL,'\0','6071706513739','','CHINZ56'),(13,'程光',NULL,'\0','100001362043','0064-09-2759536','FLYWAYEX'),(14,'德邦',NULL,'\0','5180435727','95353','DEPPON'),(15,'DPEX',NULL,'\0','','0755-88297707','DPEX'),(16,'D速',NULL,'\0','616906328376','0531-88636363','DEXP'),(17,'DHL',NULL,'\0','5846399171','800-810-8000 400-810-8000','DHL'),(18,'大田',NULL,'\0','','400-626-1166','DTW'),(19,'东骏物流',NULL,'\0','2994861011569','','DJ56'),(20,'EMS',NULL,'\0','9572253781500','40080-11183','EMS'),(21,'平安快递',NULL,'\0','EF990157553NZ','0773-2315320','EFSPOST'),(22,'EWE',NULL,'\0','mhu00061','1300-09-6655','EWE'),(23,'FedEx国际',NULL,'\0','808446554948','800-988-1888 400-886-1888','FEDEXIN'),(24,'富腾达',NULL,'\0','NZ1200523','0064-09-4432342','FTD'),(25,'FedEx',NULL,'\0','120949498648','800-988-1888 400-886-1888','FEDEX'),(26,'凤凰',NULL,'\0','','010-85826200','PHOENIXEXP'),(27,'国通',NULL,'','2840737926','4001-111-123','GTO'),(28,'能达',NULL,'\0','','400-6886-765','ND56'),(29,'共速达',NULL,'\0','','400-111-0005','GSD'),(30,'飞洋',NULL,'\0','GC501115760US','010-87785733','GCE'),(31,'百世快递',NULL,'','350630538314','4009565656','HTKY'),(32,'鸿远',NULL,'\0','LB603036351US','','HYE'),(33,'黄马甲',NULL,'\0','612007013934946','','HUANGMAJIA'),(34,'锦程快递',NULL,'\0','63760347','','HREX'),(35,'恒路',NULL,'\0','03122576','400-182-6666','HENGLU'),(36,'华企',NULL,'\0','','400-626-2356','HQKY'),(37,'嘉里物流',NULL,'\0','316B455817673','852-2410-3600','KERRY'),(38,'佳吉',NULL,'\0','729976312','400-820-5566','JIAJI'),(39,'九曳',NULL,'\0','JY0001964522','400-6199-939','JIUYESCM'),(40,'京东',NULL,'\0','12290972964','','JD'),(41,'京广',NULL,'\0','7102293245','852-23329918','KKE'),(42,'佳怡',NULL,'\0','','400-631-9999','JIAYI'),(43,'加运美',NULL,'\0','2197050107','0769-85515555 ','TMS'),(44,'急先达',NULL,'\0','','400-694-1256','JOUST'),(45,'晋越',NULL,'\0','','台北：+886-2-25016988澳门：00853-28520722福建：0592-5569715广东：0769-88763939','PEWKEE'),(46,'跨越',NULL,'\0','2628904','4008-098-098','KYEXPRESS'),(47,'货运皇',NULL,'\0','KF400023AU','','KINGFREIGHT'),(48,'快捷',NULL,'\0','536135784093','4008-333-666','FASTEXPRESS'),(49,'龙邦',NULL,'\0','686013186447','021-59218889','LBEX'),(50,'联昊通',NULL,'\0','236012014633','400-888-8887','LTS'),(51,'民航',NULL,'\0','CAE602232295','4008-17-4008','CAE'),(52,'配思航宇',NULL,'\0','','010-65489928','PEISI'),(53,'PCA',NULL,'\0','EAU839001631','1800518000','PCA'),(54,'全峰',NULL,'\0','720166045326','4001-000-001','QFKD'),(55,'全一',NULL,'\0','112393742650','400-663-1111','APEX'),(56,'全晨',NULL,'\0','2233244233','0769-82026703','QCKD'),(57,'如风达',NULL,'\0','6800000635515','400-010-6660','RFD'),(58,'顺丰',NULL,'\0','952727764582','95338','SFEXPRESS'),(59,'申通',NULL,'','403234843091','95543','STO'),(60,'苏宁',NULL,'\0','SN0030000041011500','95315','SUNING'),(61,'顺达快递',NULL,'\0','SD100330011','','SDEX'),(62,'三态',NULL,'\0','','400-881-8106','SFC'),(63,'盛辉',NULL,'\0','238601965','400-822-2222','SHENGHUI'),(64,'盛丰',NULL,'\0','39562381','4008-556688','SFWL'),(65,'速尔',NULL,'\0','880218258595','400-158-9888','SURE'),(66,'天天',NULL,'\0','560623753489','4001-888-888','TTKDEX'),(67,'TNT',NULL,'\0','335939905','800-820-9868','TNT'),(68,'天地华宇',NULL,'\0','020286402','400-808-6666','HOAU'),(69,'UPS',NULL,'\0','1ZV6509Y0468336755','800-820-8388 400-820-8388','UPS'),(70,'万庚',NULL,'\0','000000092332','','VANGEN'),(71,'文捷航空',NULL,'\0','','020-36680069','GZWENJIE'),(72,'万象',NULL,'\0','2225195562855','400-820-8088','EWINSHINE'),(73,'万家物流',NULL,'\0','31000001425628','4001-156-561','WANJIA'),(74,'信丰',NULL,'\0','137417768454','4008-306-333','XFEXPRESS'),(75,'新邦',NULL,'\0','27454784','4008-000-222','XBWL'),(76,'圆通',NULL,'\0','100668657244','021-69777888 021-69777999','YTO'),(77,'韵达',NULL,'\0','1202237859178','95546','YUNDA'),(78,'邮政包裹',NULL,'\0','9610027635439','11185','CHINAPOST'),(79,'运通',NULL,'\0','666316719','0769-81156999','YTEXPRESS'),(80,'越丰',NULL,'\0','','(852) 2390 9969 ','YFEXPRESS'),(81,'远成',NULL,'\0','300011079526','400-820-1646','YCGWL'),(82,'亚风',NULL,'\0','50042647157','4001-000-002','BROADASIA'),(83,'优速',NULL,'\0','518148752202','400-1111-119','UC56'),(84,'原飞航',NULL,'\0','8090337382','0755-29778899 / 29778100','YFHEX'),(85,'源安达',NULL,'\0','','0769-85021875','YADEX'),(86,'宜送',NULL,'\0','0125194699','','YIEXPRESS'),(87,'易通达',NULL,'\0','8000199455','0898-65339299','ETD'),(88,'易达通',NULL,'\0','ZY030841509NZ','0064-09-8388681','QEXPRESS'),(89,'中通',NULL,'\0','421447644512','95311','ZTO'),(90,'宅急送',NULL,'\0','A002083939830','400-6789-000','ZJS'),(91,'中通快运',NULL,'\0','201605182527','4000-270-270','ZTO56'),(92,'中铁物流',NULL,'\0','119005886864','400-000-5566','ZTKY'),(93,'芝麻开门',NULL,'\0','611008025181750','4001-056-056','ZMKMEX'),(94,'中国东方',NULL,'\0','','755-83575000','COE'),(95,'中邮',NULL,'\0','NE88379114242','11183','CNPL'),(96,'中铁快运',NULL,'\0','k19110633973','95572','CRE');
/*!40000 ALTER TABLE `courier_company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_form`
--

DROP TABLE IF EXISTS `order_form`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_form` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `create_date_time` datetime DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `contact` varchar(255) DEFAULT NULL,
  `courier_company_type` varchar(255) DEFAULT NULL,
  `need_date_time` datetime DEFAULT NULL,
  `tracking_number` varchar(255) DEFAULT NULL,
  `voucher_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6wjoy2xx2w2qkjgelagbu1556` (`voucher_id`),
  CONSTRAINT `FK6wjoy2xx2w2qkjgelagbu1556` FOREIGN KEY (`voucher_id`) REFERENCES `voucher` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_form`
--

LOCK TABLES `order_form` WRITE;
/*!40000 ALTER TABLE `order_form` DISABLE KEYS */;
INSERT INTO `order_form` VALUES (1,'','2017-12-16 00:03:52','',NULL,'','GTO','2017-12-07 08:00:00',NULL,10),(2,'sdff','2018-01-05 19:24:22','342',NULL,'sfsf','GTO','2018-01-26 08:00:00',NULL,8),(3,'sfdf','2018-01-05 19:31:25','sdf',NULL,'sfd','STO','2018-01-18 08:00:00',NULL,9),(4,'eqw','2018-01-05 19:36:48','qwe',NULL,'qwe','STO','2018-01-26 08:00:00','888793503397',6),(5,'s','2018-01-09 19:16:03','12',NULL,'s','GTO','2018-01-09 08:00:00','123',17);
/*!40000 ALTER TABLE `order_form` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_USER'),(2,'ROLE_ADMIN');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `template`
--

DROP TABLE IF EXISTS `template`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `template` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `company_name` varchar(255) DEFAULT NULL,
  `contact` varchar(255) DEFAULT NULL,
  `owner` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `template`
--

LOCK TABLES `template` WRITE;
/*!40000 ALTER TABLE `template` DISABLE KEYS */;
INSERT INTO `template` VALUES (1,'大闸蟹gggwerf','刘先生','sfdf','1233',1);
/*!40000 ALTER TABLE `template` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `expire_date` datetime DEFAULT NULL,
  `head_pic` varchar(255) DEFAULT NULL,
  `image_code` blob,
  `is_membership` tinyint(4) DEFAULT '0',
  `mark_for_delete` tinyint(4) DEFAULT '0',
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `register_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,NULL,'123',NULL,NULL,NULL,0,0,'123','41df2a7e31cb441d7ea6fde30e2eb8fa','123',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_roles` (
  `user_id` int(11) NOT NULL,
  `roles_id` int(11) NOT NULL,
  KEY `FKj9553ass9uctjrmh0gkqsmv0d` (`roles_id`),
  KEY `FK55itppkw3i07do3h7qoclqd4k` (`user_id`),
  CONSTRAINT `FK55itppkw3i07do3h7qoclqd4k` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKj9553ass9uctjrmh0gkqsmv0d` FOREIGN KEY (`roles_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (1,1),(1,2);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `voucher`
--

DROP TABLE IF EXISTS `voucher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `voucher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `active_date_time` datetime DEFAULT NULL,
  `courier_company_id` int(11) DEFAULT NULL,
  `create_date_time` datetime DEFAULT NULL,
  `identity_code` varchar(255) DEFAULT NULL,
  `order_id` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `tracking_number` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `voucher_category_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsv8yg4d7090cy83oggl1juj71` (`voucher_category_id`),
  CONSTRAINT `FKsv8yg4d7090cy83oggl1juj71` FOREIGN KEY (`voucher_category_id`) REFERENCES `voucher_category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `voucher`
--

LOCK TABLES `voucher` WRITE;
/*!40000 ALTER TABLE `voucher` DISABLE KEYS */;
INSERT INTO `voucher` VALUES (1,'2017-12-31 08:00:00',NULL,'2017-12-11 18:41:12','1374528286',NULL,0,NULL,1,1),(2,'2017-12-31 08:00:00',NULL,'2017-12-11 18:41:12','1736273297',NULL,0,NULL,1,1),(3,'2017-12-31 08:00:00',NULL,'2017-12-11 18:41:12','108904541',NULL,0,NULL,1,1),(4,'2017-12-31 08:00:00',NULL,'2017-12-11 18:41:12','2065966800',NULL,0,NULL,1,1),(5,'2017-12-15 08:00:00',NULL,'2017-12-11 18:46:59','1387192589',NULL,0,NULL,1,1),(6,'2017-12-17 08:00:00',NULL,'2017-12-14 20:07:28','eypHDubfv7',NULL,3,NULL,1,1),(7,'2017-12-15 08:00:00',NULL,'2017-12-14 20:08:07','GH4564OYo8',NULL,0,NULL,1,1),(8,'2017-12-02 08:00:00',NULL,'2017-12-15 21:41:30','33XDFZWD1v',NULL,3,NULL,1,1),(9,'2017-12-02 08:00:00',NULL,'2017-12-15 21:41:30','Iq9HXjBcR3',NULL,2,NULL,1,1),(10,'2017-11-30 08:00:00',NULL,'2017-12-15 21:41:43','FjYke3epbs',NULL,3,NULL,1,2),(11,'2017-11-30 08:00:00',NULL,'2017-12-15 21:41:43','Cac88v70u5',NULL,2,NULL,1,2),(12,'2018-01-18 08:00:00',NULL,'2018-01-06 09:50:23','Md66SN8c7u',NULL,0,NULL,1,1),(13,'2018-01-18 08:00:00',NULL,'2018-01-06 09:50:24','K28zf178Kp',NULL,0,NULL,1,1),(14,'2018-01-18 08:00:00',NULL,'2018-01-06 09:50:24','6Xjb3B7pO7',NULL,0,NULL,1,1),(15,'2018-01-18 08:00:00',NULL,'2018-01-06 09:50:24','3lKtR91OT6',NULL,0,NULL,1,1),(16,'2018-01-18 08:00:00',NULL,'2018-01-06 09:50:24','6657m71MB4',NULL,0,NULL,1,1),(17,'2018-01-18 08:00:00',NULL,'2018-01-06 09:50:24','L31UeEV79P',NULL,2,NULL,1,1),(18,'2018-01-18 08:00:00',NULL,'2018-01-06 09:50:24','xq1ZrzeCBP',NULL,0,NULL,1,1),(19,'2018-01-18 08:00:00',NULL,'2018-01-06 09:50:24','60D9oHAsJ3',NULL,0,NULL,1,1),(20,'2018-01-18 08:00:00',NULL,'2018-01-06 09:50:24','0qKrkNLZ03',NULL,0,NULL,1,1),(21,'2018-01-18 08:00:00',NULL,'2018-01-06 09:50:24','m9JCLT6sI5',NULL,0,NULL,1,1),(22,'2018-01-18 08:00:00',NULL,'2018-01-06 09:50:24','zo0n9Xi7ut',NULL,0,NULL,1,1),(23,'2018-01-18 08:00:00',NULL,'2018-01-06 09:50:24','Zqz5jcz8RR',NULL,0,NULL,1,1),(24,'2018-01-18 08:00:00',NULL,'2018-01-06 09:50:24','2vC6639oJa',NULL,0,NULL,1,1),(25,'2018-01-18 08:00:00',NULL,'2018-01-06 09:50:24','A9JMegkzw6',NULL,0,NULL,1,1),(26,'2018-01-18 08:00:00',NULL,'2018-01-06 09:50:24','UCXOZ0rp2p',NULL,0,NULL,1,1),(27,'2018-01-18 08:00:00',NULL,'2018-01-06 09:50:24','njlDWV1Guk',NULL,0,NULL,1,1),(28,'2018-01-18 08:00:00',NULL,'2018-01-06 09:50:24','1GjRoRiItW',NULL,0,NULL,1,1),(29,'2018-01-18 08:00:00',NULL,'2018-01-06 09:50:24','313ewH06b5',NULL,0,NULL,1,1),(30,'2018-01-18 08:00:00',NULL,'2018-01-06 09:50:24','2pxh52FoH6',NULL,0,NULL,1,1),(31,'2018-01-18 08:00:00',NULL,'2018-01-06 09:50:24','kQzguQ5BCn',NULL,0,NULL,1,1),(32,'2018-01-18 08:00:00',NULL,'2018-01-06 09:50:24','9exowPyhv2',NULL,0,NULL,1,1),(33,'2018-01-18 08:00:00',NULL,'2018-01-06 09:50:24','ylSH6ajJ00',NULL,0,NULL,1,1),(34,'2018-01-18 08:00:00',NULL,'2018-01-06 09:50:24','wADbh5AJ22',NULL,0,NULL,1,1),(35,'2018-01-18 08:00:00',NULL,'2018-01-06 09:50:24','8eEx3jKyp9',NULL,0,NULL,1,1),(36,'2018-01-18 08:00:00',NULL,'2018-01-06 09:50:24','nloS2seqEQ',NULL,0,NULL,1,1),(37,'2018-01-18 08:00:00',NULL,'2018-01-06 09:50:24','f14FYjbP75',NULL,0,NULL,1,1),(38,'2018-01-18 08:00:00',NULL,'2018-01-06 09:50:24','2YJKl4QE8D',NULL,0,NULL,1,1),(39,'2018-01-18 08:00:00',NULL,'2018-01-06 09:50:24','flfvGX3yrw',NULL,0,NULL,1,1),(40,'2018-01-18 08:00:00',NULL,'2018-01-06 09:50:24','87dF6ZYy6z',NULL,0,NULL,1,1),(41,'2018-01-18 08:00:00',NULL,'2018-01-06 09:50:24','jNuEZ2DAE5',NULL,0,NULL,1,1),(42,'2018-01-18 08:00:00',NULL,'2018-01-06 09:50:24','losRmdAh7d',NULL,0,NULL,1,1),(43,'2018-01-18 08:00:00',NULL,'2018-01-06 09:50:24','049rCeMTK7',NULL,0,NULL,1,1),(44,'2018-01-18 08:00:00',NULL,'2018-01-06 09:50:24','3Q8176x78S',NULL,0,NULL,1,1),(45,'2018-01-18 08:00:00',NULL,'2018-01-06 09:50:24','8F1Z00dfQ1',NULL,0,NULL,1,1),(46,'2018-01-18 08:00:00',NULL,'2018-01-06 09:50:24','ryU0vN5iEk',NULL,0,NULL,1,1),(47,'2018-01-18 08:00:00',NULL,'2018-01-06 09:50:24','56ZegGUO8I',NULL,0,NULL,1,1),(48,'2018-01-18 08:00:00',NULL,'2018-01-06 09:50:24','RR9bWs185G',NULL,0,NULL,1,1),(49,'2018-01-18 08:00:00',NULL,'2018-01-06 09:50:24','2zF4X96pwX',NULL,0,NULL,1,1),(50,'2018-01-18 08:00:00',NULL,'2018-01-06 09:50:24','P8ch5Sao2a',NULL,0,NULL,1,1),(51,'2018-01-18 08:00:00',NULL,'2018-01-06 09:50:24','jy8e8fXsSY',NULL,0,NULL,1,1),(52,'2018-01-18 08:00:00',NULL,'2018-01-09 19:14:08','7JdgwO5sUE',NULL,0,NULL,1,1),(53,'2018-01-18 08:00:00',NULL,'2018-01-09 19:14:08','GA8xWVrsFw',NULL,0,NULL,1,1),(54,'2018-01-18 08:00:00',NULL,'2018-01-09 19:14:08','10yYOS4XrF',NULL,0,NULL,1,1);
/*!40000 ALTER TABLE `voucher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `voucher_category`
--

DROP TABLE IF EXISTS `voucher_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `voucher_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `detail` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `voucher_category`
--

LOCK TABLES `voucher_category` WRITE;
/*!40000 ALTER TABLE `voucher_category` DISABLE KEYS */;
INSERT INTO `voucher_category` VALUES (1,'公蟹4只'),(2,'母蟹四只');
/*!40000 ALTER TABLE `voucher_category` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-13 15:09:40
