package com.nougatbar.lxp.member.dto;

import com.nougatbar.lxp.member.entity.MemberRole;

public enum MemberRoleDTO {
    MEMBER,
    INSTRUCTOR,
    ADMIN;

    public static MemberRoleDTO from(MemberRole role) {
        return switch (role) {
            case MEMBER -> MemberRoleDTO.MEMBER;
            case INSTRUCTOR -> MemberRoleDTO.INSTRUCTOR;
            case ADMIN -> MemberRoleDTO.ADMIN;
        };
    }
}
