package com.moa.repository;

import com.moa.domain.Board;
import com.moa.domain.BoardPreference;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class BoardPreferenceRepository {
    private final EntityManager em;
    private final JdbcTemplate jdbcTemplate;

    public void save(BoardPreference boardPreference) {
        em.persist(boardPreference);
    }

    // 선호도 여부 확인
    public boolean existsByMemberIdAndBoardId(Long memberId, Long boardId) {
        Long count = em.createQuery("SELECT COUNT(bl) FROM BoardPreference bl WHERE bl.member.id = :memberId AND bl.board.id = :boardId", Long.class)
                .setParameter("memberId", memberId)
                .setParameter("boardId", boardId)
                .getSingleResult();
        return count > 0;
    }

    // 선호도 상태 조회
    public BoardPreference findByMemberIdAndBoardId(Long memberId, Long boardId) {
        return em.createQuery("SELECT bl FROM BoardPreference bl WHERE bl.member.id = :memberId AND bl.board.id = :boardId", BoardPreference.class)
                .setParameter("memberId", memberId)
                .setParameter("boardId", boardId)
                .getSingleResult();
    }

    // 선호도 상태 업데이트
    public void updatePreferenceStatus(Long memberId, Long boardId, boolean likes, boolean dislikes) {
        em.createQuery("UPDATE BoardPreference bl SET bl.likes = :likes, bl.dislikes = :dislikes WHERE bl.member.id = :memberId AND bl.board.id = :boardId")
                .setParameter("likes", likes)
                .setParameter("dislikes", dislikes)
                .setParameter("memberId", memberId)
                .setParameter("boardId", boardId)
                .executeUpdate();
    }

    // 좋아요 / 싫어요 통합 토글
    public void callTogglePrefer(Long memberId, Long boardId, boolean isDislike) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("call_toggle_preference");

        SqlParameterSource inParams = new MapSqlParameterSource()
                .addValue("member_id", memberId)
                .addValue("board_id", boardId)
                .addValue("is_dislike", isDislike);

        jdbcCall.execute(inParams);
    }

    // 좋아요 누적수
    public Long countLikesByBoardId(Long boardId) {
        return em.createQuery("SELECT COUNT(bl) FROM BoardPreference bl WHERE bl.board.id = :boardId AND bl.likes = true", Long.class)
                .setParameter("boardId", boardId)
                .getSingleResult();
    }
    // 좋아요 누적수
    public Long callCountLikesByBoardIdProcedure(Long boardId) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("call_count_likes_by_board_id")
                .declareParameters(
                        new SqlParameter("board_id", Types.INTEGER),
                        new SqlOutParameter("likes_count", Types.BIGINT)
                );

        SqlParameterSource inParams = new MapSqlParameterSource()
                .addValue("board_id", boardId);

        Map<String, Object> result = jdbcCall.execute(inParams);

        return (Long) result.get("likes_count");
    }

    // 싫어요 누적수
    public Long countDislikesByBoardId(Long boardId) {
        return em.createQuery("SELECT COUNT(bl) FROM BoardPreference bl WHERE bl.board.id = :boardId AND bl.dislikes = true", Long.class)
                .setParameter("boardId", boardId)
                .getSingleResult();
    }
    // 싫어요 누적수
    public Long callCountDislikesByBoardIdProcedure(Long boardId) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("call_count_dislikes_by_board_id")
                .declareParameters(
                        new SqlParameter("board_id", Types.INTEGER),
                        new SqlOutParameter("dislikes_count", Types.BIGINT)
                );

        SqlParameterSource inParams = new MapSqlParameterSource()
                .addValue("board_id", boardId);

        Map<String, Object> result = jdbcCall.execute(inParams);

        return (Long) result.get("dislikes_count");
    }
}