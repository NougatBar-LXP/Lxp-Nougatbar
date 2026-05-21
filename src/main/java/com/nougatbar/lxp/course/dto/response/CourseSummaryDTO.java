package com.nougatbar.lxp.course.dto.response;

import com.nougatbar.lxp.course.dto.CourseLevelDTO;
import com.nougatbar.lxp.course.dto.CourseStatusDTO;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * 강좌 목록 조회 시 사용되는 응답 DTO. 하위 섹션에 대한 정보들은 포함하지 않습니다.
 *
 * @param courseId     강좌 ID
 * @param memberId     강좌를 생성한 강사 (Member) ID
 * @param title        강좌 제목
 * @param description  강좌 설명
 * @param price        강좌 가격 (0 이상)
 * @param level        강좌 난이도 (EASY, MEDIUM, HARD)
 * @param status       강좌 상태 (DRAFT, PUBLISHED, ARCHIVED)
 * @param thumbnailUrl 강좌 썸네일 이미지 URL
 * @param createdAt    강좌 생성 일시
 * @param deletedAt    강좌 삭제 일시 (삭제되지 않은 경우 null)
 * @param approvedAt   강좌 승인 일시 (승인되지 않은 경우 null)
 * @param tags         강좌에 등록된 태그 목록
 */
public record CourseSummaryDTO(Long courseId,
                               Long memberId,
                               String title,
                               String description,
                               int price,
                               CourseLevelDTO level,
                               CourseStatusDTO status,
                               URL thumbnailUrl,
                               LocalDateTime createdAt,
                               LocalDateTime deletedAt,
                               LocalDateTime approvedAt,
                               Set<String> tags) {
}
