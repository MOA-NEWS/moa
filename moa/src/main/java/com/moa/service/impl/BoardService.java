package com.moa.service.impl;

import com.moa.controller.form.BoardForm;
import com.moa.domain.Board;
import com.moa.domain.Member;
import com.moa.dto.response.BoardResponseDto;
import com.moa.dto.response.MemberDetails;
import com.moa.repository.BoardRepository;
import com.moa.repository.MemberRepository;
import com.moa.repository.PageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final PageRepository pageRepository;


    @Transactional
    public void save(Board board) {
        boardRepository.save(board);
    }

    @Transactional
    public void save(BoardForm boardForm, MemberDetails member) {
        memberRepository.findById(member.getId()).ifPresent(m -> {
            boardRepository.save(new Board(boardForm.getTitle(), boardForm.getContent(), m));
        });
    }

    public Board findOne(Long boardId) {
        return boardRepository.findById(boardId).orElse(null);
    }

    public List<BoardResponseDto> findAll(int page) {
        return boardRepository.findAll(page).stream().map(BoardResponseDto::new).collect(Collectors.toList());
    }

    public Page<BoardResponseDto> paging(Pageable pageable) {
        int page = pageable.getPageNumber() - 1; // page 위치에 있는 값은 0부터 시작한다.
        int pageLimit = 10; // 한페이지에 보여줄 글 개수

        // 한 페이지당 10개식 글을 보여주고 정렬 기준은 ID기준으로 내림차순
        Page<Board> boardPages = pageRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));

        // 목록 : id, title, content, member, postDate, comments

        return boardPages.map(BoardResponseDto::new);
    }
}
