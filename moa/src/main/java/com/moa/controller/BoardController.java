package com.moa.controller;

import com.moa.controller.form.BoardForm;
import com.moa.controller.form.CommentForm;
import com.moa.controller.form.MemberForm;
import com.moa.domain.Board;
import com.moa.domain.BoardLiked;
import com.moa.domain.Comment;
import com.moa.domain.Member;
import com.moa.service.BoardLikedService;
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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final MemberService memberService;
    private final CommentService commentService;
    private final BoardLikedService boardLikedService;

    // 페이징 : 아직안함
    @GetMapping("/boards/list")
    public String boardList(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
        List<Board> boards = boardService.findAll(page);
        model.addAttribute("boards", boards);
        return "boards/list";
    }

    @GetMapping("/boards/new")
    public String createBoardForm(Model model) {
        model.addAttribute("boardForm", new BoardForm());
        return "boards/boardForm";
    }

    @PostMapping("/boards/new")
    public String createBoard(BoardForm boardForm, HttpServletRequest request) {
        MemberForm mForm = (MemberForm) request.getSession().getAttribute("user");
        Member member = memberService.findOne(mForm.getId());
        if (member != null) {
            boardService.save(boardForm, member);
        }


        return "redirect:/boards/list";
    }

    @GetMapping("/boards/{boardId}")
    public String detail(@PathVariable("boardId") Long boardId, Model model) {
        Board findBoard = boardService.findOne(boardId);
        BoardForm form = new BoardForm();
        form.setId(boardId);
        form.setTitle(findBoard.getTitle());
        form.setContent(findBoard.getContent());
        List<Comment> comments = commentService.findAllByBoardId(boardId);

        model.addAttribute("boardForm", form);
        model.addAttribute("commentForm", new CommentForm());
        model.addAttribute("comments", comments);
        model.addAttribute("likeCount", boardLikedService.countLikes(boardId));
        return "boards/detail";
    }


}
