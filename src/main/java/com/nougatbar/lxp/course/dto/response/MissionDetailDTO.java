package com.nougatbar.lxp.course.dto.response;

import java.time.LocalDateTime;

/**
 * 미션 정보를 담는 DTO
 *
 * @param missionId 미션 ID
 * @param sectionId 미션이 속한 섹션 ID
 * @param title     미션 제목
 * @param contents  미션 내용
 * @param sequence  미션 순서. 같은 섹션 내에서의 순서로, 0-based index로 관리됩니다.
 * @param createdAt 미션 생성 시간
 * @param updatedAt 미션 수정 시간 (수정한 적이 없다면 NULL)
 */
public record MissionDetailDTO(Long missionId,
                               Long sectionId,
                               String title,
                               String contents,
                               int sequence,
                               LocalDateTime createdAt,
                               LocalDateTime updatedAt) {
}
