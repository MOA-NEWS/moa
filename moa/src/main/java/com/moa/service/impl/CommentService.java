package com.moa.service.impl;

import com.moa.controller.form.CommentForm;
import com.moa.domain.Board;
import com.moa.domain.Comment;
import com.moa.domain.Member;
import com.moa.repository.BoardRepository;
import com.moa.repository.CommentRepository;
import com.moa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void save(Comment comment) {
        commentRepository.save(comment);
    }

    public Comment findOne(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    public List<Comment> findAllByBoardId(Long boardId) {
        return commentRepository.findAllByBoardId(boardId);
    }


    @Transactional
    public void addComment(Long boardId, Long memberId, CommentForm commentForm) {
        //조회
        Optional<Board> findBoard = boardRepository.findById(boardId);
        Optional<Member> findMember = memberRepository.findById(memberId);
        Comment parent = null;

        //확인
        Board board = findBoard.orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));
        Member member = findMember.orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));
        if (commentForm.getParentId() != null) {
            parent = commentRepository.findById(commentForm.getParentId()).orElse(null);
        }

        //생성
        Comment comment = Comment.createComment(commentForm.getContent(), member, board);
        if (parent != null) {
            comment.setParent(parent);
        }

        //저장
        commentRepository.save(comment);
    }

}
