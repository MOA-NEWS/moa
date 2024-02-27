DROP PROCEDURE IF EXISTS sp_find_all_with_page_num;
CREATE PROCEDURE sp_find_city_with_string(IN page_num INT)
BEGIN
	
    SELECT b.BOARD_ID, b.TITLE, m.NAME, b.POST_DATE
    FROM BOARD b
    INNER JOIN MEMBER m
    ON b.MEMBER_ID = m.MEMBER_ID
    ORDER BY board_id desc
	LIMIT 10 OFFSET 0;
END

-- CREATE PROCEDURE sp_ClassAvg
--    @class int,
--    @minScore int,
--    @average int output
-- AS
--  DECLARE @sum int
--  DECLARE @cnt int
--  
--  SELECT @sum=SUM(Score),@cnt=COUNT(Score)
--  FROM [Scores]
--  WHERE Score >= @minScore

--  SET @average = @sum/@cnt

--  INSERT INTO RunData
--  VALUES (@class,@minScore,@avergage)
-- GO