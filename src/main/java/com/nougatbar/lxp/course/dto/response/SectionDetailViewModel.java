package com.nougatbar.lxp.course.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public record SectionDetailViewModel(Long sectionId,
                                     Long courseId,
                                     String title,
                                     int sequence,
                                     LocalDateTime createdAt,
                                     LocalDateTime updatedAt,
                                     List<LectureDetailViewModel> lectures,
                                     List<MissionDetailViewModel> missions)
        implements Comparable<SectionDetailViewModel> {
    public static SectionDetailViewModel from(SectionDetailDTO dto) {
        return new SectionDetailViewModel(dto.sectionId(),
                dto.courseId(),
                dto.title(),
                dto.sequence(),
                dto.createdAt(),
                dto.updatedAt(),
                dto.lectures().stream().map(LectureDetailViewModel::from).sorted().toList(),
                dto.missions().stream().map(MissionDetailViewModel::from).sorted().toList());
    }

    @Override
    public int compareTo(SectionDetailViewModel o) {
        return Integer.compare(sequence, o.sequence);
    }
}
