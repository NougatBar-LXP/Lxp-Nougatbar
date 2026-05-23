package com.nougatbar.lxp.member.dto;

public class SignupRequestDTO {

    private Long id;
    private String email;
    private String password;
    private String nickname;

    public SignupRequestDTO() {
    }

    public SignupRequestDTO(Long id, String email, String password, String nickname) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
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
}
