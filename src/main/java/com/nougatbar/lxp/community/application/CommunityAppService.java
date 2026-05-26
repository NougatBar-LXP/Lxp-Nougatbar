package com.nougatbar.lxp.community.application;

import com.nougatbar.lxp.community.dto.request.CommunityCreateRequest;
import com.nougatbar.lxp.community.dto.request.CommunityUpdateRequest;
import com.nougatbar.lxp.community.dto.response.CommunityResponse;
import com.nougatbar.lxp.community.service.CommunityService;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class CommunityAppService {

    private final CommunityService communityService;

    public CommunityAppService(CommunityService communityService) {
        this.communityService = communityService;
    }

    public List<CommunityResponse> findCommunities(Long courseId) {
        return communityService.findCommunities(courseId);
    }

    public CommunityResponse findCommunity(Long communityId) {
        return communityService.findCommunity(communityId);
    }

    @Transactional
    public CommunityResponse createCommunity(CommunityCreateRequest request) {
        return communityService.createCommunity(request);
    }

    @Transactional
    public CommunityResponse updateCommunity(Long communityId, CommunityUpdateRequest request) {
        return communityService.updateCommunity(communityId, request);
    }

    @Transactional
    public void deleteCommunity(Long communityId) {
        communityService.deleteCommunity(communityId);
    }
}
