package com.moa.controller;

import com.moa.controller.form.MemberForm;
import com.moa.service.BoardPreferenceService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class BoardPreferenceController {

    private final BoardPreferenceService boardPreferenceService;

    // 좋아요
//    @PostMapping("/boards/{boardId}/like")
    public String toggleLike(@PathVariable Long boardId, HttpServletRequest request) {
        // 세션에서 회원 ID 가져오기
        MemberForm memberForm = (MemberForm) request.getSession().getAttribute("user");
        if (memberForm == null) {
            // 세션에 회원 ID가 없으면 로그인 페이지로 리다이렉트
            return "redirect:/members/login";
        }
        // 게시글에 좋아요 토글 수행
        boardPreferenceService.toggleLike(memberForm.getId(), boardId);

        // 게시글 상세 페이지로 리다이렉트하며 좋아요가 토글된 후의 게시글 ID를 함께 전달
        return "redirect:/boards/" + boardId;
    }

    // 싫어요
//    @PostMapping("/boards/{boardId}/dislike")
    public String toggleDislike(@PathVariable Long boardId, HttpServletRequest request) {
        // 세션에서 회원 ID 가져오기
        MemberForm memberForm = (MemberForm) request.getSession().getAttribute("user");
        if (memberForm == null) {
            // 세션에 회원 ID가 없으면 로그인 페이지로 리다이렉트
            return "redirect:/members/login";
        }
        // 게시글에 싫어요 토글 수행
        boardPreferenceService.toggleDislike(memberForm.getId(), boardId);

        // 게시글 상세 페이지로 리다이렉트하며 싫어요가 토글된 후의 게시글 ID를 함께 전달
        return "redirect:/boards/" + boardId;
    }

    // 좋아요 / 싫어요 통합
    @PostMapping({"/boards/{boardId}/dislike", "/boards/{boardId}/like"})
    public String togglePrefer(@PathVariable Long boardId, HttpServletRequest request, RedirectAttributes re) {
        // path가 .../dislike인지 확인
        boolean isDislike = request.getServletPath().contains("dis");
        // 세션에서 회원 ID 가져오기
        MemberForm memberForm = (MemberForm) request.getSession().getAttribute("user");
        if (memberForm == null) {
            // 세션에 회원 ID가 없으면 로그인 페이지로 리다이렉트
            re.addFlashAttribute("loginFail", "로그인 후에 이용해주세요");
            return "redirect:/members/login";
        }
        // 게시글 선호도 토글 수행
        boardPreferenceService.togglePrefer(memberForm.getId(), boardId, isDislike);

        // 게시글 상세 페이지로 리다이렉트하며 토글된 후에 게시글 ID를 함께 전달
        return "redirect:/boards/" + boardId;
    }
}