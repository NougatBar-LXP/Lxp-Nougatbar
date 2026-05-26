package com.nougatbar.lxp.member.service.impl;

import com.nougatbar.lxp.member.dto.LoginMemberDTO;
import com.nougatbar.lxp.member.dto.SignupRequestDTO;
import com.nougatbar.lxp.member.dto.response.MemberDTO;
import com.nougatbar.lxp.member.entity.Member;
import com.nougatbar.lxp.member.exceptional.DuplicateFieldException;
import com.nougatbar.lxp.member.repository.MemberRepository;
import com.nougatbar.lxp.member.service.MemberService;
import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberServiceImpl(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<MemberDTO> getMemberById(Long memberId) {
        return memberRepository.findById(memberId).map(MemberDTO::from);
    }

    @Override
    public Optional<MemberDTO> getMemberByEmail(String email) {
        return memberRepository.findByEmail(email).map(MemberDTO::from);
    }

    // 중복체크 전담 메서드
    @Override
    public void validateDuplicate(SignupRequestDTO signupRequestDTO) {
        if (memberRepository.existsByEmail(signupRequestDTO.getEmail())) {
            throw new DuplicateFieldException("이미 사용중인 아이디입니다.");
        }
        if (memberRepository.existsByNickname(signupRequestDTO.getNickname())) {
            throw new DuplicateFieldException("이미 사용중인 닉네임입니다.");
        }
    }

    @Override
    public Long register(SignupRequestDTO signupRequestDTO) {
        validateDuplicate(signupRequestDTO); // 중복체크 따로 분리

        Member member = new Member(signupRequestDTO.getEmail(),
                signupRequestDTO.getNickname(),
                passwordEncoder.encode(signupRequestDTO.getPassword()),
                signupRequestDTO.getName());
        Member saved = memberRepository.save(member);
        return saved.getId();
    }

    // 로그인
    @Transactional(readOnly = true)
    @Override
    public LoginMemberDTO findByEmail(String email) {
        return memberRepository.findByEmail(email)
                .map(member -> new LoginMemberDTO(member.getId(),
                        member.getEmail(),
                        member.getPassword(),
                        member.getNickname(),
                        member.getRole()))
                .orElse(null);
    }
}
