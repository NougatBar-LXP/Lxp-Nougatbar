package com.nougatbar.lxp.member.service;

import com.nougatbar.lxp.member.dto.response.MemberDTO;

public interface MemberService {
    MemberDTO getMemberById(Long memberId);
}
