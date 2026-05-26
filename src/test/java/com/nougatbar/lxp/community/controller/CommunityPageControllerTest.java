package com.nougatbar.lxp.community.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nougatbar.lxp.community.application.CommunityAppService;
import com.nougatbar.lxp.community.dto.request.CommunityCreateRequest;
import com.nougatbar.lxp.community.dto.response.CommunityResponse;
import com.nougatbar.lxp.community.entity.CommunityType;
import com.nougatbar.lxp.member.dto.MemberRoleDTO;
import com.nougatbar.lxp.member.dto.MemberStatusDTO;
import com.nougatbar.lxp.member.dto.response.MemberDTO;
import com.nougatbar.lxp.member.service.MemberService;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
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
    private CommunityAppService communityAppService;

    @MockitoBean
    private MemberService memberService;

    @Test
    void courseCommunityPage_success() throws Exception {
        when(memberService.getMemberById(anyLong())).thenReturn(Optional.of(new MemberDTO(
                1L,
                "instructor@lxp.test",
                "Instructor",
                "instructor",
                MemberStatusDTO.ACTIVE,
                MemberRoleDTO.INSTRUCTOR,
                null,
                null,
                null
        )));

        mockMvc.perform(get("/courses/1/community"))
                .andExpect(status().isOk())
                .andExpect(view().name("community/community"));
    }

    @Test
    void courseCommunityDetailPage_success() throws Exception {
        when(memberService.getMemberById(anyLong())).thenReturn(Optional.of(new MemberDTO(
                1L,
                "instructor@lxp.test",
                "Instructor",
                "instructor",
                MemberStatusDTO.ACTIVE,
                MemberRoleDTO.INSTRUCTOR,
                null,
                null,
                null
        )));
        CommunityResponse created = communityAppService.createCommunity(
                new CommunityCreateRequest(1L, 1L, CommunityType.COURSE, "title", "content")
        );

        mockMvc.perform(get("/courses/1/community/{communityId}", created.communityId()))
                .andExpect(status().isOk())
                .andExpect(view().name("community/community-detail"));
    }

    @Test
    void communityPage_success() throws Exception {
        mockMvc.perform(get("/community-ui"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/courses/1/community?type=ALL&q="));
    }

    @Test
    void newCommunityPage_success() throws Exception {
        mockMvc.perform(get("/community-ui/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/courses/1/community?communityMode=new&type=ALL&q="));
    }

    @Test
    void editCommunityPage_success() throws Exception {
        CommunityResponse created = communityAppService.createCommunity(
                new CommunityCreateRequest(1L, 1L, CommunityType.COURSE, "title", "content")
        );

        mockMvc.perform(get("/community-ui/{communityId}/edit", created.communityId()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/courses/1/community?communityId=" + created.communityId()
                        + "&communityMode=edit&type=ALL&q="));
    }

    @Test
    void detailCommunityPage_redirect_whenCourseIdDoesNotMatch() throws Exception {
        // given
        CommunityResponse created = communityAppService.createCommunity(
                new CommunityCreateRequest(2L, 1L, CommunityType.COURSE, "title", "content")
        );

        // when & then
        mockMvc.perform(get("/community-ui/{communityId}", created.communityId())
                        .param("cid", "1")
                        .param("type", "COURSE")
                .param("q", "title"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/courses/2/community/" + created.communityId()
                        + "?type=COURSE&q=title"));
    }

    @Test
    void editCommunityPage_redirect_whenCourseIdDoesNotMatch() throws Exception {
        // given
        CommunityResponse created = communityAppService.createCommunity(
                new CommunityCreateRequest(2L, 1L, CommunityType.COURSE, "title", "content")
        );

        // when & then
        mockMvc.perform(get("/community-ui/{communityId}/edit", created.communityId())
                        .param("cid", "1")
                        .param("type", "COURSE")
                        .param("q", "title"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/courses/2/community?communityId=" + created.communityId()
                        + "&communityMode=edit&type=COURSE&q=title"));
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
                .andExpect(redirectedUrl("/courses/1/community?type=ALL&q="));
    }

    @Test
    void updateCommunityByForm_redirectToActualCourseId() throws Exception {
        // given
        CommunityResponse created = communityAppService.createCommunity(
                new CommunityCreateRequest(2L, 1L, CommunityType.COURSE, "title", "content")
        );

        // when & then
        mockMvc.perform(post("/community-ui/{communityId}", created.communityId())
                        .with(csrf())
                        .param("cid", "1")
                        .param("title", "updated title")
                .param("content", "updated content"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/courses/2/community/" + created.communityId()));
    }

    @Test
    void deleteCommunityByForm_redirectToActualCourseId() throws Exception {
        // given
        CommunityResponse created = communityAppService.createCommunity(
                new CommunityCreateRequest(2L, 1L, CommunityType.COURSE, "title", "content")
        );

        // when & then
        mockMvc.perform(post("/community-ui/{communityId}/delete", created.communityId())
                        .with(csrf())
                        .param("cid", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/courses/2/community?type=ALL&q="));
    }
}
