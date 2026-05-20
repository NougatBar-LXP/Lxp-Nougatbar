package com.nougatbar.lxp.community.dto.response;

import com.nougatbar.lxp.community.entity.Community;
import com.nougatbar.lxp.community.entity.CommunityType;
import java.time.LocalDateTime;

public record CommunityResponse(
        Long communityId,
        Long courseId,
        Long memberId,
        CommunityType type,
        String title,
        String content,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public static CommunityResponse from(Community community) {
        return new CommunityResponse(
                community.getCommunityId(),
                community.getCourseId(),
                community.getMemberId(),
                community.getType(),
                community.getTitle(),
                community.getContent(),
                community.getCreatedAt(),
                community.getUpdatedAt()
        );
    }
}
