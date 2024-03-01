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
INSERT INTO `board` VALUES (1,1,'2024-02-29 21:44:33.584075','1st','첫번째'),(2,1,'2024-02-29 21:44:33.686082','2nd','두번째'),(3,2,'2024-02-29 21:44:33.984279','3rd','세번째'),(4,2,'2024-02-29 21:44:33.990275','4th','네번째'),(5,4,'2024-02-29 21:44:34.367694','페이징 확인','ㅇㅇ'),(6,4,'2024-02-29 21:44:34.372695','페이징 확인','ㅇㅇ'),(7,4,'2024-02-29 21:44:34.378697','페이징 확인','ㅇㅇ'),(8,4,'2024-02-29 21:44:34.382693','페이징 확인','ㅇㅇ'),(9,4,'2024-02-29 21:44:34.387693','페이징 확인','ㅇㅇ'),(10,4,'2024-02-29 21:44:34.391694','페이징 확인','ㅇㅇ'),(11,4,'2024-02-29 21:44:34.396694','페이징 확인','ㅇㅇ'),(12,4,'2024-02-29 21:44:34.400694','페이징 확인','ㅇㅇ'),(13,4,'2024-02-29 21:44:34.405696','페이징 확인','ㅇㅇ');
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
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comments` (
  `board_id` bigint DEFAULT NULL,
  `comment_date` datetime(6) DEFAULT NULL,
  `comment_id` bigint NOT NULL AUTO_INCREMENT,
  `member_id` bigint DEFAULT NULL,
  `parent_id` bigint DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `FK2sbm05xp09r2igj2t4j2so05l` (`board_id`),
  KEY `FK5my97to6xmqeuycmeelttgj44` (`member_id`),
  KEY `FKlri30okf66phtcgbe5pok7cc0` (`parent_id`),
  CONSTRAINT `FK2sbm05xp09r2igj2t4j2so05l` FOREIGN KEY (`board_id`) REFERENCES `board` (`board_id`),
  CONSTRAINT `FK5my97to6xmqeuycmeelttgj44` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`),
  CONSTRAINT `FKlri30okf66phtcgbe5pok7cc0` FOREIGN KEY (`parent_id`) REFERENCES `comments` (`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (_binary '',1,'userA','$2a$10$md8IjPx8A8FC5D4Bclx0JOqAHOJ6ExzSVBFihfxWlGGRXAczboMWK','ROLE_ADMIN'),(_binary '',2,'userB','$2a$10$sdWSn34cONBpDdtO7p3Zv.Z6DCjPhxabclabnVdgOHZ9KDLL.flNm','ROLE_USER'),(_binary '',3,'userC','$2a$10$JOlOhFTASUt4vBq0oEdiZu1exO5fe0WeajGFS6g2U2R5ImBZ0CVDa','ROLE_USER'),(_binary '',4,'userD','$2a$10$dyyAYLlTYZd9fp8ZqZbbjukORHs7KogFY0F4MtmfbhF1zzj.r21CC','ROLE_USER');
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
  KEY `FKjd46p0k0waihmq9r3ng6m5fr5` (`comment_id`),
  CONSTRAINT `FKjd46p0k0waihmq9r3ng6m5fr5` FOREIGN KEY (`comment_id`) REFERENCES `comments` (`comment_id`)
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
/*!50003 DROP PROCEDURE IF EXISTS `call_add_comment` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`moa_ljh`@`localhost` PROCEDURE `call_add_comment`(
	IN pi_board_id LONG,
	IN pi_member_id LONG,
	IN pi_content VARCHAR(255),
	IN pi_parent_id LONG

)
BEGIN

    INSERT INTO COMMENTS(BOARD_ID, COMMENT_DATE, MEMBER_ID, PARENT_ID, CONTENT) 
    -- VALUES(pi_board_id, NOW(), pi_member_id, COALESCE(pi_parent_id, NULL), pi_content);
	VALUES(pi_board_id, NOW(), pi_member_id, COALESCE(pi_parent_id, NULL), pi_content),
    (pi_board_id, NOW(), pi_member_id, COALESCE(pi_parent_id, NULL), pi_content)
    ,(pi_board_id, NOW(), pi_member_id, COALESCE(pi_parent_id, NULL), pi_content);
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
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
	INNER JOIN MEMBER m
	ON b.MEMBER_ID = m.MEMBER_ID
	-- 댓글 정렬
	-- LEFT JOIN COMMENT c
	-- ON b.BOARD_ID = c.BOARD_ID
	ORDER BY b.BOARD_ID DESC -- , coalesce(c.PARENT_ID, c.COMMENT_ID), c.COMMENT_DATE
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
    IN pi_member_id INT,
    IN pi_board_id INT,
    IN pi_is_dislike BOOLEAN
)
BEGIN
    DECLARE v_current_likes BOOLEAN;
    DECLARE v_current_dislikes BOOLEAN;
    
    SELECT LIKES, DISLIKES INTO v_current_likes, v_current_dislikes
    FROM BOARD_PREFERENCE AS BP
    WHERE BP.MEMBER_ID = pi_member_id AND BP.BOARD_ID = pi_board_id;
    
    IF v_current_likes IS NOT NULL OR v_current_dislikes IS NOT NULL THEN
        UPDATE BOARD_PREFERENCE AS BP
        SET LIKES = IF(pi_is_dislike, FALSE, NOT v_current_likes),
            DISLIKES = IF(pi_is_dislike, NOT v_current_dislikes, FALSE)
        WHERE BP.MEMBER_ID = pi_member_id AND BP.BOARD_ID = pi_board_id;
    ELSE
        INSERT INTO BOARD_PREFERENCE (MEMBER_ID, BOARD_ID, LIKES, DISLIKES)
        VALUES (pi_member_id, pi_board_id, IF(pi_is_dislike, 0, 1), IF(pi_is_dislike, 1, 0));
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

-- Dump completed on 2024-03-01 12:24:17
