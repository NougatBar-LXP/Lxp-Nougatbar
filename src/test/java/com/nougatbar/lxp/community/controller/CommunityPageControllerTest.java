package com.nougatbar.lxp.community.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
}
