package com.nougatbar.lxp.community.controller.support;

import com.nougatbar.lxp.community.dto.response.CommunityResponse;
import com.nougatbar.lxp.community.entity.CommunityType;
import java.util.List;
import java.util.Locale;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class CommunityPageModelAssembler {

    public void addCommunityAttributes(
            Model model,
            List<CommunityResponse> allCommunities,
            CommunityResponse selectedCommunity,
            String type,
            String q,
            boolean showForm,
            boolean editMode
    ) {
        List<CommunityResponse> filteredCommunities = filterCommunities(allCommunities, type, q);

        model.addAttribute("communities", filteredCommunities);
        model.addAttribute("selectedCommunity", selectedCommunity);
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
}
