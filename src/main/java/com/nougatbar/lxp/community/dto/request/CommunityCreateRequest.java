package com.nougatbar.lxp.community.dto.request;

import com.nougatbar.lxp.community.entity.CommunityType;

public record CommunityCreateRequest(
        Long courseId,
        Long memberId,
        CommunityType type,
        String title,
        String content
) {
}
