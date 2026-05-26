package com.nougatbar.lxp.member.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

`@Controller`
`@RequestMapping`("/auth")
public class LonginController {
    // 로그인 폼 화면
    `@GetMapping`("/login")
    public String login() {
        return "auth/login";
    }
}

    // 로그인 실패화면
    @GetMapping("/fail")
    public ModelAndView loginFail(@RequestParam String message, ModelAndView mv) {
        mv.addObject("message", message);
        mv.setViewName("auth/fail");
        return mv;
    }

}
