package com.nougatbar.lxp.community.service;

import com.nougatbar.lxp.community.dto.request.CommunityCreateRequest;
import com.nougatbar.lxp.community.dto.request.CommunityUpdateRequest;
import com.nougatbar.lxp.community.dto.response.CommunityResponse;
import com.nougatbar.lxp.community.entity.Community;
import com.nougatbar.lxp.community.repository.CommunityRepository;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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
                request.title().trim(),
                request.content().trim()
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
        community.update(request.title().trim(), request.content().trim());
        return CommunityResponse.from(community);
    }

    @Transactional
    public void deleteCommunity(Long communityId) {
        Community community = getCommunity(communityId);
        communityRepository.delete(community);
    }

    private Community getCommunity(Long communityId) {
        return communityRepository.findById(communityId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Community not found."));
    }

    private void validateCreateRequest(CommunityCreateRequest request) {
        if (request == null
                || request.courseId() == null
                || request.memberId() == null
                || request.type() == null
                || isBlank(request.title())
                || isBlank(request.content())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "courseId, memberId, type, title, and content are required."
            );
        }
    }

    private void validateUpdateRequest(CommunityUpdateRequest request) {
        if (request == null || isBlank(request.title()) || isBlank(request.content())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "title and content are required.");
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}
