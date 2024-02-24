package com.moa.controller;

import com.moa.controller.form.MemberForm;
import com.moa.domain.Member;
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
        return "redirect:/login";
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
}