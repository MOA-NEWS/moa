package com.moa.repository;

import com.moa.domain.BoardPreference;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BoardPreferenceRepository {
    private final EntityManager em;

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

    // 좋아요 누적수
    public Long countLikesByBoardId(Long boardId) {
        return em.createQuery("SELECT COUNT(bl) FROM BoardPreference bl WHERE bl.board.id = :boardId AND bl.likes = true", Long.class)
                .setParameter("boardId", boardId)
                .getSingleResult();
    }

    // 좋아요 누적수
    public Long countDislikesByBoardId(Long boardId) {
        return em.createQuery("SELECT COUNT(bl) FROM BoardPreference bl WHERE bl.board.id = :boardId AND bl.dislikes = true", Long.class)
                .setParameter("boardId", boardId)
                .getSingleResult();
    }
}