package com.moa.controller;

import com.moa.controller.form.BoardForm;
import com.moa.controller.form.CommentForm;
import com.moa.domain.Board;
import com.moa.domain.BoardPreference;
import com.moa.domain.Comments;
import com.moa.dto.response.BoardResponseDto;
import com.moa.dto.response.MemberDetails;
import com.moa.service.MemberService;
import com.moa.service.impl.BoardPreferenceService;
import com.moa.service.impl.BoardService;
import com.moa.service.impl.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    private final CommentService commentService;
    private final BoardPreferenceService boardPreferenceService;


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
    public String createBoardForm(Model model) {

        model.addAttribute("boardForm", new BoardForm());
        return "boards/boardForm";
    }

    @PostMapping("/boards/new")
    public String createBoard(@AuthenticationPrincipal MemberDetails member, BoardForm boardForm) {
        boardService.save(boardForm, member);
        return "redirect:/boards/list";
    }

    @GetMapping("/boards/{boardId}")
    public String detail(@PathVariable("boardId") Long boardId, Model model) {
        Board findBoard = boardService.findOne(boardId);
        BoardForm form = new BoardForm();
        form.setId(boardId);
        form.setTitle(findBoard.getTitle());
        form.setContent(findBoard.getContent());
        List<Comments> comments = commentService.findAllByBoardId(boardId);

        model.addAttribute("boardForm", form);
        model.addAttribute("commentForm", new CommentForm());
        model.addAttribute("comments", comments);

        // 좋아요 수와 싫어요 수를 가져와서 모델에 추가
        Long likesCount = boardPreferenceService.countLikes(boardId);
        Long dislikesCount = boardPreferenceService.countDislikes(boardId);
        model.addAttribute("likeCount", likesCount);
        model.addAttribute("dislikeCount", dislikesCount);
        return "boards/detail";
    }
    @GetMapping("/boards/{boardId}/2")
    public String detail2(@PathVariable("boardId") Long boardId, Model model) {
        Board findBoard = boardService.findOne(boardId);
        List<BoardPreference> boardPreferences = findBoard.getBoardPreferences();
        int likesCount = 0;
        int dislikesCount = 0;
        for (BoardPreference bp : boardPreferences) {
            if (bp.isLikes()) {
                likesCount++;
            }
             else if(bp.isDislikes()) {
                dislikesCount++;
            }
        }
        model.addAttribute("boardForm", findBoard);
        model.addAttribute("likeCount", likesCount);
        model.addAttribute("dislikeCount", dislikesCount);


        // 댓글 작성용
        model.addAttribute("commentForm", new CommentForm());
        return "boards/detail2";
    }
}
