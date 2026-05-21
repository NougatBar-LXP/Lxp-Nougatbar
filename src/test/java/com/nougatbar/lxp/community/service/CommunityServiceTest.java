package com.nougatbar.lxp.community.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import com.nougatbar.lxp.common.exception.BaseException;
import com.nougatbar.lxp.common.exception.ErrorCode;
import com.nougatbar.lxp.community.dto.request.CommunityCreateRequest;
import com.nougatbar.lxp.community.dto.request.CommunityUpdateRequest;
import com.nougatbar.lxp.community.dto.response.CommunityResponse;
import com.nougatbar.lxp.community.entity.CommunityType;
import com.nougatbar.lxp.community.repository.CommunityRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class CommunityServiceTest {

    @Autowired
    private CommunityService communityService;

    @Autowired
    private CommunityRepository communityRepository;

    @Test
    void createCommunity_success() {
        // given
        CommunityCreateRequest request = createRequest(1L, 1L, CommunityType.COURSE, "community title", "community content");

        // when
        CommunityResponse response = communityService.createCommunity(request);

        // then
        assertThat(response.communityId()).isNotNull();
        assertThat(response.courseId()).isEqualTo(1L);
        assertThat(response.memberId()).isEqualTo(1L);
        assertThat(response.type()).isEqualTo(CommunityType.COURSE);
        assertThat(response.title()).isEqualTo("community title");
        assertThat(response.content()).isEqualTo("community content");
        assertThat(response.createdAt()).isNotNull();
    }

    @Test
    void findCommunities_success() {
        // given
        communityService.createCommunity(createRequest(1L, 1L, CommunityType.COURSE, "first title", "first content"));
        communityService.createCommunity(createRequest(2L, 1L, CommunityType.MISSION, "second title", "second content"));

        // when
        List<CommunityResponse> responses = communityService.findCommunities(null);

        // then
        assertThat(responses).hasSize(2);
        assertThat(responses)
                .extracting(CommunityResponse::title)
                .containsExactly("second title", "first title");
    }

    @Test
    void findCommunitiesByCourseId_success() {
        // given
        communityService.createCommunity(createRequest(1L, 1L, CommunityType.COURSE, "course 1 title", "content"));
        communityService.createCommunity(createRequest(2L, 1L, CommunityType.COURSE, "course 2 title", "content"));

        // when
        List<CommunityResponse> responses = communityService.findCommunities(1L);

        // then
        assertThat(responses).hasSize(1);
        assertThat(responses.get(0).courseId()).isEqualTo(1L);
        assertThat(responses.get(0).title()).isEqualTo("course 1 title");
    }

    @Test
    void findCommunity_success() {
        // given
        CommunityResponse created = communityService.createCommunity(
                createRequest(1L, 1L, CommunityType.LECTURE, "lecture question", "question content")
        );

        // when
        CommunityResponse response = communityService.findCommunity(created.communityId());

        // then
        assertThat(response.communityId()).isEqualTo(created.communityId());
        assertThat(response.type()).isEqualTo(CommunityType.LECTURE);
        assertThat(response.title()).isEqualTo("lecture question");
    }

    @Test
    void updateCommunity_success() {
        // given
        CommunityResponse created = communityService.createCommunity(
                createRequest(1L, 1L, CommunityType.COURSE, "before title", "before content")
        );

        // when
        CommunityResponse response = communityService.updateCommunity(
                created.communityId(),
                new CommunityUpdateRequest("after title", "after content")
        );

        // then
        assertThat(response.title()).isEqualTo("after title");
        assertThat(response.content()).isEqualTo("after content");
        assertThat(response.updatedAt()).isNotNull();
    }

    @Test
    void deleteCommunity_success() {
        // given
        CommunityResponse created = communityService.createCommunity(
                createRequest(1L, 1L, CommunityType.COURSE, "delete title", "delete content")
        );

        // when
        communityService.deleteCommunity(created.communityId());

        // then
        assertThat(communityRepository.existsById(created.communityId())).isFalse();
    }

    @Test
    void findCommunity_fail_whenCommunityDoesNotExist() {
        // given
        Long communityId = 999L;

        // when
        Throwable thrown = catchThrowable(() -> communityService.findCommunity(communityId));

        // then
        assertThat(thrown)
                .isInstanceOf(BaseException.class)
                .extracting("errorCode")
                .isEqualTo(ErrorCode.COMMUNITY_NOT_FOUND);
    }

    @Test
    void createCommunity_fail_whenRequiredValueIsMissing() {
        // given
        CommunityCreateRequest request = createRequest(1L, 1L, CommunityType.COURSE, " ", "community content");

        // when
        Throwable thrown = catchThrowable(() -> communityService.createCommunity(request));

        // then
        assertThat(thrown)
                .isInstanceOf(BaseException.class)
                .extracting("errorCode")
                .isEqualTo(ErrorCode.COMMUNITY_REQUIRED_VALUE_MISSING);
    }

    @Test
    void updateCommunity_fail_whenRequiredValueIsMissing() {
        // given
        CommunityResponse created = communityService.createCommunity(
                createRequest(1L, 1L, CommunityType.COURSE, "before title", "before content")
        );
        CommunityUpdateRequest request = new CommunityUpdateRequest("", "after content");

        // when
        Throwable thrown = catchThrowable(() -> communityService.updateCommunity(created.communityId(), request));

        // then
        assertThat(thrown)
                .isInstanceOf(BaseException.class)
                .extracting("errorCode")
                .isEqualTo(ErrorCode.COMMUNITY_UPDATE_REQUIRED_VALUE_MISSING);
    }

    private CommunityCreateRequest createRequest(
            Long courseId,
            Long memberId,
            CommunityType type,
            String title,
            String content
    ) {
        return new CommunityCreateRequest(courseId, memberId, type, title, content);
    }
}
