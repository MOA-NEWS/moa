package com.moa.controller;

import com.moa.controller.form.MemberForm;
import com.moa.dto.response.MemberDetails;
import com.moa.service.impl.BoardPreferenceService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class BoardPreferenceController {

    private final BoardPreferenceService boardPreferenceService;

    // 좋아요 / 싫어요 통합
    @PostMapping({"/boards/{boardId}/dislike", "/boards/{boardId}/like"})
    public String togglePrefer(@AuthenticationPrincipal MemberDetails member, @PathVariable Long boardId, RedirectAttributes re, HttpServletRequest request) {
        // path가 .../dislike인지 확인
        boolean isDislike = request.getServletPath().contains("dis");
        // 세션에서 회원 ID 가져오기
        if (member == null) {
            // 세션에 회원 ID가 없으면 로그인 페이지로 리다이렉트
            re.addFlashAttribute("loginFail", "로그인 후에 이용해주세요");
            return "redirect:/login";
        }
        // 게시글 선호도 토글 수행
        boardPreferenceService.togglePrefer(member.getId(), boardId, isDislike);

        // 게시글 상세 페이지로 리다이렉트하며 토글된 후에 게시글 ID를 함께 전달
        return "redirect:/boards/" + boardId;
    }
}