package com.nougatbar.lxp.course.dto.response;

import java.net.URI;
import java.time.LocalDateTime;

public record LectureDetailViewModel(Long lectureId,
                                     Long sectionId,
                                     String type,
                                     String title,
                                     int sequence,
                                     URI contentUri,
                                     LocalDateTime createdAt,
                                     LocalDateTime updatedAt)
        implements Comparable<LectureDetailViewModel> {
    public static LectureDetailViewModel from(LectureDetailDTO dto) {
        return new LectureDetailViewModel(dto.lectureId(),
                dto.sectionId(),
                dto.type().name(),
                dto.title(),
                dto.sequence(),
                dto.contentUri(),
                dto.createdAt(),
                dto.updatedAt());
    }

    @Override
    public int compareTo(LectureDetailViewModel o) {
        return Integer.compare(this.sequence, o.sequence);
    }
}
