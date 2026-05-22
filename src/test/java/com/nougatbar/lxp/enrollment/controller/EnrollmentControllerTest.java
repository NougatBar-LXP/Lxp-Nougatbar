package com.nougatbar.lxp.enrollment.controller;

import static org.mockito.BDDMockito.given;
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
    @DisplayName("양수가 memberId에 들어왔을때 200 OK 반환")
    void EnrollmentSuccess() throws Exception {

        // given
        Long memberId = 1L;
        given(enrollmentService.findById(memberId)).willReturn(Collections.emptyList());
        // when
        var result = mockMvc.perform(get("/enrollments/members/{memberId}", memberId));
        // then
        result.andExpect(status().isOk());
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
