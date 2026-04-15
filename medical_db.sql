-- MySQL dump 10.13  Distrib 9.2.0, for macos15 (x86_64)
--
-- Host: localhost    Database: medical_db
-- ------------------------------------------------------
-- Server version	9.2.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `admin_no` varchar(20) NOT NULL COMMENT '管理员工号（唯一标识）',
  `admin_name` varchar(50) NOT NULL COMMENT '管理员姓名',
  `password` varchar(100) NOT NULL COMMENT '登录密码（建议加密存储）',
  `phone` varchar(15) NOT NULL COMMENT '手机号',
  `email` varchar(50) NOT NULL COMMENT '邮箱',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `role` varchar(20) NOT NULL DEFAULT '管理员' COMMENT '职位（固定为管理员）',
  `admin_pic` varchar(255) DEFAULT NULL COMMENT '管理员头像，存放url路径，可为空',
  PRIMARY KEY (`admin_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='管理员信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES ('A2022000','admin','12345','13820169036','admin@medical-hospital.com','2026-01-26 22:35:42','2026-03-17 20:51:45','管理员',NULL);
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `check_items`
--

DROP TABLE IF EXISTS `check_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `check_items` (
  `item_id` int NOT NULL AUTO_INCREMENT,
  `item_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_ci NOT NULL,
  `item_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_ci NOT NULL,
  `item_place` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_ci DEFAULT NULL,
  `start_time` datetime NOT NULL COMMENT '开始时间',
  `end_time` datetime NOT NULL COMMENT '结束时间',
  `is_active` tinyint NOT NULL,
  `lab_no` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_ci NOT NULL,
  `reserve_max` int NOT NULL DEFAULT '30' COMMENT '最大预约人数',
  `reserved` int NOT NULL DEFAULT '0' COMMENT '已预约人数',
  `reserve_empty` int GENERATED ALWAYS AS ((`reserve_max` - `reserved`)) STORED,
  `is_delete` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除，默认为 0，表示未删除',
  PRIMARY KEY (`item_id`),
  KEY `check_items_lab_tech_lab_no_fk` (`lab_no`),
  CONSTRAINT `check_items_lab_tech_lab_no_fk` FOREIGN KEY (`lab_no`) REFERENCES `lab_tech` (`lab_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_as_ci COMMENT='检查化验项目表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `check_items`
--

LOCK TABLES `check_items` WRITE;
/*!40000 ALTER TABLE `check_items` DISABLE KEYS */;
/*!40000 ALTER TABLE `check_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `check_reserve_record`
--

DROP TABLE IF EXISTS `check_reserve_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `check_reserve_record` (
  `record_id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `card_id` int NOT NULL COMMENT '就诊卡 id，外键',
  `item_id` int NOT NULL COMMENT '检验项目id，关联检验项目表',
  PRIMARY KEY (`record_id`),
  KEY `check_reserve_record_check_items_item_id_fk` (`item_id`),
  KEY `check_reserve_record_medical_cards_card_id_fk` (`card_id`),
  CONSTRAINT `check_reserve_record_check_items_item_id_fk` FOREIGN KEY (`item_id`) REFERENCES `check_items` (`item_id`),
  CONSTRAINT `check_reserve_record_medical_cards_card_id_fk` FOREIGN KEY (`card_id`) REFERENCES `medical_cards` (`card_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_cs_0900_ai_ci COMMENT='预约检验项目记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `check_reserve_record`
--

LOCK TABLES `check_reserve_record` WRITE;
/*!40000 ALTER TABLE `check_reserve_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `check_reserve_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dept`
--

DROP TABLE IF EXISTS `dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dept` (
  `dept_id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dept_name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_ci DEFAULT NULL COMMENT '科室名',
  `dept_sort_id` int DEFAULT NULL COMMENT '科室分类id',
  PRIMARY KEY (`dept_id`),
  KEY `dept_dept_sort_dept_sort_id_fk` (`dept_sort_id`),
  CONSTRAINT `dept_dept_sort_dept_sort_id_fk` FOREIGN KEY (`dept_sort_id`) REFERENCES `dept_sort` (`dept_sort_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_as_ci COMMENT='科室表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dept`
--

LOCK TABLES `dept` WRITE;
/*!40000 ALTER TABLE `dept` DISABLE KEYS */;
INSERT INTO `dept` VALUES (1,'呼吸内科',1),(2,'消化内科',1),(3,'肾内科',1),(4,'内分泌科',1),(5,'骨科',2),(6,'五官科',2),(7,'妇科',3),(8,'儿科',3),(10,'胸腔外科',2),(11,'神经外科',2);
/*!40000 ALTER TABLE `dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dept_sort`
--

DROP TABLE IF EXISTS `dept_sort`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dept_sort` (
  `dept_sort_id` int NOT NULL AUTO_INCREMENT COMMENT '科室分类主键id',
  `dept_sort_name` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_cs_0900_ai_ci NOT NULL COMMENT '科室分类的类名',
  PRIMARY KEY (`dept_sort_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_cs_0900_ai_ci COMMENT='科室分类表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dept_sort`
--

LOCK TABLES `dept_sort` WRITE;
/*!40000 ALTER TABLE `dept_sort` DISABLE KEYS */;
INSERT INTO `dept_sort` VALUES (1,'内科'),(2,'外科'),(3,'特殊科');
/*!40000 ALTER TABLE `dept_sort` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctor`
--

DROP TABLE IF EXISTS `doctor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doctor` (
  `doctor_no` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '医生工号（唯一标识）',
  `doctor_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '医生姓名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '登录密码（建议加密存储）',
  `role` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '医生' COMMENT '职位（固定为医生）',
  `title` enum('医师','主治医师','副主任医师','主任医师') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '医生职称（枚举值：医师<主治医师<副主任医师<主任医师）',
  `phone` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '手机号',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '邮箱',
  `dept_id` int DEFAULT NULL COMMENT '所属科室',
  `specialty` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '擅长领域',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `doctor_pic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '医生头像URL',
  PRIMARY KEY (`doctor_no`),
  UNIQUE KEY `doctor_pk` (`doctor_name`),
  KEY `doctor_dept_dept_id_fk` (`dept_id`),
  CONSTRAINT `doctor_dept_dept_id_fk` FOREIGN KEY (`dept_id`) REFERENCES `dept` (`dept_id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_cs_0900_ai_ci COMMENT='医生信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctor`
--

LOCK TABLES `doctor` WRITE;
/*!40000 ALTER TABLE `doctor` DISABLE KEYS */;
INSERT INTO `doctor` VALUES ('D2022001','王明','12345','医生','主治医师','12345678901','287334qq.com',2,'治疗胃病，肚子痛','2026-01-26 23:56:28','2026-03-19 13:48:40','http://localhost:8080/images/f96acb70-7884-488f-883f-04cda9a9dda6.jpg'),('D2022002','李长寿','12345','医生','副主任医师','77777777777',NULL,1,NULL,'2026-01-27 15:12:08','2026-03-18 01:58:05',''),('D2022003','白月魁','','医生','主任医师','11111111111',NULL,5,NULL,'2026-02-01 16:36:09','2026-03-14 20:26:53',NULL),('D2022004','马克','','医生','副主任医师','22222222222',NULL,3,NULL,'2026-02-01 16:36:09','2026-03-14 20:26:53',NULL),('D2022005','冉冰','','医生','主治医师','33333333333',NULL,4,NULL,'2026-02-01 16:36:09','2026-03-14 20:26:53',NULL),('D2022006','随影',NULL,'医生','医师','44444444444',NULL,6,NULL,'2026-02-01 16:36:09','2026-02-01 17:35:00',NULL),('D2022007','查尔斯',NULL,'医生','副主任医师','55555555555',NULL,8,NULL,'2026-02-01 16:36:09','2026-02-01 17:35:25',NULL),('D2022008','麦朵',NULL,'医生','副主任医师','66666666666',NULL,7,NULL,'2026-02-01 16:36:09','2026-02-01 17:35:25',NULL),('D2022009','镜南',NULL,'医生','医师','88888888888',NULL,2,NULL,'2026-03-18 01:47:54','2026-03-18 01:47:54',NULL),('D2022010','墨城',NULL,'医生','医师','98765432109',NULL,2,'','2026-03-19 13:44:24','2026-03-19 13:44:36',NULL);
/*!40000 ALTER TABLE `doctor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lab_tech`
--

DROP TABLE IF EXISTS `lab_tech`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lab_tech` (
  `lab_no` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_ci NOT NULL COMMENT '化验员工号（唯一标识）',
  `lab_name` varchar(50) NOT NULL COMMENT '化验员姓名',
  `password` varchar(100) DEFAULT NULL COMMENT '登录密码（建议加密存储）',
  `role` varchar(20) NOT NULL DEFAULT '化验员' COMMENT '职位（固定为化验员）',
  `phone` varchar(15) DEFAULT NULL COMMENT '手机号',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `lab_pic` varchar(255) DEFAULT NULL COMMENT '化验员头像，未对用户展示可为空',
  PRIMARY KEY (`lab_no`),
  KEY `lab_no` (`lab_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='化验员信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lab_tech`
--

LOCK TABLES `lab_tech` WRITE;
/*!40000 ALTER TABLE `lab_tech` DISABLE KEYS */;
INSERT INTO `lab_tech` VALUES ('L2022001','张三','12345','化验员','12345678901',NULL,'2026-01-27 00:03:25','2026-03-18 13:26:17','https:/image'),('L2022003','嘉莉',NULL,'化验员','19290967021',NULL,'2026-03-18 15:40:29','2026-03-18 15:40:29',NULL);
/*!40000 ALTER TABLE `lab_tech` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medical_cards`
--

DROP TABLE IF EXISTS `medical_cards`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medical_cards` (
  `card_id` int NOT NULL AUTO_INCREMENT,
  `patient_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `id_number` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `gender` enum('男','女') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `age` tinyint NOT NULL,
  `relationship` enum('本人','配偶','父母','子女','其他') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `contact_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `user_id` int NOT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`card_id`),
  UNIQUE KEY `id_number` (`id_number`),
  KEY `idx_card_user` (`user_id`),
  KEY `idx_card_id` (`id_number`),
  CONSTRAINT `medical_cards_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='就诊卡';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medical_cards`
--

LOCK TABLES `medical_cards` WRITE;
/*!40000 ALTER TABLE `medical_cards` DISABLE KEYS */;
INSERT INTO `medical_cards` VALUES (1,'患者李四','431121200507150424','女',100,'配偶','99999999999',1,'2026-01-29 09:39:31','2026-01-29 09:39:31'),(2,'患者张三','351104199911231234','男',99,'本人','19290967024',1,'2026-02-02 02:52:32','2026-02-02 02:52:32'),(3,'患者王麻子','216668199502071234','男',98,'本人','12345678901',2,'2026-02-02 02:58:22','2026-02-02 02:58:22');
/*!40000 ALTER TABLE `medical_cards` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medical_histories`
--

DROP TABLE IF EXISTS `medical_histories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medical_histories` (
  `history_id` int NOT NULL AUTO_INCREMENT,
  `medical_history` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `patient_description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `doctor_advice` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `doctor_name` varchar(25) COLLATE utf8mb4_general_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `card_id` int NOT NULL COMMENT '外键，关联就诊卡，一个就诊卡对应多个病历本',
  `reservation_id` int NOT NULL COMMENT '外键，关联预约记录表',
  PRIMARY KEY (`history_id`),
  KEY `medical_histories_medical_cards_card_id_fk` (`card_id`),
  KEY `medical_histories_reservation_reservation_id_fk` (`reservation_id`),
  CONSTRAINT `medical_histories_medical_cards_card_id_fk` FOREIGN KEY (`card_id`) REFERENCES `medical_cards` (`card_id`),
  CONSTRAINT `medical_histories_reservation_reservation_id_fk` FOREIGN KEY (`reservation_id`) REFERENCES `reservation` (`reservation_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='病历本';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medical_histories`
--

LOCK TABLES `medical_histories` WRITE;
/*!40000 ALTER TABLE `medical_histories` DISABLE KEYS */;
/*!40000 ALTER TABLE `medical_histories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prescription`
--

DROP TABLE IF EXISTS `prescription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prescription` (
  `prescription_id` int NOT NULL AUTO_INCREMENT COMMENT '药方唯一ID',
  `doctor_no` varchar(20) NOT NULL COMMENT '关联医生工号',
  `prescription_name` varchar(100) NOT NULL COMMENT '药方名称',
  `disease` varchar(50) NOT NULL COMMENT '适用疾病类型',
  `prescription_desc` text COMMENT '药方说明',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`prescription_id`),
  KEY `fk_prescription_doctor` (`doctor_no`),
  CONSTRAINT `fk_prescription_doctor` FOREIGN KEY (`doctor_no`) REFERENCES `doctor` (`doctor_no`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='药方主表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prescription`
--

LOCK TABLES `prescription` WRITE;
/*!40000 ALTER TABLE `prescription` DISABLE KEYS */;
INSERT INTO `prescription` VALUES (4,'D2022001','治疗胃病','肚子疼','治疗由食物中毒引发的肚子疼','2026-03-17 14:27:13','2026-03-17 14:27:13');
/*!40000 ALTER TABLE `prescription` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prescription_detail`
--

DROP TABLE IF EXISTS `prescription_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prescription_detail` (
  `detail_id` int NOT NULL AUTO_INCREMENT COMMENT '药方详情唯一ID',
  `prescription_id` int NOT NULL COMMENT '关联药方ID',
  `drug_name` varchar(50) NOT NULL COMMENT '药物名称',
  `dosage` varchar(50) NOT NULL COMMENT '单次用量',
  `frequency` varchar(30) NOT NULL COMMENT '每日次数',
  `usage` varchar(30) NOT NULL COMMENT '给药方式',
  `drug_remark` varchar(200) DEFAULT NULL COMMENT '药物备注',
  PRIMARY KEY (`detail_id`),
  KEY `fk_detail_prescription` (`prescription_id`),
  CONSTRAINT `fk_detail_prescription` FOREIGN KEY (`prescription_id`) REFERENCES `prescription` (`prescription_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='药方药物详情表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prescription_detail`
--

LOCK TABLES `prescription_detail` WRITE;
/*!40000 ALTER TABLE `prescription_detail` DISABLE KEYS */;
INSERT INTO `prescription_detail` VALUES (2,4,'药物 1','1 袋',' 2','冲泡','无'),(3,4,'药物 2','1 粒',' 2','吞服','无');
/*!40000 ALTER TABLE `prescription_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservation` (
  `reservation_id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `card_id` int DEFAULT NULL COMMENT '外键，关联就诊卡',
  `reserve_date` date NOT NULL COMMENT '预约日期',
  `reserve_time` enum('上午','下午','晚上') CHARACTER SET utf8mb4 COLLATE utf8mb4_cs_0900_ai_ci NOT NULL COMMENT '预约时间',
  `doctor_no` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `is_receive` tinyint DEFAULT '0' COMMENT '医生是否接诊，默认为否，0',
  PRIMARY KEY (`reservation_id`),
  KEY `reservation_medical_cards_card_id_fk` (`card_id`),
  KEY `reservation_doctor_doctor_name_fk` (`doctor_no`),
  CONSTRAINT `reservation_doctor_doctor_no_fk` FOREIGN KEY (`doctor_no`) REFERENCES `doctor` (`doctor_no`),
  CONSTRAINT `reservation_medical_cards_card_id_fk` FOREIGN KEY (`card_id`) REFERENCES `medical_cards` (`card_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_cs_0900_ai_ci COMMENT='预约信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
INSERT INTO `reservation` VALUES (1,1,'2026-03-08','上午','D2022001',0),(2,2,'2026-03-09','上午','D2022001',0),(3,3,'2026-03-10','上午','D2022001',0);
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT COMMENT '用户唯一id，自增',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '手机号，唯一',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `user_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `max_cards` tinyint NOT NULL DEFAULT '4' COMMENT '就诊卡最大数量',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `sex` enum('男','女') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '性别',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `phone` (`phone`),
  KEY `idx_user_phone` (`phone`),
  KEY `idx_user_name` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'19290967024','123456','用户张三',4,'2026-01-27 15:19:59','2026-02-02 02:49:28',NULL),(2,'12345678901','23456','用户王麻子',4,'2026-02-02 02:53:54','2026-02-02 02:53:54',NULL),(3,'19290967025','123456','小张',4,'2026-03-08 03:18:35','2026-03-08 03:18:35',NULL),(4,'19290967026','123456','渣渣辉',4,'2026-03-08 09:58:19','2026-03-08 09:58:19',NULL),(5,'11111111111','123456','lds',4,'2026-03-14 22:29:58','2026-03-14 22:29:58',NULL),(6,'19290967022','123456','张三',4,'2026-03-19 05:50:56','2026-03-19 05:50:56',NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `work_table`
--

DROP TABLE IF EXISTS `work_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `work_table` (
  `table_id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '值班记录唯一标识',
  `work_date` date NOT NULL COMMENT '值班日期',
  `work_time` enum('上午','下午','晚上') NOT NULL COMMENT '值班时间段',
  `doctor_no` varchar(20) NOT NULL COMMENT '关联医生信息表的医生工号',
  `reserved` int unsigned NOT NULL DEFAULT '0' COMMENT '当前已预约人数',
  `reserve_max` int unsigned NOT NULL DEFAULT '20' COMMENT '最大预约人数',
  `reserve_empty` int unsigned NOT NULL COMMENT '剩余空位',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`table_id`),
  UNIQUE KEY `uk_医生_日期_时间段` (`doctor_no`,`work_date`,`work_time`) COMMENT '确保同一医生同一时间仅一条值班记录',
  KEY `idx_值班日期` (`work_date`) COMMENT '按日期查询索引',
  CONSTRAINT `fk_值班表_医生id` FOREIGN KEY (`doctor_no`) REFERENCES `doctor` (`doctor_no`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='医生值班安排表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `work_table`
--

LOCK TABLES `work_table` WRITE;
/*!40000 ALTER TABLE `work_table` DISABLE KEYS */;
INSERT INTO `work_table` VALUES (1,'2026-03-20','上午','D2022001',0,20,20,'2026-03-18 02:27:05','2026-03-18 02:27:06'),(3,'2026-03-08','晚上','D2022007',0,20,20,'2026-03-18 02:27:05','2026-03-18 02:27:06'),(4,'2026-03-09','上午','D2022001',0,20,20,'2026-03-18 02:27:05','2026-03-18 02:27:06'),(5,'2026-03-09','下午','D2022001',0,20,20,'2026-03-18 02:27:05','2026-03-18 02:27:06'),(6,'2026-03-10','上午','D2022001',0,20,20,'2026-03-18 02:27:05','2026-03-18 02:27:06'),(7,'2026-03-18','上午','D2022002',0,20,20,'2026-03-18 02:27:05','2026-03-18 12:45:12'),(9,'2026-03-10','下午','D2022002',0,20,20,'2026-03-18 02:27:05','2026-03-18 02:27:06'),(10,'2026-03-10','上午','D2022003',0,20,20,'2026-03-18 02:27:05','2026-03-18 02:27:06'),(11,'2026-03-11','上午','D2022003',0,20,20,'2026-03-18 02:27:05','2026-03-18 02:27:06'),(12,'2026-03-10','下午','D2022004',0,20,20,'2026-03-18 02:27:05','2026-03-18 02:27:06'),(13,'2026-03-11','下午','D2022004',0,20,20,'2026-03-18 02:27:05','2026-03-18 02:27:06'),(14,'2026-03-11','上午','D2022005',0,20,20,'2026-03-18 02:27:05','2026-03-18 02:27:06'),(15,'2026-03-12','上午','D2022005',0,20,20,'2026-03-18 02:27:05','2026-03-18 02:27:06'),(16,'2026-03-11','下午','D2022006',0,20,20,'2026-03-18 02:27:05','2026-03-18 02:27:06'),(17,'2026-03-12','下午','D2022006',0,20,20,'2026-03-18 02:27:05','2026-03-18 02:27:06'),(18,'2026-03-12','上午','D2022007',0,20,20,'2026-03-18 02:27:05','2026-03-18 02:27:06'),(19,'2026-03-13','上午','D2022007',0,20,20,'2026-03-18 02:27:05','2026-03-18 02:27:06'),(20,'2026-03-12','下午','D2022008',0,20,20,'2026-03-18 02:27:05','2026-03-18 02:27:06'),(21,'2026-03-13','下午','D2022008',0,20,20,'2026-03-18 02:27:05','2026-03-18 02:27:06'),(22,'2026-03-14','上午','D2022001',0,20,20,'2026-03-18 02:27:05','2026-03-18 02:27:06'),(23,'2026-03-14','下午','D2022001',0,20,20,'2026-03-18 02:27:05','2026-03-18 02:27:06'),(24,'2026-03-14','晚上','D2022001',0,20,20,'2026-03-18 02:27:05','2026-03-18 02:27:06'),(25,'2026-03-15','上午','D2022001',0,20,20,'2026-03-18 02:27:05','2026-03-18 02:27:06'),(26,'2026-03-15','晚上','D2022001',0,20,20,'2026-03-18 02:27:05','2026-03-18 02:27:06'),(27,'2026-03-14','上午','D2022002',0,20,20,'2026-03-18 02:27:05','2026-03-18 02:27:06'),(28,'2026-03-14','晚上','D2022002',0,20,20,'2026-03-18 02:27:05','2026-03-18 02:27:06'),(29,'2026-03-15','下午','D2022002',0,20,20,'2026-03-18 02:27:05','2026-03-18 02:27:06'),(30,'2026-03-15','晚上','D2022002',0,20,20,'2026-03-18 02:27:05','2026-03-18 02:27:06'),(31,'2026-03-16','上午','D2022002',0,20,20,'2026-03-18 02:27:05','2026-03-18 02:27:06'),(32,'2026-03-14','下午','D2022003',0,20,20,'2026-03-18 02:27:05','2026-03-18 02:27:06'),(33,'2026-03-15','上午','D2022003',0,20,20,'2026-03-18 02:27:05','2026-03-18 02:27:06'),(34,'2026-03-15','下午','D2022003',0,20,20,'2026-03-18 02:27:05','2026-03-18 02:27:06'),(35,'2026-03-16','下午','D2022003',0,20,20,'2026-03-18 02:27:05','2026-03-18 02:27:06'),(36,'2026-03-16','晚上','D2022003',0,20,20,'2026-03-18 02:27:05','2026-03-18 02:27:06'),(37,'2026-03-14','晚上','D2022004',0,20,20,'2026-03-18 02:27:05','2026-03-18 02:27:06'),(38,'2026-03-15','上午','D2022004',0,20,20,'2026-03-18 02:27:05','2026-03-18 02:27:06'),(39,'2026-03-15','晚上','D2022004',0,20,20,'2026-03-18 02:27:05','2026-03-18 02:27:06'),(40,'2026-03-16','上午','D2022004',0,20,20,'2026-03-18 02:27:05','2026-03-18 02:27:06'),(41,'2026-03-16','下午','D2022004',0,20,20,'2026-03-18 02:27:05','2026-03-18 02:27:06'),(42,'2026-03-17','上午','D2022005',0,20,20,'2026-03-18 02:27:05','2026-03-18 02:27:06'),(43,'2026-03-17','下午','D2022005',0,20,20,'2026-03-18 02:27:05','2026-03-18 02:27:06'),(44,'2026-03-17','晚上','D2022005',0,20,20,'2026-03-18 02:27:05','2026-03-18 02:27:06'),(45,'2026-03-18','上午','D2022005',0,20,20,'2026-03-18 02:27:05','2026-03-18 02:27:06'),(46,'2026-03-17','上午','D2022006',0,20,20,'2026-03-18 02:27:05','2026-03-18 02:27:06'),(47,'2026-03-17','晚上','D2022006',0,20,20,'2026-03-18 02:27:05','2026-03-18 02:27:06'),(48,'2026-03-18','下午','D2022006',0,20,20,'2026-03-18 02:27:05','2026-03-18 02:27:06'),(49,'2026-03-18','晚上','D2022006',0,20,20,'2026-03-18 02:27:05','2026-03-18 02:27:06'),(50,'2026-03-18','上午','D2022007',0,20,20,'2026-03-18 02:27:05','2026-03-18 02:27:06'),(51,'2026-03-18','下午','D2022007',0,20,20,'2026-03-18 02:27:05','2026-03-18 02:27:06'),(52,'2026-03-18','晚上','D2022008',0,20,20,'2026-03-18 02:27:05','2026-03-18 02:27:06'),(54,'2026-03-23','上午','D2022009',0,20,20,'2026-03-18 12:02:22','2026-03-18 12:02:22'),(55,'2026-03-18','下午','D2022009',0,20,20,'2026-03-18 13:09:45','2026-03-18 13:09:45'),(56,'2026-03-19','下午','D2022001',0,20,20,'2026-03-18 14:37:27','2026-03-18 14:37:27'),(57,'2026-03-19','上午','D2022006',0,20,20,'2026-03-19 13:46:02','2026-03-19 13:46:02');
/*!40000 ALTER TABLE `work_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for trigger `before_work_table_insert`
--

DROP TRIGGER IF EXISTS `before_work_table_insert`;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `before_work_table_insert` BEFORE INSERT ON `work_table` FOR EACH ROW BEGIN

    SET NEW.reserve_empty = NEW.reserve_max - NEW.reserved;

END */;
DELIMITER ;

--
-- Table structure for trigger `before_work_table_update`
--

DROP TRIGGER IF EXISTS `before_work_table_update`;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `before_work_table_update` BEFORE UPDATE ON `work_table` FOR EACH ROW BEGIN

    SET NEW.reserve_empty = NEW.reserve_max - NEW.reserved;

END */;
DELIMITER ;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-04-11 14:07:51