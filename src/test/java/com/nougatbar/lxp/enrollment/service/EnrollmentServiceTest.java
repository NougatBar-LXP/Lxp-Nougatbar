package com.nougatbar.lxp.enrollment.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.BDDMockito.given;

import com.nougatbar.lxp.enrollment.dto.response.EnrollmentDTO;
import com.nougatbar.lxp.enrollment.entity.Enrollment;
import com.nougatbar.lxp.enrollment.repository.EnrollmentRepository;
import java.util.List;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


class EnrollmentServiceTest {

    @Mock
    private EnrollmentRepository repository;

    @InjectMocks
    private EnrollmentService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("유저 id로 조회 시 수강 내역이 존재하면 DTO 리스트 반환")
    void findAllSuccess() {
        // given
        Long memberId = 1L;
        Enrollment mockEnrollment = new Enrollment(true, 100L, memberId);
        given(repository.findByMemberIdOrderByCreatedAtDesc(memberId)).willReturn(List.of(
                mockEnrollment));
        // when
        List<EnrollmentDTO> response = service.findById(memberId);
        // then
        assertFalse(response.isEmpty());
        assertEquals(1, response.size());
    }

    @Test
    @DisplayName("유저 id가 음수면 에러 발생")
    void findAllFail() {
        // given
        Long memberId = -1L;
        // when
        ThrowingCallable when = () -> service.findById(memberId);
        // then
        assertThatThrownBy(when).isInstanceOf(IllegalArgumentException.class);
    }

}
