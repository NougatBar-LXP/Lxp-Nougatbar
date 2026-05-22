package com.nougatbar.lxp.cart.repository;

import com.nougatbar.lxp.cart.entity.Cart;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findAllByMemberId(Long memberId);

    boolean existsByMemberIdAndCourseId(Long memberId, Long courseId);

    void deleteByMemberIdAndCourseId(Long memberId, Long courseId);

    void deleteByMemberId(Long memberId);
}
