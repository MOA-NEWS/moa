CREATE DEFINER=`root`@`localhost` PROCEDURE `call_toggle_preference`(
    IN member_Id INT, -- 유저 조회를 위한 멤버 ID
    IN board_id INT,
    IN is_dislike BOOLEAN
)
BEGIN
    DECLARE v_current_likes BOOLEAN;
    DECLARE v_current_dislikes BOOLEAN;

    SELECT likes, dislikes INTO v_current_likes, v_current_dislikes
    FROM board_preference as b
    WHERE b.member_id = member_Id AND b.board_id = board_id;

-- 값이 있으면 실행
    IF v_current_likes IS NOT NULL OR v_current_dislikes IS NOT NULL THEN
        UPDATE board_preference as b
        SET likes = IF(is_dislike, FALSE, NOT v_current_likes),
            dislikes = IF(is_dislike, NOT v_current_dislikes, FALSE)
        WHERE b.member_id = member_Id AND b.board_id = board_id;
    ELSE
        INSERT INTO board_preference (member_id, board_id, likes, dislikes)
        VALUES (member_Id, board_id, IF(is_dislike, 0, 1), IF(is_dislike, 1, 0));
    END IF;
END
--------------------------------------
