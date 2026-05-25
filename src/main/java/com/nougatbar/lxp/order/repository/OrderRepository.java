package com.nougatbar.lxp.order.repository;

import com.nougatbar.lxp.order.entity.Order;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByMemberIdOrderByCreatedAtDesc(Long memberId);
    
    Optional<Order> findByMemberIdAndOrderId(Long memberId, Long orderId);

}
