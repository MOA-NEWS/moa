package com.moa.repository;

import com.moa.domain.Comments;
import jakarta.persistence.EntityManager;
import jakarta.persistence.StoredProcedureQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class CommentsRepository {
    private final EntityManager em;

    public void save(Comments comment) {
        em.persist(comment);
    }

    public Optional<Comments> findById(Long commentId) {
        return Optional.ofNullable(em.find(Comments.class, commentId));
    }

    public List<Comments> findAll() {
        return em.createQuery("select c from Comments c", Comments.class)
                .getResultList();
    }

    public List<Comments> findAllByBoardId(Long boardId) {
        String jpql = "select c from Comments c left join fetch c.parent p where c.board.id = :id order by coalesce(p.id, c.id), c.commentDate";
        return em.createQuery(jpql, Comments.class)
                .setParameter("id", boardId)
                .getResultList();
    }

    @Procedure(procedureName = "call_add_comment")
    public long spSaveComment(Long boardId, Long memberId, String content, Long parentId) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("addComment");
        spq
                .setParameter("pi_board_id", boardId)
                .setParameter("pi_member_id", memberId)
                .setParameter("pi_content", content)
                .setParameter("pi_parent_id", parentId)
                .execute();
//        return spq.getOutputParameterValue("po_temp") == null ? 0 : (long) spq.getOutputParameterValue("po_temp");
        return spq.getUpdateCount();

    }
}
