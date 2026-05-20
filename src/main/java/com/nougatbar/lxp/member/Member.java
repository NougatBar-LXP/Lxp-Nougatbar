package com.nougatbar.lxp.member;

import jakarta.persistence.*;

@Entity
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    public Long getMemberId() {
        return memberId;
    }
}