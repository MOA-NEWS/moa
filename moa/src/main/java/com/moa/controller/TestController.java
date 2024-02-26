package com.moa.controller;

import com.moa.controller.form.BoardForm;
import com.moa.controller.form.CommentForm;
import com.moa.controller.form.MemberForm;
import com.moa.domain.Board;
import com.moa.domain.Comment;
import com.moa.dto.response.MemberDetails;
import com.moa.service.TestService;
import com.moa.service.impl.BoardService;
import com.moa.service.impl.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class TestController {

    private final BoardService boardService;
    private final CommentService commentService;
    private final TestService testService;

    @GetMapping("/jpaTest/{boardId}")
    public String beforeDetail(@PathVariable("boardId") Long boardId, Model model) {
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
        Long likesCount = testService.beforeCountLikes(boardId);
        Long dislikesCount = testService.beforeCountDislikes(boardId);
        model.addAttribute("likeCount", likesCount);
        model.addAttribute("dislikeCount", dislikesCount);
        return "test/testJpaDetail";
    }

    // 좋아요 / 싫어요 통합
    @PostMapping({"/jpaTest/{boardId}/dislike", "/jpaTest/{boardId}/like"})
    public String beforeTogglePrefer(@AuthenticationPrincipal MemberDetails member, @PathVariable Long boardId, HttpServletRequest request) {
        // path가 .../dislike인지 확인
        boolean isDislike = request.getServletPath().contains("dis");

        LocalDateTime startTime = LocalDateTime.now();
        // 게시글 선호도 토글 수행
        for (int i = 0; i < 10; i++) {
            testService.beforeTogglePrefer(member.getId(), boardId, isDislike);
        }
        LocalDateTime endTime = LocalDateTime.now();


        Duration duration = Duration.between(startTime, endTime);
        System.out.println(duration.getSeconds() + "초");
        System.out.println(duration.toMillis() + "밀리초");
        // 게시글 상세 페이지로 리다이렉트하며 토글된 후에 게시글 ID를 함께 전달
        return "redirect:/jpaTest/" + boardId;
    }

    @GetMapping("/spTest/{boardId}")
    public String afterDetail(@PathVariable("boardId") Long boardId, Model model) {
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
        Long likesCount = testService.afterCountLikes(boardId);
        Long dislikesCount = testService.afterCountDislikes(boardId);
        model.addAttribute("likeCount", likesCount);
        model.addAttribute("dislikeCount", dislikesCount);
        return "test/testSpDetail";
    }

    // 좋아요 / 싫어요 통합
    @PostMapping({"/spTest/{boardId}/dislike", "/spTest/{boardId}/like"})
    public String afterTogglePrefer(@AuthenticationPrincipal MemberDetails member, @PathVariable Long boardId, HttpServletRequest request) {
        // path가 .../dislike인지 확인
        boolean isDislike = request.getServletPath().contains("dis");

        LocalDateTime startTime = LocalDateTime.now();
        // 게시글 선호도 토글 수행
        for (int i = 1; i <= 1001; i++) {
            testService.afterTogglePrefer(member.getId(), boardId, isDislike);
        }
        LocalDateTime endTime = LocalDateTime.now();

        Duration duration = Duration.between(startTime, endTime);
        System.out.println(duration.getSeconds() + "초");
        System.out.println(duration.toMillis() + "밀리초");

        // 게시글 상세 페이지로 리다이렉트하며 토글된 후에 게시글 ID를 함께 전달
        return "redirect:/spTest/" + boardId;
    }
}