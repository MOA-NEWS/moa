package com.moa.service;

import com.moa.controller.form.BoardForm;
import com.moa.domain.Board;
import com.moa.domain.Member;
import com.moa.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {
    private final BoardRepository boardRepository;


    @Transactional
    public void save(Board board) {
        boardRepository.save(board);
    }
    @Transactional
    public void save(BoardForm boardForm, Member member) {
        Board board = new Board(boardForm.getTitle(), boardForm.getContent(), member);
        boardRepository.save(board);
    }

    public Board findOne(Long boardId) {
        return boardRepository.findById(boardId).orElse(null);
    }

    public List<Board> findAll(int page) {
        return boardRepository.findAll(page);
    }


}
