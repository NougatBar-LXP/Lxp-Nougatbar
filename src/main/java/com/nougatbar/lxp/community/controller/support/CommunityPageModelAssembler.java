package com.nougatbar.lxp.community.controller.support;

import com.nougatbar.lxp.community.dto.response.CommunityResponse;
import com.nougatbar.lxp.community.entity.CommunityType;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class CommunityPageModelAssembler {

    public void addCommunityModel(
            Model model,
            List<CommunityResponse> allCommunities,
            CommunityResponse selectedCommunity,
            Long courseId,
            String type,
            String q
    ) {
        addCommunityModel(model, allCommunities, selectedCommunity, courseId, type, q, false, false);
    }

    public void addCommunityModel(
            Model model,
            List<CommunityResponse> allCommunities,
            CommunityResponse selectedCommunity,
            Long courseId,
            String type,
            String q,
            boolean showForm,
            boolean editMode
    ) {
        List<CommunityResponse> filteredCommunities = filterCommunities(allCommunities, type, q);
        CommunityResponse resolvedSelectedCommunity = selectedCommunity;
        if (resolvedSelectedCommunity == null && !showForm) {
            resolvedSelectedCommunity = firstOrNull(filteredCommunities);
        }

        model.addAttribute("course", CourseView.sample(courseId));
        model.addAttribute("communities", filteredCommunities);
        model.addAttribute("selectedCommunity", resolvedSelectedCommunity);
        model.addAttribute("currentType", normalizeType(type));
        model.addAttribute("keyword", q);
        model.addAttribute("showForm", showForm);
        model.addAttribute("editMode", editMode);
        model.addAttribute("totalCount", allCommunities.size());
        model.addAttribute("lectureCount", countByType(allCommunities, CommunityType.LECTURE));
        model.addAttribute("missionCount", countByType(allCommunities, CommunityType.MISSION));
    }

    private List<CommunityResponse> filterCommunities(List<CommunityResponse> communities, String type, String q) {
        String normalizedType = normalizeType(type);
        String keyword = q == null ? "" : q.trim().toLowerCase(Locale.ROOT);

        return communities.stream()
                .filter(community -> "ALL".equals(normalizedType) || community.type().name().equals(normalizedType))
                .filter(community -> keyword.isBlank() || containsKeyword(community, keyword))
                .toList();
    }

    private String normalizeType(String type) {
        if (type == null || type.isBlank()) {
            return "ALL";
        }

        String normalizedType = type.trim().toUpperCase(Locale.ROOT);
        if ("COURSE".equals(normalizedType) || "LECTURE".equals(normalizedType) || "MISSION".equals(normalizedType)) {
            return normalizedType;
        }

        return "ALL";
    }

    private boolean containsKeyword(CommunityResponse community, String keyword) {
        return community.title().toLowerCase(Locale.ROOT).contains(keyword)
                || community.content().toLowerCase(Locale.ROOT).contains(keyword)
                || community.courseId().toString().contains(keyword);
    }

    private long countByType(List<CommunityResponse> communities, CommunityType type) {
        return communities.stream()
                .filter(community -> community.type() == type)
                .count();
    }

    private CommunityResponse firstOrNull(List<CommunityResponse> communities) {
        if (communities.isEmpty()) {
            return null;
        }

        return communities.get(0);
    }

    public record CourseView(
            Long courseId,
            Long memberId,
            String title,
            String description,
            Integer price,
            String level,
            String status,
            String thumbnailUrl,
            boolean sample
    ) {

        private static CourseView sample(Long courseId) {
            return new CourseView(
                    courseId,
                    1L,
                    "운영체제 공룡책 전공강의",
                    "운영체제의 핵심 개념을 전공 수업 흐름에 맞춰 학습하고, 질문과 토론으로 이해도를 높이는 강의입니다.",
                    33000,
                    "NORMAL",
                    "PUBLISHED",
                    "",
                    true
            );
        }

        public String descriptionText() {
            if (description == null || description.isBlank()) {
                return "강의 설명이 아직 등록되지 않았습니다.";
            }

            return description;
        }

        public String priceText() {
            if (price == null || price == 0) {
                return "무료";
            }

            return "₩" + NumberFormat.getNumberInstance(Locale.KOREA).format(price);
        }

        public String levelText() {
            if ("EASY".equals(level)) {
                return "난이도 입문";
            }
            if ("NORMAL".equals(level)) {
                return "난이도 중급";
            }
            if ("HARD".equals(level)) {
                return "난이도 고급";
            }

            return "난이도 미정";
        }

        public String statusText() {
            if ("PUBLISHED".equals(status)) {
                return "게시됨";
            }
            if ("DRAFT".equals(status)) {
                return "작성중";
            }
            if ("ARCHIVED".equals(status)) {
                return "보관됨";
            }

            return "상태 미정";
        }

        public String thumbnailLabel() {
            if (title == null || title.isBlank()) {
                return "LXP";
            }

            return title.substring(0, Math.min(title.length(), 2)).toUpperCase(Locale.ROOT);
        }

        public boolean hasThumbnail() {
            return thumbnailUrl != null && !thumbnailUrl.isBlank();
        }
    }
}
