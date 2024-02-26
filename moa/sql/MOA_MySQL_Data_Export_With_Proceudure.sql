-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: moa
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `board`
--

DROP TABLE IF EXISTS `board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `board` (
  `board_id` bigint NOT NULL AUTO_INCREMENT,
  `member_id` bigint DEFAULT NULL,
  `post_date` datetime(6) DEFAULT NULL,
  `title` varchar(30) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`board_id`),
  KEY `FKsds8ox89wwf6aihinar49rmfy` (`member_id`),
  CONSTRAINT `FKsds8ox89wwf6aihinar49rmfy` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `board`
--

LOCK TABLES `board` WRITE;
/*!40000 ALTER TABLE `board` DISABLE KEYS */;
INSERT INTO `board` VALUES (1,1,'2024-02-26 02:25:39.245535','1st','첫번째'),(2,1,'2024-02-26 02:25:39.287423','2nd','두번째'),(3,2,'2024-02-26 02:25:39.311357','3rd','세번째'),(4,2,'2024-02-26 02:25:39.315350','4th','네번째'),(5,4,'2024-02-26 02:25:39.339283','페이징 확인','ㅇㅇ'),(6,4,'2024-02-26 02:25:39.341278','페이징 확인','ㅇㅇ'),(7,4,'2024-02-26 02:25:39.344272','페이징 확인','ㅇㅇ'),(8,4,'2024-02-26 02:25:39.346265','페이징 확인','ㅇㅇ'),(9,4,'2024-02-26 02:25:39.348258','페이징 확인','ㅇㅇ'),(10,4,'2024-02-26 02:25:39.350254','페이징 확인','ㅇㅇ'),(11,4,'2024-02-26 02:25:39.354244','페이징 확인','ㅇㅇ'),(12,4,'2024-02-26 02:25:39.356238','페이징 확인','ㅇㅇ'),(13,4,'2024-02-26 02:25:39.359230','페이징 확인','ㅇㅇ');
/*!40000 ALTER TABLE `board` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `board_preference`
--

DROP TABLE IF EXISTS `board_preference`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `board_preference` (
  `dislikes` bit(1) NOT NULL,
  `likes` bit(1) NOT NULL,
  `board_id` bigint DEFAULT NULL,
  `board_preference_id` bigint NOT NULL AUTO_INCREMENT,
  `member_id` bigint DEFAULT NULL,
  PRIMARY KEY (`board_preference_id`),
  KEY `FKpo8ytt3gac4h5r70xxk9d0f7f` (`board_id`),
  KEY `FKhypifoq1640w3b53xia9142nc` (`member_id`),
  CONSTRAINT `FKhypifoq1640w3b53xia9142nc` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`),
  CONSTRAINT `FKpo8ytt3gac4h5r70xxk9d0f7f` FOREIGN KEY (`board_id`) REFERENCES `board` (`board_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `board_preference`
--

LOCK TABLES `board_preference` WRITE;
/*!40000 ALTER TABLE `board_preference` DISABLE KEYS */;
/*!40000 ALTER TABLE `board_preference` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `board_id` bigint DEFAULT NULL,
  `comment_date` datetime(6) DEFAULT NULL,
  `comment_id` bigint NOT NULL AUTO_INCREMENT,
  `member_id` bigint DEFAULT NULL,
  `parent_id` bigint DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `FKlij9oor1nav89jeat35s6kbp1` (`board_id`),
  KEY `FKmrrrpi513ssu63i2783jyiv9m` (`member_id`),
  KEY `FKde3rfu96lep00br5ov0mdieyt` (`parent_id`),
  CONSTRAINT `FKde3rfu96lep00br5ov0mdieyt` FOREIGN KEY (`parent_id`) REFERENCES `comment` (`comment_id`),
  CONSTRAINT `FKlij9oor1nav89jeat35s6kbp1` FOREIGN KEY (`board_id`) REFERENCES `board` (`board_id`),
  CONSTRAINT `FKmrrrpi513ssu63i2783jyiv9m` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `locked` bit(1) DEFAULT NULL,
  `member_id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `role` enum('ADMIN','USER') DEFAULT NULL,
  PRIMARY KEY (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (_binary '\0',1,'userA','ADMIN'),(_binary '\0',2,'userB','USER'),(_binary '\0',3,'userC','USER'),(_binary '\0',4,'userD','USER');
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reply`
--

DROP TABLE IF EXISTS `reply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reply` (
  `comment_id` bigint DEFAULT NULL,
  `reply_date` datetime(6) DEFAULT NULL,
  `reply_id` bigint NOT NULL AUTO_INCREMENT,
  `text` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`reply_id`),
  KEY `FK6w0ns67lrq1jdiwi5xvtj1vxx` (`comment_id`),
  CONSTRAINT `FK6w0ns67lrq1jdiwi5xvtj1vxx` FOREIGN KEY (`comment_id`) REFERENCES `comment` (`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reply`
--

LOCK TABLES `reply` WRITE;
/*!40000 ALTER TABLE `reply` DISABLE KEYS */;
/*!40000 ALTER TABLE `reply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'moa'
--
/*!50003 DROP PROCEDURE IF EXISTS `call_count_dislikes_by_board_id` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `call_count_dislikes_by_board_id`(IN board_id INT, OUT dislikes_count BIGINT)
BEGIN
    SELECT COUNT(*) into dislikes_count FROM Board_Preference as b WHERE b.board_id = board_id AND dislikes = true;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `call_count_likes_by_board_id` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `call_count_likes_by_board_id`(IN board_id INT, OUT likes_count BIGINT)
BEGIN
    SELECT COUNT(*) into likes_count FROM Board_Preference as b WHERE b.board_id = board_id AND likes = true;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `call_toggle_preference` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `call_toggle_preference`(
    IN member_id INT,
    IN board_id INT,
    IN is_dislike BOOLEAN
)
BEGIN
    DECLARE preference_exists BOOLEAN;
    DECLARE current_likes BOOLEAN;
    DECLARE current_dislikes BOOLEAN;
    
    -- Check if preference exists for the given member and board
    SELECT EXISTS(
        SELECT 1
        FROM board_preference as b
        WHERE b.member_id = member_id AND b.board_id = board_id
    ) INTO preference_exists;
    
    IF preference_exists THEN
        -- Get current likes and dislikes values
        SELECT likes, dislikes INTO current_likes, current_dislikes
        FROM board_preference as b
        WHERE b.member_id = member_id AND b.board_id = board_id;
        
        -- Update likes and dislikes based on conditions
        IF is_dislike THEN
            IF current_likes THEN
                -- If likes is true and dislikes is being toggled to true, set likes to false
                UPDATE board_preference as b
                SET likes = FALSE,
                    dislikes = TRUE
                WHERE b.member_id = member_id AND b.board_id = board_id;
            ELSE
                -- If likes is false, toggle dislikes
                UPDATE board_preference as b
                SET dislikes = NOT current_dislikes
                WHERE b.member_id = member_id AND b.board_id = board_id;
            END IF;
        ELSE
            IF current_dislikes THEN
                -- If dislikes is true and likes is being toggled to true, set dislikes to false
                UPDATE board_preference as b
                SET dislikes = FALSE,
                    likes = TRUE
                WHERE b.member_id = member_id AND b.board_id = board_id;
            ELSE
                -- If dislikes is false, toggle likes
                UPDATE board_preference as b
                SET likes = NOT current_likes
                WHERE b.member_id = member_id AND b.board_id = board_id;
            END IF;
        END IF;
        
    ELSE
        -- If preference does not exist, insert a new preference
        INSERT INTO board_preference (member_id, board_id, likes, dislikes)
        VALUES (member_id, board_id, IF(is_dislike, 0, 1), IF(is_dislike, 1, 0));
    END IF;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-02-26  2:28:08
