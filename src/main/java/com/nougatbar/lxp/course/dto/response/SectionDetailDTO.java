package com.nougatbar.lxp.course.dto.response;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 강의 섹션 정보를 담는 DTO
 *
 * @param sectionId 섹션 ID
 * @param courseId  섹션이 속한 강좌 ID
 * @param title     섹션 제목
 * @param sequence  섹션 순서
 * @param createdAt 섹션 생성 시간
 * @param updatedAt 섹션 수정 시간
 * @param lectures  섹션에 포함된 강의 목록
 * @param missions  섹션에 포함된 미션 목록
 */
public record SectionDetailDTO(Long sectionId,
                               Long courseId,
                               String title,
                               int sequence,
                               LocalDateTime createdAt,
                               LocalDateTime updatedAt,
                               List<LectureDetailDTO> lectures,
                               List<MissionDetailDTO> missions) {
}
