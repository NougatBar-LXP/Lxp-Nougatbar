package com.nougatbar.lxp.cart.service;

import com.nougatbar.lxp.cart.dto.response.CartDTO;
import com.nougatbar.lxp.cart.entity.Cart;
import com.nougatbar.lxp.cart.repository.CartRepository;
import com.nougatbar.lxp.course.dto.response.CourseSummaryDTO;
import com.nougatbar.lxp.course.service.CourseService;
import com.nougatbar.lxp.member.dto.response.MemberDTO;
import com.nougatbar.lxp.member.service.MemberService;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CourseService courseService;
    private final MemberService memberService;

    public CartService(CartRepository cartRepository,
                       CourseService courseService,
                       MemberService memberService) {
        this.cartRepository = cartRepository;
        this.courseService = courseService;
        this.memberService = memberService;
    }

    @Transactional(readOnly = true)
    public List<CartDTO> findCartsById(Long memberId) {
        if (memberId == null) {
            throw new IllegalArgumentException("memberId가 필요합니다.");
        }

        MemberDTO member = getMemberById(memberId);

        return cartRepository.findAllByMemberId(memberId)
                .stream()
                .map(cart -> toCartResponse(member, cart))
                .toList();
    }

    @Transactional
    public CartDTO addCart(Long memberId, Long courseId) {
        if (memberId == null || courseId == null) {
            throw new IllegalArgumentException("memberId와 courseId가 필요합니다.");
        }

        if (cartRepository.existsByMemberIdAndCourseId(memberId, courseId)) {
            throw new IllegalArgumentException("이미 장바구니에 담긴 강의입니다. courseId=" + courseId);
        }

        Cart cart = new Cart(courseId, memberId);
        cartRepository.save(cart);

        MemberDTO member = getMemberById(memberId);

        return toCartResponse(member, cart);
    }

    @Transactional
    public void deleteCart(Long memberId, Long courseId) {
        if (memberId == null || courseId == null) {
            throw new IllegalArgumentException("memberId와 courseId가 필요합니다.");
        }

        cartRepository.deleteByMemberIdAndCourseId(memberId, courseId);
    }

    @Transactional
    public void deleteAllCart(Long memberId) {
        if (memberId == null) {
            throw new IllegalArgumentException("memberId가 필요합니다.");
        }

        cartRepository.deleteByMemberId(memberId);
    }

    private MemberDTO getMemberById(Long memberId) {
        return memberService.getMemberById(memberId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "멤버를 찾을 수 없습니다. memberId=" + memberId));
    }

    private CourseSummaryDTO getCourseSummaryById(Long courseId) {
        return courseService.getCourseSummaryById(courseId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "강좌를 찾을 수 없습니다. courseId=" + courseId));
    }

    private CartDTO toCartResponse(MemberDTO member, Cart cart) {
        CourseSummaryDTO course = getCourseSummaryById(cart.getCourseId());

        return new CartDTO(course.courseId(),
                member,
                course.title(),
                course.price(),
                course.description(),
                course.thumbnailUri());
    }
}
