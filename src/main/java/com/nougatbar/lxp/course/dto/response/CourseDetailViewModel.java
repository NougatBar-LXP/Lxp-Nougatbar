package com.nougatbar.lxp.course.dto.response;

import com.nougatbar.lxp.member.dto.response.MemberDTO;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

public record CourseDetailViewModel(Long courseId,
                                    String courseTitle,
                                    String courseDescription,
                                    int coursePrice,
                                    String courseLevel,
                                    String courseStatus,
                                    URI courseThumbnailUri,
                                    LocalDateTime courseCreatedAt,
                                    LocalDateTime courseDeletedAt,
                                    LocalDateTime courseApprovedAt,
                                    List<String> courseTags,
                                    List<SectionDetailViewModel> courseSections,
                                    Long instructorId,
                                    String instructorName,
                                    boolean isEnrolled) {
    public static CourseDetailViewModel from(CourseDetailDTO courseDetail,
                                             MemberDTO instructor,
                                             boolean isEnrolled) {
        return new CourseDetailViewModel(courseDetail.courseId(),
                courseDetail.title(),
                courseDetail.description(),
                courseDetail.price(),
                courseDetail.level().name(),
                courseDetail.status().name(),
                courseDetail.thumbnailUri(),
                courseDetail.createdAt(),
                courseDetail.deletedAt(),
                courseDetail.approvedAt(),
                courseDetail.tags().stream().sorted().toList(),
                courseDetail.sections()
                        .stream()
                        .map(SectionDetailViewModel::from)
                        .sorted()
                        .toList(),
                instructor.memberId(),
                instructor.name(),
                isEnrolled);
    }
}
