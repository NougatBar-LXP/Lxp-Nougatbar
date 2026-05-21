package com.nougatbar.lxp.community.service;

import com.nougatbar.lxp.common.exception.BaseException;
import com.nougatbar.lxp.common.exception.ErrorCode;
import com.nougatbar.lxp.community.dto.request.CommunityCreateRequest;
import com.nougatbar.lxp.community.dto.request.CommunityUpdateRequest;
import com.nougatbar.lxp.community.dto.response.CommunityResponse;
import com.nougatbar.lxp.community.entity.Community;
import com.nougatbar.lxp.community.repository.CommunityRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class CommunityService {

    private final CommunityRepository communityRepository;

    public CommunityService(CommunityRepository communityRepository) {
        this.communityRepository = communityRepository;
    }

    public List<CommunityResponse> findCommunities(Long courseId) {
        List<Community> communities = courseId == null
                ? communityRepository.findAllByOrderByCreatedAtDesc()
                : communityRepository.findAllByCourseIdOrderByCreatedAtDesc(courseId);

        return communities.stream()
                .map(CommunityResponse::from)
                .toList();
    }

    @Transactional
    public CommunityResponse createCommunity(CommunityCreateRequest request) {
        validateCreateRequest(request);

        Community community = Community.create(
                request.courseId(),
                request.memberId(),
                request.type(),
                request.title(),
                request.content()
        );

        return CommunityResponse.from(communityRepository.save(community));
    }

    public CommunityResponse findCommunity(Long communityId) {
        return CommunityResponse.from(getCommunity(communityId));
    }

    @Transactional
    public CommunityResponse updateCommunity(Long communityId, CommunityUpdateRequest request) {
        validateUpdateRequest(request);

        Community community = getCommunity(communityId);
        community.update(request.title(), request.content());
        return CommunityResponse.from(community);
    }

    @Transactional
    public void deleteCommunity(Long communityId) {
        Community community = getCommunity(communityId);
        communityRepository.delete(community);
    }

    private Community getCommunity(Long communityId) {
        return communityRepository.findById(communityId)
                .orElseThrow(() -> new BaseException(ErrorCode.COMMUNITY_NOT_FOUND));
    }

    private void validateCreateRequest(CommunityCreateRequest request) {
        if (request == null
                || request.courseId() == null
                || request.memberId() == null
                || request.type() == null
                || isBlank(request.title())
                || isBlank(request.content())) {
            throw new BaseException(ErrorCode.COMMUNITY_REQUIRED_VALUE_MISSING);
        }
    }

    private void validateUpdateRequest(CommunityUpdateRequest request) {
        if (request == null || isBlank(request.title()) || isBlank(request.content())) {
            throw new BaseException(ErrorCode.COMMUNITY_UPDATE_REQUIRED_VALUE_MISSING);
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}
