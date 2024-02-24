package com.moa.repository;

import com.moa.domain.Board;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BoardRepository {
    private final EntityManager em;
    private static final int PAGE_SIZE = 10;

    public void save(Board board) {
        em.persist(board);
    }

    public Optional<Board> findById(Long id) {
        return Optional.ofNullable(em.find(Board.class, id));
    }

    //fetch join 사용
    //Dto로 받아야함
    public List<Board> findAll() {
        return em.createQuery("select b from Board b", Board.class)
                .getResultList();
    }

    public List<Board> findAll(int page) {
        return em.createQuery("select b from Board b", Board.class)
                .setFirstResult((page - 1) * PAGE_SIZE)
                .setMaxResults(PAGE_SIZE)
                .getResultList();
    }

    public List<Board> findAllWithJoin() {
        String select = "b.board_id, b.member_id, b.title, b.content, b.post_date, c.comment_id, c.text, c.comment_date, r.reply_id, r.reply_text, r.reply_date";
        String jpql = "select " + select +
                      " from Board b join Comment c on b.id = c.board.id join Reply r on c.id = r.comment.id";
        return em.createQuery(jpql, Board.class)
                .getResultList();
    }

    public List<Board> findAllWithFetch() {
        String jpql = "select b from Board b" +
                      " left join fetch b.comments p" +
                      " left join fetch p.children c";
        return em.createQuery(jpql, Board.class)
                .getResultList();
    }


}
