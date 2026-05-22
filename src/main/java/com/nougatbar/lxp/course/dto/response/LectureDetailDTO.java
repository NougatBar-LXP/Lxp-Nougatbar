package com.nougatbar.lxp.course.dto.response;

import com.nougatbar.lxp.course.dto.LectureContentTypeDTO;
import com.nougatbar.lxp.course.entity.Lecture;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;

/**
 * 강의 정보를 담는 DTO
 *
 * @param lectureId  강의 ID
 * @param sectionId  강의가 속한 섹션 ID
 * @param type       강의 유형 (PDF, VIDEO 등)
 * @param title      강의 제목
 * @param sequence   강의 순서. 같은 섹션 내에서의 순서로, 0-based index로 관리됩니다.
 * @param contentUrl 강의 자료 URL
 * @param createdAt  강의 생성 시간
 * @param updatedAt  강의 수정 시간 (수정한 적이 없다면 NULL)
 */
public record LectureDetailDTO(Long lectureId,
                               Long sectionId,
                               LectureContentTypeDTO type,
                               String title,
                               int sequence,
                               URL contentUrl,
                               LocalDateTime createdAt,
                               LocalDateTime updatedAt) {
    public static LectureDetailDTO from(Lecture lecture) {
        try {
            return new LectureDetailDTO(lecture.getLectureId(),
                    lecture.getSection().getSectionId(),
                    LectureContentTypeDTO.from(lecture.getType()),
                    lecture.getTitle(),
                    lecture.getSequence(),
                    new URL(lecture.getContentUrl()),
                    lecture.getCreatedAt(),
                    lecture.getUpdatedAt());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
