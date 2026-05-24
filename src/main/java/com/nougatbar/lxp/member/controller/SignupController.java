package com.nougatbar.lxp.member.controller;

import com.nougatbar.lxp.member.dto.SignupRequestDTO;
import com.nougatbar.lxp.member.exceptional.DuplicateFieldException;
import com.nougatbar.lxp.member.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/members")
public class SignupController {

    private final MemberService memberService;

    public SignupController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 회원가입 폼 화면
    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("signupRequest", new SignupRequestDTO());
        return "members/signup";
    }

    // 회원가입 처리
    @PostMapping("/signup")
    public String signup(@ModelAttribute SignupRequestDTO request, Model model) {
        try {
            memberService.register(request);
            return "redirect:/auth/login";
        } catch (DuplicateFieldException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("signupRequest", request);
            return "members/signup";
        }
    }

}
