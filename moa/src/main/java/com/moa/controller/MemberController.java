package com.moa.controller;

import com.moa.controller.form.MemberForm;
import com.moa.domain.Member;
import com.moa.domain.RoleStatus;
import com.moa.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
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
    public String create(MemberForm memberForm) {
        memberService.join(memberForm);
        return "redirect:/members/login";
    }

    @GetMapping("/members/login")
    public String loginFrom(Model model) {
        model.addAttribute("memberForm", new MemberForm());
//        model.addAttribute("loginFail", model.getAttribute("loginFail"));
        return "members/loginForm";
    }

    //인터셉터로 이동 때 마다 로그인 여부 확인(만들어야함) 혹은 스프링 시큐리티 사용
    @PostMapping("/members/login")
    public String login(MemberForm memberForm, HttpServletRequest request, RedirectAttributes re) {
        Member findMember = memberService.findOne(memberForm.getName());
        if (findMember == null) {
            // Model에 저장됨
            re.addFlashAttribute("loginFail", "이름을 확인 해주세요");
            return "redirect:/members/login";
        }
        memberForm.setId(findMember.getId());
        request.getSession().setAttribute("user", memberForm);
        request.getSession().setAttribute("role", findMember.getRole().toString());
        request.getSession().setAttribute("name", findMember.getName());
        return "redirect:/";
    }

    @GetMapping("/members/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);// 새로운 세션을 생성하지 않음

        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

    @GetMapping("/members/info")
    public String info(Model model, HttpServletRequest request) {
        MemberForm user = (MemberForm) request.getSession().getAttribute("user");

        Member findMember = memberService.findOne(user.getId());
        if (findMember != null) {
            MemberForm form = new MemberForm();
            form.setName(findMember.getName());
            form.setRole(findMember.getRole());

            model.addAttribute("memberForm", form);
        }

        return "members/info";
    }

    @GetMapping("/members/update")
    public String updateForm(Model model, HttpServletRequest request) {

        MemberForm user = (MemberForm) request.getSession().getAttribute("user");
        if (user == null) return "redirect:/members/login";

        model.addAttribute("memberForm", user);
        return "members/memberUpdate";
    }

    @PostMapping("/members/update")
    public String update(MemberForm memberForm, HttpServletRequest request) {
        boolean result = memberService.update(memberForm);
        if (result) request.getSession().setAttribute("user", memberForm);
        return "redirect:/members/info";
    }

    @GetMapping("/members/retire")
    public String retireForm(Model model, HttpServletRequest request) {
        MemberForm user = (MemberForm) request.getSession().getAttribute("user");
        model.addAttribute("memberForm", user);
        return "members/retireForm";
    }

    @PostMapping("/members/retire")
    public String retired(HttpServletRequest request, RedirectAttributes re) {
        HttpSession session = request.getSession();
        MemberForm user = (MemberForm) session.getAttribute("user");
        boolean result = false;

        if (user != null)
            result = memberService.retire(user.getId());

        if (result) {
            // 정상처리 됨
            session.invalidate();
            return "redirect:/";
        }
        re.addFlashAttribute("retireFail", "처리 중에 문제가 발생되었습니다. 다시 한번 시도해주세요.");
        return "redirect:/members/retire";
    }

    // 전체 및 등급별 유저 검색
    @GetMapping("/admin/members")
    public String getMembersByRole(@RequestParam(value = "role", required = false) String roleStr, Model model, HttpServletRequest request) {
        MemberForm memberForm = (MemberForm) request.getSession().getAttribute("user");

        // 세션이 없을경우 로그인 페이지로 리다이렉트
        if (memberForm == null) {
            return "redirect:/members/login";
        }

        // 관리자만 접근 가능, 아닐경우 에러페이지
        Member findMember = memberService.findOne(memberForm.getId());
        if (findMember.getRole() != RoleStatus.ADMIN) {
            return "redirect:/not_authorized";
        }

        List<Member> members;
        if (roleStr == null) { // 처음 접속시 빈 리스트
            members = new ArrayList<>();
        } else if ("ALL".equals(roleStr)) {
            members = memberService.findAll();
        } else {
            RoleStatus role = RoleStatus.valueOf(roleStr); // 문자열을 RoleStatus로 변환
            members = switch (role) {
                case ADMIN -> memberService.findAllAdmins();
                case USER -> memberService.findAllUsers();
            };
        }

        model.addAttribute("members", members);
        return "boards/memberList";
    }
}