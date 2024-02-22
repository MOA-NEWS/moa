package com.moa.controller;

import com.moa.controller.form.CommentForm;
import com.moa.controller.form.MemberForm;
import com.moa.domain.Board;
import com.moa.domain.Comment;
import com.moa.domain.Member;
import com.moa.service.BoardService;
import com.moa.service.CommentService;
import com.moa.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comments/{boardId}")
    public String createComment(CommentForm commentForm, @PathVariable("boardId") Long boardId, HttpServletRequest request) {
        MemberForm memberForm = (MemberForm) request.getSession().getAttribute("user");
        commentService.addComment(boardId, memberForm.getId(), commentForm);
        return "redirect:/boards/" + boardId;

    }

    @GetMapping("/comments/{boardId}/{parentId}")
    public String addReplyForm(@PathVariable("boardId") Long boardId, @PathVariable("parentId") Long commentId, Model model) {
        CommentForm form = new CommentForm();
        form.setParentId(commentId);
        form.setBoardId(boardId);
        model.addAttribute("commentForm", form);
        return "boards/reply/replyForm";
    }

    @PostMapping("/comments/reply/{boardId}")
    public String addReply(@PathVariable("boardId") Long boardId, CommentForm commentForm, HttpServletRequest request) {
        MemberForm memberForm = (MemberForm) request.getSession().getAttribute("user");


        commentService.addComment(boardId, memberForm.getId(), commentForm);
        return "redirect:/boards/" + boardId;
    }
}
