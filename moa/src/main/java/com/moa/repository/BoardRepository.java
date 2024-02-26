package com.moa.repository;

import com.moa.domain.Board;
import com.moa.dto.response.BoardListResponseDto;
import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
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

    @Procedure(procedureName = "call_find_all_by_page_num")
    public Object findAllByPageNum(@Param("page_num") int page) {
        StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("findAllByPageNum");
        spq.setParameter("page_num", page - 1);
        spq.setParameter("page_size", PAGE_SIZE);
        spq.execute();

        return spq.getOutputParameterValue("boardId");

    }


}
