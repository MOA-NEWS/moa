package com.moa.controller.old;

import com.moa.controller.form.MemberForm;
import com.moa.domain.old.Member;
import com.moa.service.impl.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//@Controller
@RequiredArgsConstructor
public class LoginController {
    private final MemberService memberService;

    @GetMapping("/login")
    public String loginFrom(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "login/loginForm";
    }

    //인터셉터로 이동 때 마다 로그인 여부 확인(만들어야함) 혹은 스프링 시큐리티 사용
    @PostMapping("/login")
    public String login(MemberForm memberForm, HttpServletRequest request, RedirectAttributes re) {
        Member findMember = memberService.findOne(memberForm.getName());
        if (findMember == null) {
            // Model에 저장됨
            re.addFlashAttribute("loginFail", "이름을 확인 해주세요");
            return "redirect:/login";
        }
        memberForm.setId(findMember.getId());
        memberForm.setRole(findMember.getRole());
        memberForm.setName(findMember.getName());

        request.getSession().setAttribute("user", memberForm);
        return "redirect:/";
    }
}
