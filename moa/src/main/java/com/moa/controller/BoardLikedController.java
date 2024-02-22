package com.moa.controller;

import com.moa.controller.form.MemberForm;
import com.moa.service.BoardLikedService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class BoardLikedController {

    private final BoardLikedService boardLikedService;

    // 좋아요
    @PostMapping("/boards/{boardId}/like")
    public String toggleLike(@PathVariable Long boardId, HttpServletRequest request) {
        // 세션에서 회원 ID 가져오기
        MemberForm memberForm = (MemberForm) request.getSession().getAttribute("user");
        if (memberForm == null) {
            // 세션에 회원 ID가 없으면 로그인 페이지로 리다이렉트
            return "redirect:/members/login";
        }
        // 게시글에 좋아요 토글 수행
        boardLikedService.toggleLike(memberForm.getId(), boardId);

        // 게시글 상세 페이지로 리다이렉트하며 좋아요가 토글된 후의 게시글 ID를 함께 전달
        return "redirect:/boards/" + boardId;
    }
}