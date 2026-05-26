package com.nougatbar.lxp.member.repository;

import com.nougatbar.lxp.member.entity.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    // 로그인 UserId로 회원 조회
    Optional<Member> findById(Long id);

    Optional<Member> findByEmail(String email);

    // 회원 가입 : 중복 체크
    boolean existsById(Long id);

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);
}
