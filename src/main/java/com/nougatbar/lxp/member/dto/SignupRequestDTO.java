package com.nougatbar.lxp.member.dto;

import com.nougatbar.lxp.member.entity.MemberRole;

public class SignupRequestDTO {

    private Long id;
    private String email;
    private String password;
    private String nickname;
    private MemberRole role;

    public SignupRequestDTO() {
    }

    public SignupRequestDTO(String email, String password, String nickname, MemberRole role) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
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
