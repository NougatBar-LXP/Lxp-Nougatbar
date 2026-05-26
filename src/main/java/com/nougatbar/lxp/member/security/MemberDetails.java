package com.nougatbar.lxp.member.security;

import com.nougatbar.lxp.member.dto.LoginMemberDTO;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MemberDetails implements UserDetails {

    private final LoginMemberDTO loginMemberDTO;

    public MemberDetails(LoginMemberDTO loginMemberDTO) {
        this.loginMemberDTO = loginMemberDTO;
    }

    // 사용자 정보를 받아올 곳
    // 로그인 폼에서 입력하는 값과 같아야 하므로 화면 이름이 아닌 로그인 아이디를 반환한다.

    // 사용자의 권한 정보를 반환하는 메서드
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + loginMemberDTO.getRole().name()));
    }

    // 사용자의 비밀번호를 반환하는 메서드
    @Override
    public String getPassword() {
        return loginMemberDTO.getPassword();
    }

    // 사용자의 ID를 반환하는 메서드
    @Override
    public String getUsername() {
        return loginMemberDTO.getEmail();
    }

    // 아래 네 메서드는 계정 상태를 표현한다.
    // 현 단계에서는 구현 계획이 없으므로 모두 활성(true)을 반환하도록 설정했다.

    // 계정 말료 여부를 표현하는 메서드
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정이 잠겨 있는지 확인하는 메서드
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 탈퇴 계정을 확인하는 메서드
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정 비활성화 여부를 표현하는 메서드
    @Override
    public boolean isEnabled() {
        return true;
    }
}
