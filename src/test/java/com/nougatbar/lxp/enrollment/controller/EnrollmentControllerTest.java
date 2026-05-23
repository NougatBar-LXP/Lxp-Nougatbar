package com.nougatbar.lxp.enrollment.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.nougatbar.lxp.enrollment.service.EnrollmentService;
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
    @DisplayName("음수 memberId가 들어오면 에러를 발생하여 400 에러로 처리")
    void EnrollmentFail() throws Exception {

        // given
        Long incomingMemberId = -1L;
        // when
        var result = mockMvc.perform(get("/enrollments/members/{memberId}", incomingMemberId));
        // then
        result.andExpect(status().isBadRequest());
    }
}