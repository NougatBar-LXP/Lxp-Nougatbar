package com.nougatbar.lxp.member.service;

import com.nougatbar.lxp.member.dto.LoginMemberDTO;
import com.nougatbar.lxp.member.dto.SignupRequestDTO;
import com.nougatbar.lxp.member.dto.response.MemberDTO;
import com.nougatbar.lxp.member.entity.Member;
import com.nougatbar.lxp.member.exceptional.DuplicateFieldException;
import com.nougatbar.lxp.member.repository.MemberRepository;
import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public interface MemberService {
    Optional<MemberDTO> getMemberById(Long memberId);

    Optional<MemberDTO> getMemberByEmail(String email);

    // 중복체크 전담 메서드
    void validateDuplicate(SignupRequestDTO signupRequestDTO);

    Long register(SignupRequestDTO signupRequestDTO);

    // 로그인
    LoginMemberDTO findByEmail(String email);
}
