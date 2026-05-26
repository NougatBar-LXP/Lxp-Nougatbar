package com.nougatbar.lxp.member.dto;

import com.nougatbar.lxp.member.entity.MemberRole;

public class SignupRequestDTO {

    private Long id;
    private String email;
    private String password;
    private String nickname;
    private String name;


    public SignupRequestDTO() {
    }

    public SignupRequestDTO(Long id, String email, String password, String nickname, String name) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.name = name;
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

    public String getName() {
        return name;
    }
}
