package com.nougatbar.lxp.member.service.impl;

import com.nougatbar.lxp.member.dto.MemberRoleDTO;
import com.nougatbar.lxp.member.dto.MemberStatusDTO;
import com.nougatbar.lxp.member.dto.response.MemberDTO;
import com.nougatbar.lxp.member.service.MemberService;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {
    private final MemberDTO dummyMember = new MemberDTO(1L,
            "hong@lxp.com",
            "홍길동",
            "Hong",
            MemberStatusDTO.ACTIVE,
            MemberRoleDTO.MEMBER,
            LocalDateTime.now(),
            null,
            null);

    @Override
    public Optional<MemberDTO> getMemberById(Long memberId) {
        return Optional.of(dummyMember);
    }
}
