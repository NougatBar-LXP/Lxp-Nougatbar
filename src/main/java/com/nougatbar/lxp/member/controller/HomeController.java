package com.nougatbar.lxp.member.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 사이트 진입 경로와 권한별 안내 페이지를보여주는 단순 라우팅 컨트롤러
@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

}
