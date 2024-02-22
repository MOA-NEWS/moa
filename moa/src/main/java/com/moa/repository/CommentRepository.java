package com.moa.repository;

import com.moa.domain.Comment;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CommentRepository {
    private final EntityManager em;

    public void save(Comment comment) {
        em.persist(comment);
    }

    public Optional<Comment> findById(Long commentId) {
        return Optional.ofNullable(em.find(Comment.class, commentId));
    }

    public List<Comment> findAll() {
        return em.createQuery("select c from Comment c", Comment.class)
                .getResultList();
    }

    public List<Comment> findAllByBoardId(Long boardId) {
        String jpql = "select c from Comment c left join fetch c.parent p where c.board.id = :id order by coalesce(p.id, c.id), c.commentDate";
        return em.createQuery(jpql, Comment.class)
                .setParameter("id", boardId)
                .getResultList();
    }

}
