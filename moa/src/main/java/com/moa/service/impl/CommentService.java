package com.moa.service.impl;

import com.moa.controller.form.CommentForm;
import com.moa.domain.Board;
import com.moa.domain.Comments;
import com.moa.domain.Comments;
import com.moa.domain.Member;
import com.moa.repository.BoardRepository;
import com.moa.repository.CommentsRepository;
import com.moa.repository.CommentsRepository;
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

    private final CommentsRepository commentRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void save(Comments comment) {
        commentRepository.save(comment);
    }

    public Comments findOne(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    public List<Comments> findAll() {
        return commentRepository.findAll();
    }

    public List<Comments> findAllByBoardId(Long boardId) {
        return commentRepository.findAllByBoardId(boardId);
    }


    @Transactional
    public void addComment(Long boardId, Long memberId, CommentForm commentForm) {
        //조회
        Optional<Board> findBoard = boardRepository.findById(boardId);
        Optional<Member> findMember = memberRepository.findById(memberId);
        Comments parent = null;

        //확인
        Board board = findBoard.orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));
        Member member = findMember.orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));
        if (commentForm.getParentId() != null) {
            parent = commentRepository.findById(commentForm.getParentId()).orElse(null);
        }

        //생성
        Comments comment = Comments.createComment(commentForm.getContent(), member, board);
        if (parent != null) {
            comment.setParent(parent);
        }

        //저장
        commentRepository.save(comment);
    }

    @Transactional
    public long spAddComment(Long boardId, Long memberId, CommentForm commentForm) {
        //저장
        return commentRepository.spSaveComment(boardId, memberId, commentForm.getContent(), commentForm.getParentId());

    }
}
