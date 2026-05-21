package com.nougatbar.lxp.cart.controller;

import com.nougatbar.lxp.cart.dto.response.CartResponse;
import com.nougatbar.lxp.cart.service.CartService;
import com.nougatbar.lxp.course.dto.response.CourseSummaryDTO;
import com.nougatbar.lxp.course.service.CourseService;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartPageController {

    private final CartService cartService;
    private final CourseService courseService;

    public CartPageController(CartService cartService, CourseService courseService) {
        this.cartService = cartService;
        this.courseService = courseService;
    }

    @GetMapping("/cart-ui")
    public String showCartPage(@RequestParam(defaultValue = "1") Long memberId, Model model) {
        List<CartResponse> carts = cartService.findCartsById(memberId);
        List<CourseSummaryDTO> courses = courseService.listAllCourses();
        Set<Long> cartCourseIds = carts.stream()
                .map(CartResponse::courseId)
                .collect(Collectors.toSet());
        int totalPrice = carts.stream()
                .mapToInt(CartResponse::price)
                .sum();

        model.addAttribute("memberId", memberId);
        model.addAttribute("courses", courses);
        model.addAttribute("carts", carts);
        model.addAttribute("cartCourseIds", cartCourseIds);
        model.addAttribute("cartCount", carts.size());
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("courseMessage",
                carts.isEmpty() ? "장바구니에 담긴 강의가 없습니다." : "담긴 강의 " + carts.size() + "개");

        return "cart/index";
    }

    @PostMapping("/cart-ui")
    public String addCart(@RequestParam Long memberId, @RequestParam Long courseId) {
        cartService.addCart(memberId, courseId);
        return "redirect:/cart-ui?memberId=" + memberId;
    }

    @PostMapping("/cart-ui/delete")
    public String deleteCart(@RequestParam Long memberId, @RequestParam Long courseId) {
        cartService.deleteCart(memberId, courseId);
        return "redirect:/cart-ui?memberId=" + memberId;
    }

    @PostMapping("/cart-ui/delete-all")
    public String deleteAllCart(@RequestParam Long memberId) {
        cartService.deleteAllCart(memberId);
        return "redirect:/cart-ui?memberId=" + memberId;
    }
}
