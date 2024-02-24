package com.moa.controller.old;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//@Controller
@RequiredArgsConstructor
public class LogoutController {
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);// 새로운 세션을 생성하지 않음

        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }
}
