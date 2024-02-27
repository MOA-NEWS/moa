-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: moa
-- ------------------------------------------------------
-- Server version	8.0.32

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
INSERT INTO `board` VALUES (1,1,'2024-02-26 14:48:38.640396','1st','첫번째'),(2,1,'2024-02-26 14:48:38.750402','2nd','두번째'),(3,2,'2024-02-26 14:48:39.012636','3rd','세번째'),(4,2,'2024-02-26 14:48:39.019652','4th','네번째'),(5,4,'2024-02-26 14:48:39.453042','페이징 확인','ㅇㅇ'),(6,4,'2024-02-26 14:48:39.458047','페이징 확인','ㅇㅇ'),(7,4,'2024-02-26 14:48:39.463042','페이징 확인','ㅇㅇ'),(8,4,'2024-02-26 14:48:39.475045','페이징 확인','ㅇㅇ'),(9,4,'2024-02-26 14:48:39.482046','페이징 확인','ㅇㅇ'),(10,4,'2024-02-26 14:48:39.488046','페이징 확인','ㅇㅇ'),(11,4,'2024-02-26 14:48:39.494045','페이징 확인','ㅇㅇ'),(12,4,'2024-02-26 14:48:39.499046','페이징 확인','ㅇㅇ'),(13,4,'2024-02-26 14:48:39.507050','페이징 확인','ㅇㅇ');
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `board_preference`
--

LOCK TABLES `board_preference` WRITE;
/*!40000 ALTER TABLE `board_preference` DISABLE KEYS */;
INSERT INTO `board_preference` VALUES (_binary '\0',_binary '',10,1,1),(_binary '',_binary '\0',13,2,1),(_binary '\0',_binary '',13,3,2),(_binary '',_binary '\0',13,4,3);
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (4,'2024-02-27 00:17:29.188901',1,1,NULL,'p'),(4,'2024-02-27 00:17:36.031331',2,1,NULL,'p2'),(4,'2024-02-27 00:17:41.826243',3,1,1,'c1'),(4,'2024-02-27 00:17:49.890517',4,1,1,'c2');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `enabled` bit(1) NOT NULL,
  `member_id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (_binary '',1,'userA','$2a$10$ew.NySm3XXJ1dRBD3u1Ew.a7GEoshA.DMrO1d18cUdktX3KA6Ljnu','ROLE_ADMIN'),(_binary '',2,'userB','$2a$10$nMG4a3uNYAMoB66LRwCQUudWfIsevoPm0GYHLbb7Xz6ypxQQ7Xzza','ROLE_USER'),(_binary '',3,'userC','$2a$10$csSwr4E2beBJ5ua.WaH.qeE8dg2.0C1Kt1M5z9km4BcDs9cwU6shq','ROLE_USER'),(_binary '',4,'userD','$2a$10$p1hbbYYsXhIMxWjFEu9rEuyVA7nVI4DHA6salhpxtzERvZ25eYySi','ROLE_USER'),(_binary '',5,'userU','$2a$10$8hOgGP47A5NBCx5QW3GiGuQmfX0uoYRYA0omdE7D5g8UZmg0/wkUa','ROLE_USER');
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
/*!50003 DROP PROCEDURE IF EXISTS `call_find_all_by_page_num` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`moa_ljh`@`localhost` PROCEDURE `call_find_all_by_page_num`(
	IN pi_page_num INT,
	IN pi_page_size INT
    -- OUT boardId LONG
    -- OUT title VARCHAR(255),
    -- OUT name VARCHAR(255),
    -- OUT postDate DATETIME
    
)
BEGIN	
	DECLARE v_offset_num INT;
    
    SET v_offset_num = pi_page_size * pi_page_num;
    
	SELECT b.BOARD_ID, b.POST_DATE, b.TITLE, b.CONTENT, b.MEMBER_ID
	FROM BOARD b
	-- INNER JOIN MEMBER m
	-- ON b.MEMBER_ID = m.MEMBER_ID
	-- 댓글 정렬
	-- LEFT JOIN COMMENT c
	-- ON b.BOARD_ID = c.BOARD_ID
	ORDER BY b.BOARD_ID desc -- , coalesce(c.PARENT_ID, c.COMMENT_ID), c.COMMENT_DATE
	LIMIT pi_page_size OFFSET v_offset_num;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `call_find_all_with_page_num` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`moa_ljh`@`localhost` PROCEDURE `call_find_all_with_page_num`(
	IN page_num INT,
	IN page_size INT
)
BEGIN
	DECLARE offset_num INT;
    
    SET offset_num = page_size * page_num;
    
    SELECT b.BOARD_ID, b.TITLE, m.NAME, b.POST_DATE
    FROM BOARD b
    INNER JOIN MEMBER m
    ON b.MEMBER_ID = m.MEMBER_ID
    ORDER BY board_id desc
	LIMIT page_size OFFSET offset_num;
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
    
    
    SELECT EXISTS(
        SELECT 1
        FROM board_preference as b
        WHERE b.member_id = member_id AND b.board_id = board_id
    ) INTO preference_exists;
    
    IF preference_exists THEN
        
        SELECT likes, dislikes INTO current_likes, current_dislikes
        FROM board_preference as b
        WHERE b.member_id = member_id AND b.board_id = board_id;
        
        
        IF is_dislike THEN
            IF current_likes THEN
                
                UPDATE board_preference as b
                SET likes = FALSE,
                    dislikes = TRUE
                WHERE b.member_id = member_id AND b.board_id = board_id;
            ELSE
                
                UPDATE board_preference as b
                SET dislikes = NOT current_dislikes
                WHERE b.member_id = member_id AND b.board_id = board_id;
            END IF;
        ELSE
            IF current_dislikes THEN
                
                UPDATE board_preference as b
                SET dislikes = FALSE,
                    likes = TRUE
                WHERE b.member_id = member_id AND b.board_id = board_id;
            ELSE
                
                UPDATE board_preference as b
                SET likes = NOT current_likes
                WHERE b.member_id = member_id AND b.board_id = board_id;
            END IF;
        END IF;
        
    ELSE
        
        INSERT INTO board_preference (member_id, board_id, likes, dislikes)
        VALUES (member_id, board_id, IF(is_dislike, 0, 1), IF(is_dislike, 1, 0));
    END IF;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_find_all_with_page_num` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`moa_ljh`@`localhost` PROCEDURE `sp_find_all_with_page_num`(
	IN page_num INT,
	IN page_size INT
)
BEGIN
	DECLARE offset_num INT;
    
    SET offset_num = page_size * page_num;
    
    SELECT b.BOARD_ID, b.TITLE, m.NAME, b.POST_DATE
    FROM BOARD b
    INNER JOIN MEMBER m
    ON b.MEMBER_ID = m.MEMBER_ID
    ORDER BY board_id desc
	LIMIT page_size OFFSET offset_num;
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

-- Dump completed on 2024-02-27 13:52:15
