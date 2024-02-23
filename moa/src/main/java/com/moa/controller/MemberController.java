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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    // 모든 일반유저 검색
    @GetMapping("/admin/members")
    public String getAllUsers(Model model, HttpServletRequest request) {
        MemberForm memberForm = (MemberForm) request.getSession().getAttribute("user");
        if (memberForm == null) {
            // 로그인되어 있지 않은 경우 로그인 페이지로 리다이렉트
            return "redirect:/members/login";
        }

        // 현재 로그인된 사용자의 역할이 ADMIN인지 확인
        Member findMember = memberService.findOne(memberForm.getId());
        if (findMember.getRole() != RoleStatus.ADMIN) {
            // 관리자가 아닌 경우 접근 권한이 없음을 알리는 페이지로 이동하거나,
            // 다른 적절한 처리를 수행할 수 있습니다.
            return "redirect:/access-denied"; // 예시로 접근 거부 페이지로 리다이렉트
        }

        // 현재 로그인된 사용자가 관리자인 경우, 모든 유저 정보를 가져와서 모델에 추가
        List<Member> members = memberService.findAllMembers();
        model.addAttribute("members", members);

        return "boards/memberList";
    }
}