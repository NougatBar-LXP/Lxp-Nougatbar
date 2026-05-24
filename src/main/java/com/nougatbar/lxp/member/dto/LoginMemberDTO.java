package com.nougatbar.lxp.member.dto;

import com.nougatbar.lxp.member.entity.MemberRole;

public class LoginMemberDTO {

    private final Long id;
    private final String email;
    private final String password;
    private final String nickname;
    private final MemberRole role;

    public LoginMemberDTO(Long id,
                          String email,
                          String password,
                          String nickname,
                          MemberRole role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

    public MemberRole getRole() {
        return role;
    }
}
