package com.nougatbar.lxp.member.service;

import com.nougatbar.lxp.member.dto.LoginMemberDTO;
import com.nougatbar.lxp.member.security.MemberDetails;
import java.util.Collections;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// DB에서 유저 조회
@Service
public class MemberDetailsServiceImpl implements UserDetailsService {

    private final MemberService memberService;

    public MemberDetailsServiceImpl(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginMemberDTO loginMember = memberService.findByEmail(username);

        // 존재하지 않는 Id는 표준 예외로 던진다.
        if (loginMember == null) {
            throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다.");
        }
        return new MemberDetails(loginMember);
    }


}
