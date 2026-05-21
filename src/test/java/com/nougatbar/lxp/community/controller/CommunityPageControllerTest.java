package com.nougatbar.lxp.community.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nougatbar.lxp.community.dto.request.CommunityCreateRequest;
import com.nougatbar.lxp.community.dto.response.CommunityResponse;
import com.nougatbar.lxp.community.entity.CommunityType;
import com.nougatbar.lxp.community.service.CommunityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@WithMockUser
class CommunityPageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CommunityService communityService;

    @Test
    void communityPage_success() throws Exception {
        mockMvc.perform(get("/community-ui"))
                .andExpect(status().isOk())
                .andExpect(view().name("community/index"));
    }

    @Test
    void newCommunityPage_success() throws Exception {
        mockMvc.perform(get("/community-ui/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("community/index"));
    }

    @Test
    void editCommunityPage_success() throws Exception {
        CommunityResponse created = communityService.createCommunity(
                new CommunityCreateRequest(1L, 1L, CommunityType.COURSE, "title", "content")
        );

        mockMvc.perform(get("/community-ui/{communityId}/edit", created.communityId()))
                .andExpect(status().isOk())
                .andExpect(view().name("community/index"));
    }

    @Test
    void detailCommunityPage_redirect_whenCourseIdDoesNotMatch() throws Exception {
        // given
        CommunityResponse created = communityService.createCommunity(
                new CommunityCreateRequest(2L, 1L, CommunityType.COURSE, "title", "content")
        );

        // when & then
        mockMvc.perform(get("/community-ui/{communityId}", created.communityId())
                        .param("cid", "1")
                        .param("type", "COURSE")
                        .param("q", "title"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/community-ui/" + created.communityId() + "?cid=2&type=COURSE&q=title"));
    }

    @Test
    void editCommunityPage_redirect_whenCourseIdDoesNotMatch() throws Exception {
        // given
        CommunityResponse created = communityService.createCommunity(
                new CommunityCreateRequest(2L, 1L, CommunityType.COURSE, "title", "content")
        );

        // when & then
        mockMvc.perform(get("/community-ui/{communityId}/edit", created.communityId())
                        .param("cid", "1")
                        .param("type", "COURSE")
                        .param("q", "title"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/community-ui/" + created.communityId() + "/edit?cid=2&type=COURSE&q=title"));
    }

    @Test
    void createCommunityByForm_success() throws Exception {
        mockMvc.perform(post("/community-ui")
                        .with(csrf())
                        .param("courseId", "1")
                        .param("memberId", "1")
                        .param("type", CommunityType.COURSE.name())
                        .param("title", "form title")
                        .param("content", "form content"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("/community-ui/*"));
    }

    @Test
    void updateCommunityByForm_redirectToActualCourseId() throws Exception {
        // given
        CommunityResponse created = communityService.createCommunity(
                new CommunityCreateRequest(2L, 1L, CommunityType.COURSE, "title", "content")
        );

        // when & then
        mockMvc.perform(post("/community-ui/{communityId}", created.communityId())
                        .with(csrf())
                        .param("cid", "1")
                        .param("title", "updated title")
                        .param("content", "updated content"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/community-ui/" + created.communityId() + "?cid=2"));
    }

    @Test
    void deleteCommunityByForm_redirectToActualCourseId() throws Exception {
        // given
        CommunityResponse created = communityService.createCommunity(
                new CommunityCreateRequest(2L, 1L, CommunityType.COURSE, "title", "content")
        );

        // when & then
        mockMvc.perform(post("/community-ui/{communityId}/delete", created.communityId())
                        .with(csrf())
                        .param("cid", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/community-ui?cid=2"));
    }
}
