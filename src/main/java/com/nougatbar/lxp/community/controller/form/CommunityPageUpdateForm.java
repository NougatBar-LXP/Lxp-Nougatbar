package com.nougatbar.lxp.community.controller.form;

import com.nougatbar.lxp.community.dto.request.CommunityUpdateRequest;

public record CommunityPageUpdateForm(
        String title,
        String content
) {

    public CommunityUpdateRequest toRequest() {
        return new CommunityUpdateRequest(title, content);
    }
}
