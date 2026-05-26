package com.nougatbar.lxp.member.dto;

import com.nougatbar.lxp.member.entity.MemberStatus;

public enum MemberStatusDTO {
    ACTIVE,
    WITHDRAWN;
    
    public static MemberStatusDTO from(MemberStatus status) {
        return switch (status) {
            case ACTIVE -> MemberStatusDTO.ACTIVE;
            case WITHDRAWN -> MemberStatusDTO.WITHDRAWN;
        };
    }
}
