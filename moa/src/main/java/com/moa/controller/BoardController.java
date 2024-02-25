package com.moa.controller;

import com.moa.controller.form.BoardForm;
import com.moa.controller.form.CommentForm;
import com.moa.controller.form.MemberForm;
import com.moa.domain.Board;
import com.moa.domain.Comment;
import com.moa.domain.Member;
import com.moa.dto.response.BoardResponseDto;
import com.moa.service.BoardPreferenceService;
import com.moa.service.BoardService;
import com.moa.service.CommentService;
import com.moa.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final MemberService memberService;
    private final CommentService commentService;
    private final BoardPreferenceService boardPreferenceService;

    // 페이징 : 아직안함
//    @GetMapping("/boards/list")
    public String boardList(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
        List<BoardResponseDto> boards = boardService.findAll(page);
        model.addAttribute("boards", boards);
        return "boards/list";
    }
    // 페이징 구현 중
    @GetMapping("/boards/list")
    public String boardPaging(@PageableDefault(page = 1) Pageable pageable, Model model) {
        Page<BoardResponseDto> boards = boardService.paging(pageable);

        /**
         * blockLimit : page 개수 설정
         * 현재 사용자가 선택한 페이지 앞 뒤로 3페이지씩만 보여준다.
         * ex : 현재 사용자가 4페이지라면 2, 3, (4), 5, 6
         */
        int blockLimit = 10;
        int startPage = (((int) Math.ceil(((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        int endPage = Math.min((startPage + blockLimit - 1), boards.getTotalPages());
//        int startPage = (( pageable.getPageNumber() / blockLimit) - 1) * blockLimit + 1;
//        int endPage = startPage + blockLimit - 1;

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("boards", boards);
        return "boards/list";
    }

    @GetMapping("/boards/new")
    public String createBoardForm(Model model, HttpServletRequest request, RedirectAttributes re) {
        MemberForm mForm = (MemberForm) request.getSession().getAttribute("user");
        if(mForm == null) {
            // Model에 저장됨
            re.addFlashAttribute("loginFail", "로그인 후에 이용해주세요");
            return "redirect:/login";
        }
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
        model.addAttribute("comments",comments);

        // 좋아요 수와 싫어요 수를 가져와서 모델에 추가
        Long likesCount = boardPreferenceService.countLikes(boardId);
        Long dislikesCount = boardPreferenceService.countDislikes(boardId);
        model.addAttribute("likeCount", likesCount);
        model.addAttribute("dislikeCount", dislikesCount);
        return "boards/detail";
    }
}
