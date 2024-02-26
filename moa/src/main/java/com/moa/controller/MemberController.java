package com.moa.controller;

import com.moa.controller.form.MemberForm;
import com.moa.domain.Member;
import com.moa.dto.response.MemberDetails;
import com.moa.dto.response.MemberResponseDto;
import com.moa.service.MemberService;
import com.moa.service.impl.MemberServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/memberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm memberForm, RedirectAttributes re) {
        boolean result = memberService.join(memberForm);
        if (!result) {
            re.addFlashAttribute("joinFail", "이미 존재하는 이름입니다.");
            re.addFlashAttribute("memberForm", memberForm);

            return "redirect:/members/new";
        }
        return "redirect:/login";
    }


    @GetMapping("/members/info")
    public String info() {
        return "members/info";
    }

    @GetMapping("/members/update")
    public String updateForm(@AuthenticationPrincipal MemberDetails member, Model model) {
        MemberForm memberForm = new MemberForm();
        memberForm.setName(member.getUsername());
        model.addAttribute("memberForm", memberForm);

        return "members/memberUpdate";
    }

    @PostMapping("/members/update")
    public String update(@AuthenticationPrincipal MemberDetails member, MemberForm memberForm, HttpServletRequest request) {
        memberForm.setId(member.getId());
        boolean result = memberService.update(memberForm);

        if (result) {
            member.setUsername(memberForm.getName());
        }
        return "redirect:/members/info";
    }

    @GetMapping("/members/retire")
    public String retireForm() {
        return "members/retireForm";
    }

    @PostMapping("/members/retire")
    public String retired(@AuthenticationPrincipal MemberDetails member, HttpServletRequest request, RedirectAttributes re) {
        memberService.retire(member.getId());

        return "redirect:/logout";
    }

    // 전체 및 등급별 유저 검색
    @GetMapping("/admin/members")
    public String getMembersByRole(@RequestParam(value = "role", required = false) String roleStr, Model model) {

        List<Member> members;
        if (roleStr == null) { // 처음 접속시 빈 리스트
            members = new ArrayList<>();
        } else if (roleStr.isEmpty()) {
            return "boards/memberList";
        } else if (roleStr.equals("ALL")) {
            members = memberService.findAll();
        } else {
            members = memberService.findAllByRole(roleStr);
        }

        model.addAttribute("members", members);
        return "boards/memberList";
    }
}