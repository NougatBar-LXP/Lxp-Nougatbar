package com.nougatbar.lxp.enrollment.controller;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.nougatbar.lxp.enrollment.service.EnrollmentService;
import java.util.Collections;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(EnrollmentController.class)
public class EnrollmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EnrollmentService enrollmentService;

    @Test
    @WithMockUser
    @DisplayName("음수 memberId가 들어오면 0L로 보정(Math.max)하여 200 OK를 반환한다")
    void EnrollmentSuccess() throws Exception {

        // given
        Long incomingMemberId = -1L;
        Long expectedNormalizedId = 0L;

        given(enrollmentService.findById(expectedNormalizedId)).willReturn(Collections.emptyList());
        // when
        var result = mockMvc.perform(get("/enrollments/members/{memberId}", incomingMemberId));
        // then
        result.andExpect(status().isOk());

        verify(enrollmentService).findById(expectedNormalizedId);
    }

    @Test
    @WithMockUser
    @DisplayName("음수가 memberId에 값을 0으로 전환 시켜서 200 OK 반환")
    void EnrollmentFail() throws Exception {
        // given
        Long memberId = -1L;
        given(enrollmentService.findById(memberId)).willReturn(Collections.emptyList());
        // when
        var result = mockMvc.perform(get("/enrollments/members/{memberId}", memberId));
        // then
        result.andExpect(status().isOk());
    }
    }