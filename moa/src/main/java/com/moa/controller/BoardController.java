package com.moa.controller;

import com.moa.controller.form.BoardForm;
import com.moa.controller.form.CommentForm;
import com.moa.controller.form.MemberForm;
import com.moa.domain.Board;
import com.moa.domain.Member;
import com.moa.service.BoardService;
import com.moa.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final MemberService memberService;

    // 페이징 : 아직안함
    @GetMapping("/boards/list")
    public String boardList(Model model) {
        List<Board> boards = boardService.findAll();
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

    @GetMapping("/boards/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        Board findBoard = boardService.findOne(id);
        BoardForm form = new BoardForm();
        form.setId(id);
        form.setTitle(findBoard.getTitle());
        form.setContent(findBoard.getContent());

        model.addAttribute("boardForm", form);
        model.addAttribute("commentForm", new CommentForm());
        return "boards/detail";
    }


}
