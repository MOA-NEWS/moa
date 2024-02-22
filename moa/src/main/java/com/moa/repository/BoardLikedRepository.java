package com.moa.repository;

import com.moa.domain.BoardLiked;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BoardLikedRepository {
    private final EntityManager em;

    public void save(BoardLiked boardLiked) {
        em.persist(boardLiked);
    }

    // 좋아요 여부 확인
    public boolean existsByMemberIdAndBoardId(Long memberId, Long boardId) {
        Long count = em.createQuery("SELECT COUNT(bl) FROM BoardLiked bl WHERE bl.member.id = :memberId AND bl.board.id = :boardId", Long.class)
                .setParameter("memberId", memberId)
                .setParameter("boardId", boardId)
                .getSingleResult();
        return count > 0;
    }

    // 좋아요 상태 조회
    public BoardLiked findByMemberIdAndBoardId(Long memberId, Long boardId) {
        return em.createQuery("SELECT bl FROM BoardLiked bl WHERE bl.member.id = :memberId AND bl.board.id = :boardId", BoardLiked.class)
                .setParameter("memberId", memberId)
                .setParameter("boardId", boardId)
                .getSingleResult();
    }

    // 좋아요 상태 업데이트
    public void updateLikedStatus(Long memberId, Long boardId, boolean liked) {
        em.createQuery("UPDATE BoardLiked bl SET bl.liked = :liked WHERE bl.member.id = :memberId AND bl.board.id = :boardId")
                .setParameter("liked", liked)
                .setParameter("memberId", memberId)
                .setParameter("boardId", boardId)
                .executeUpdate();
    }

    // 좋아요 누적수
    public Long countLikesByBoardId(Long boardId) {
        return em.createQuery("SELECT COUNT(bl) FROM BoardLiked bl WHERE bl.board.id = :boardId AND bl.liked = true", Long.class)
                .setParameter("boardId", boardId)
                .getSingleResult();
    }

//    // 좋아요 하드딜리트 참고용
//    public void deleteByMemberIdAndBoardId(Long memberId, Long boardId) {
//        em.createQuery("DELETE FROM BoardLiked bl WHERE bl.member.id = :memberId AND bl.board.id = :boardId")
//                .setParameter("memberId", memberId)
//                .setParameter("boardId", boardId)
//                .executeUpdate();
//    }
}