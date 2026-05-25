package com.nougatbar.lxp.course.dto.response;

import com.nougatbar.lxp.member.dto.response.MemberDTO;
import java.net.URI;
import java.util.List;

public record CourseSummeryViewModel(Long courseId,
                                     String courseTitle,
                                     String courseDescription,
                                     int coursePrice,
                                     String courseLevel,
                                     String courseStatus,
                                     URI courseThumbnailUri,
                                     List<String> courseTags,
                                     Long instructorId,
                                     String instructorName) {
    public static CourseSummeryViewModel from(CourseSummaryDTO courseSummary,
                                              MemberDTO instructor) {
        return new CourseSummeryViewModel(courseSummary.courseId(),
                courseSummary.title(),
                courseSummary.description(),
                courseSummary.price(),
                courseSummary.level().name(),
                courseSummary.status().name(),
                courseSummary.thumbnailUri(),
                courseSummary.tags().stream().sorted().toList(),
                instructor.memberId(),
                instructor.name());
    }
}
