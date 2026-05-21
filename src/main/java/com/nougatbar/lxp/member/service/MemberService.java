package com.nougatbar.lxp.member.service;

import com.nougatbar.lxp.member.dto.response.MemberDTO;
import java.util.Optional;

public interface MemberService {
    Optional<MemberDTO> getMemberById(Long memberId);
}
