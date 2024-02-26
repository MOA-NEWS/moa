package com.moa.controller;

import com.moa.controller.form.MemberForm;
import com.moa.domain.Member;
import com.moa.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class LoginController {

    @GetMapping("/login")
    public String loginFrom(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "login/loginForm";
    }

}
