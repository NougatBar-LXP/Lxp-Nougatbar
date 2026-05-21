package com.nougatbar.lxp.member.dto.response;

import com.nougatbar.lxp.member.dto.MemberRoleDTO;
import com.nougatbar.lxp.member.dto.MemberStatusDTO;
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
}
