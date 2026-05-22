package com.nougatbar.lxp.course.dto.response;

import com.nougatbar.lxp.course.dto.CourseLevelDTO;
import com.nougatbar.lxp.course.dto.CourseStatusDTO;
import com.nougatbar.lxp.course.entity.Course;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 강좌 상세 조회에 대한 응답 DTO. 하위 섹션에 대한 정보들을 포함합니다.
 *
 * @param courseId     강좌 ID
 * @param memberId     강좌를 생성한 강사 (Member) ID
 * @param title        강좌 제목
 * @param description  강좌 설명
 * @param price        강좌 가격 (0 이상)
 * @param level        강좌 난이도
 * @param status       강좌 상태
 * @param thumbnailUrl 강좌 썸네일 이미지 URL
 * @param createdAt    강좌 생성 일시
 * @param deletedAt    강좌 삭제 일시 (삭제되지 않은 경우 null)
 * @param approvedAt   강좌 승인 일시 (승인되지 않은 경우 null)
 * @param tags         강좌에 등록된 태그 목록
 * @param sections     강좌에 포함된 섹션 목록. {@link SectionDetailDTO}
 */
public record CourseDetailDTO(Long courseId,
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
                              Set<String> tags,
                              List<SectionDetailDTO> sections) {
    public static CourseDetailDTO from(Course course) {
        try {
            return new CourseDetailDTO(course.getCourseId(),
                    course.getMemberId(),
                    course.getTitle(),
                    course.getDescription(),
                    course.getPrice(),
                    CourseLevelDTO.from(course.getLevel()),
                    CourseStatusDTO.from(course.getStatus()),
                    new URL(course.getThumbnailUrl()),
                    course.getCreatedAt(),
                    course.getDeletedAt(),
                    course.getApprovedAt(),
                    course.getTagRefs()
                            .stream()
                            .map(ref -> ref.getTag().getTagName())
                            .collect(Collectors.toSet()),
                    course.getSections().stream().map(SectionDetailDTO::from).toList());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
