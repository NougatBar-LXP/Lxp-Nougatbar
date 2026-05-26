package com.nougatbar.lxp.member.dto.response;

import com.nougatbar.lxp.member.dto.MemberRoleDTO;
import com.nougatbar.lxp.member.dto.MemberStatusDTO;
import com.nougatbar.lxp.member.entity.Member;
import java.time.LocalDateTime;

public record MemberDTO(Long memberId,
                        String email,
                        String name,
                        String nickname,
                        MemberStatusDTO status,
                        MemberRoleDTO role,
                        LocalDateTime createdAt,
                        LocalDateTime updatedAt,
                        LocalDateTime deletedAt) {
    public static MemberDTO from(Member member) {
        return new MemberDTO(member.getId(),
                member.getEmail(),
                member.getName(),
                member.getNickname(),
                MemberStatusDTO.from(member.getStatus()),
                MemberRoleDTO.from(member.getRole()),
                member.getDeletedAt(),
                member.getCreatedAt(),
                member.getDeletedAt());
    }
}
