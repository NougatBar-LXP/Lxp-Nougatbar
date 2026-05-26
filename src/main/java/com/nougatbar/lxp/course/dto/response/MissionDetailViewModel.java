package com.nougatbar.lxp.course.dto.response;

import java.time.LocalDateTime;

public record MissionDetailViewModel(Long missionId,
                                     Long sectionId,
                                     String title,
                                     String contents,
                                     int sequence,
                                     LocalDateTime createdAt,
                                     LocalDateTime updatedAt)
        implements Comparable<MissionDetailViewModel> {
    public static MissionDetailViewModel from(MissionDetailDTO dto) {
        return new MissionDetailViewModel(dto.missionId(),
                dto.sectionId(),
                dto.title(),
                dto.contents(),
                dto.sequence(),
                dto.createdAt(),
                dto.updatedAt());
    }

    @Override
    public int compareTo(MissionDetailViewModel o) {
        return Integer.compare(sequence, o.sequence);
    }
}
