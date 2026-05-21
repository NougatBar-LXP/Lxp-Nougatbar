package com.nougatbar.lxp.community.controller.form;

import com.nougatbar.lxp.community.dto.request.CommunityCreateRequest;
import com.nougatbar.lxp.community.entity.CommunityType;

public record CommunityPageCreateForm(
        Long courseId,
        Long memberId,
        CommunityType type,
        String title,
        String content
) {

    public CommunityCreateRequest toRequest() {
        return new CommunityCreateRequest(courseId, memberId, type, title, content);
    }
}
