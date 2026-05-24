package com.nougatbar.lxp.member.entity;

public enum MemberRole {
    MEMBER("MEMBER"),
    INSTRUCTOR("INSTRUCTOR"),
    ADMIN("ADMIN");

    private final String value;

    MemberRole(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
