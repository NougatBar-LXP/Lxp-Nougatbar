package com.nougatbar.lxp.community.controller;

import com.nougatbar.lxp.community.dto.request.CommunityCreateRequest;
import com.nougatbar.lxp.community.dto.request.CommunityUpdateRequest;
import com.nougatbar.lxp.community.dto.response.CommunityResponse;
import com.nougatbar.lxp.community.entity.CommunityType;
import com.nougatbar.lxp.community.service.CommunityService;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommunityPageController {

    private final CommunityService communityService;

    public CommunityPageController(CommunityService communityService) {
        this.communityService = communityService;
    }

    @GetMapping({"/", "/community-ui"})
    public String community(
            @RequestParam(name = "cid", defaultValue = "1") Long courseId,
            @RequestParam(defaultValue = "ALL") String type,
            @RequestParam(defaultValue = "") String q,
            Model model
    ) {
        List<CommunityResponse> communities = communityService.findCommunities(courseId);
        List<CommunityResponse> filteredCommunities = filterCommunities(communities, type, q);

        addCommunityModel(model, communities, filteredCommunities, firstOrNull(filteredCommunities), courseId, type, q);
        return "community/index";
    }

    @GetMapping("/community-ui/new")
    public String newCommunity(
            @RequestParam(name = "cid", defaultValue = "1") Long courseId,
            @RequestParam(defaultValue = "ALL") String type,
            @RequestParam(defaultValue = "") String q,
            Model model
    ) {
        List<CommunityResponse> communities = communityService.findCommunities(courseId);
        List<CommunityResponse> filteredCommunities = filterCommunities(communities, type, q);

        addCommunityModel(model, communities, filteredCommunities, null, courseId, type, q);
        model.addAttribute("showForm", true);
        model.addAttribute("editMode", false);
        return "community/index";
    }

    @PostMapping("/community-ui")
    public String createCommunity(
            @RequestParam Long courseId,
            @RequestParam Long memberId,
            @RequestParam CommunityType type,
            @RequestParam String title,
            @RequestParam String content
    ) {
        CommunityResponse created = communityService.createCommunity(
                new CommunityCreateRequest(courseId, memberId, type, title, content)
        );

        return "redirect:/community-ui/" + created.communityId() + "?cid=" + created.courseId();
    }

    @GetMapping("/community-ui/{communityId}")
    public String detailCommunity(
            @PathVariable Long communityId,
            @RequestParam(name = "cid", defaultValue = "1") Long courseId,
            @RequestParam(defaultValue = "ALL") String type,
            @RequestParam(defaultValue = "") String q,
            Model model
    ) {
        List<CommunityResponse> communities = communityService.findCommunities(courseId);
        List<CommunityResponse> filteredCommunities = filterCommunities(communities, type, q);

        addCommunityModel(model, communities, filteredCommunities, communityService.findCommunity(communityId), courseId, type, q);
        return "community/index";
    }

    @GetMapping("/community-ui/{communityId}/edit")
    public String editCommunity(
            @PathVariable Long communityId,
            @RequestParam(name = "cid", defaultValue = "1") Long courseId,
            @RequestParam(defaultValue = "ALL") String type,
            @RequestParam(defaultValue = "") String q,
            Model model
    ) {
        List<CommunityResponse> communities = communityService.findCommunities(courseId);
        List<CommunityResponse> filteredCommunities = filterCommunities(communities, type, q);

        addCommunityModel(model, communities, filteredCommunities, communityService.findCommunity(communityId), courseId, type, q);
        model.addAttribute("showForm", true);
        model.addAttribute("editMode", true);
        return "community/index";
    }

    @PostMapping("/community-ui/{communityId}")
    public String updateCommunity(
            @PathVariable Long communityId,
            @RequestParam(name = "cid", defaultValue = "1") Long courseId,
            @RequestParam String title,
            @RequestParam String content
    ) {
        communityService.updateCommunity(communityId, new CommunityUpdateRequest(title, content));
        return "redirect:/community-ui/" + communityId + "?cid=" + courseId;
    }

    @PostMapping("/community-ui/{communityId}/delete")
    public String deleteCommunity(
            @PathVariable Long communityId,
            @RequestParam(name = "cid", defaultValue = "1") Long courseId
    ) {
        communityService.deleteCommunity(communityId);
        return "redirect:/community-ui?cid=" + courseId;
    }

    private void addCommunityModel(
            Model model,
            List<CommunityResponse> allCommunities,
            List<CommunityResponse> filteredCommunities,
            CommunityResponse selectedCommunity,
            Long courseId,
            String type,
            String q
    ) {
        model.addAttribute("course", CourseView.sample(courseId));
        model.addAttribute("communities", filteredCommunities);
        model.addAttribute("selectedCommunity", selectedCommunity);
        model.addAttribute("currentType", normalizeType(type));
        model.addAttribute("keyword", q);
        model.addAttribute("showForm", false);
        model.addAttribute("editMode", false);
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
