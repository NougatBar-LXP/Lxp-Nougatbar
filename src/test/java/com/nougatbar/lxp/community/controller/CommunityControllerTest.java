package com.nougatbar.lxp.community.controller;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nougatbar.lxp.community.application.CommunityAppService;
import com.nougatbar.lxp.community.dto.request.CommunityCreateRequest;
import com.nougatbar.lxp.community.dto.request.CommunityUpdateRequest;
import com.nougatbar.lxp.community.dto.response.CommunityResponse;
import com.nougatbar.lxp.community.entity.CommunityType;
import com.nougatbar.lxp.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@WithMockUser
class CommunityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CommunityAppService communityAppService;

    @MockitoBean
    private MemberService memberService;

    @Test
    void createCommunity_success() throws Exception {
        // given
        CommunityCreateRequest request = createRequest(1L, 1L, CommunityType.COURSE, "community title", "community content");

        // when
        ResultActions result = mockMvc.perform(post("/community")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        // then
        result.andExpect(status().isCreated())
                .andExpect(header().string("Location", containsString("/community/")))
                .andExpect(jsonPath("$.communityId").exists())
                .andExpect(jsonPath("$.courseId").value(1))
                .andExpect(jsonPath("$.memberId").value(1))
                .andExpect(jsonPath("$.type").value("COURSE"))
                .andExpect(jsonPath("$.title").value("community title"))
                .andExpect(jsonPath("$.content").value("community content"));
    }

    @Test
    void findCommunities_success() throws Exception {
        // given
        communityAppService.createCommunity(createRequest(1L, 1L, CommunityType.COURSE, "first title", "first content"));
        communityAppService.createCommunity(createRequest(2L, 1L, CommunityType.MISSION, "second title", "second content"));

        // when
        ResultActions result = mockMvc.perform(get("/community"));

        // then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title").value("second title"))
                .andExpect(jsonPath("$[1].title").value("first title"));
    }

    @Test
    void findCommunitiesByCourseId_success() throws Exception {
        // given
        communityAppService.createCommunity(createRequest(1L, 1L, CommunityType.COURSE, "course 1 title", "content"));
        communityAppService.createCommunity(createRequest(2L, 1L, CommunityType.COURSE, "course 2 title", "content"));

        // when
        ResultActions result = mockMvc.perform(get("/community")
                .param("courseId", "1"));

        // then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].courseId").value(1))
                .andExpect(jsonPath("$[0].title").value("course 1 title"));
    }

    @Test
    void findCommunity_success() throws Exception {
        // given
        CommunityResponse created = communityAppService.createCommunity(
                createRequest(1L, 1L, CommunityType.LECTURE, "lecture question", "question content")
        );

        // when
        ResultActions result = mockMvc.perform(get("/community/{communityId}", created.communityId()));

        // then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.communityId").value(created.communityId()))
                .andExpect(jsonPath("$.type").value("LECTURE"))
                .andExpect(jsonPath("$.title").value("lecture question"));
    }

    @Test
    void updateCommunity_success() throws Exception {
        // given
        CommunityResponse created = communityAppService.createCommunity(
                createRequest(1L, 1L, CommunityType.COURSE, "before title", "before content")
        );
        CommunityUpdateRequest request = new CommunityUpdateRequest("after title", "after content");

        // when
        ResultActions result = mockMvc.perform(put("/community/{communityId}", created.communityId())
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        // then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.communityId").value(created.communityId()))
                .andExpect(jsonPath("$.title").value("after title"))
                .andExpect(jsonPath("$.content").value("after content"))
                .andExpect(jsonPath("$.updatedAt").exists());
    }

    @Test
    void deleteCommunity_success() throws Exception {
        // given
        CommunityResponse created = communityAppService.createCommunity(
                createRequest(1L, 1L, CommunityType.COURSE, "delete title", "delete content")
        );

        // when
        ResultActions deleteResult = mockMvc.perform(delete("/community/{communityId}", created.communityId())
                .with(csrf()));
        ResultActions findResult = mockMvc.perform(get("/community/{communityId}", created.communityId()));

        // then
        deleteResult.andExpect(status().isNoContent());
        findResult.andExpect(status().isNotFound());
    }

    @Test
    void createCommunity_fail_whenRequiredValueIsMissing() throws Exception {
        // given
        CommunityCreateRequest request = createRequest(1L, 1L, CommunityType.COURSE, "", "community content");

        // when
        ResultActions result = mockMvc.perform(post("/community")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        // then
        result.andExpect(status().isBadRequest());
    }

    @Test
    void findCommunity_fail_whenCommunityDoesNotExist() throws Exception {
        // given
        Long communityId = 999L;

        // when
        ResultActions result = mockMvc.perform(get("/community/{communityId}", communityId));

        // then
        result.andExpect(status().isNotFound());
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
