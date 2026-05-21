package com.nougatbar.lxp.community.controller;

import com.nougatbar.lxp.community.controller.form.CommunityPageCreateForm;
import com.nougatbar.lxp.community.controller.form.CommunityPageUpdateForm;
import com.nougatbar.lxp.community.controller.support.CommunityPageModelAssembler;
import com.nougatbar.lxp.community.dto.response.CommunityResponse;
import com.nougatbar.lxp.community.service.CommunityService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
public class CommunityPageController {

    private final CommunityService communityService;
    private final CommunityPageModelAssembler communityPageModelAssembler;

    public CommunityPageController(
            CommunityService communityService,
            CommunityPageModelAssembler communityPageModelAssembler
    ) {
        this.communityService = communityService;
        this.communityPageModelAssembler = communityPageModelAssembler;
    }

    @GetMapping({"/", "/community-ui"})
    public String community(
            @RequestParam(name = "cid", defaultValue = "1") Long courseId,
            @RequestParam(defaultValue = "ALL") String type,
            @RequestParam(defaultValue = "") String q,
            Model model
    ) {
        List<CommunityResponse> communities = communityService.findCommunities(courseId);
        communityPageModelAssembler.addCommunityModel(model, communities, null, courseId, type, q);
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
        communityPageModelAssembler.addCommunityModel(model, communities, null, courseId, type, q, true, false);
        return "community/index";
    }

    @PostMapping("/community-ui")
    public String createCommunity(@ModelAttribute CommunityPageCreateForm form) {
        CommunityResponse created = communityService.createCommunity(form.toRequest());

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
        CommunityResponse selectedCommunity = communityService.findCommunity(communityId);
        if (!selectedCommunity.courseId().equals(courseId)) {
            return redirectCommunityDetail(selectedCommunity.communityId(), selectedCommunity.courseId(), type, q);
        }

        List<CommunityResponse> communities = communityService.findCommunities(courseId);
        communityPageModelAssembler.addCommunityModel(model, communities, selectedCommunity, courseId, type, q);
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
        CommunityResponse selectedCommunity = communityService.findCommunity(communityId);
        if (!selectedCommunity.courseId().equals(courseId)) {
            return redirectCommunityEdit(selectedCommunity.communityId(), selectedCommunity.courseId(), type, q);
        }

        List<CommunityResponse> communities = communityService.findCommunities(courseId);
        communityPageModelAssembler.addCommunityModel(model, communities, selectedCommunity, courseId, type, q, true, true);
        return "community/index";
    }

    @PostMapping("/community-ui/{communityId}")
    public String updateCommunity(
            @PathVariable Long communityId,
            @ModelAttribute CommunityPageUpdateForm form
    ) {
        CommunityResponse updated = communityService.updateCommunity(communityId, form.toRequest());
        return redirectCommunityDetail(updated.communityId(), updated.courseId());
    }

    @PostMapping("/community-ui/{communityId}/delete")
    public String deleteCommunity(@PathVariable Long communityId) {
        CommunityResponse deleted = communityService.findCommunity(communityId);
        communityService.deleteCommunity(communityId);
        return "redirect:/community-ui?cid=" + deleted.courseId();
    }

    private String redirectCommunityDetail(Long communityId, Long courseId) {
        return "redirect:" + UriComponentsBuilder.fromPath("/community-ui/{communityId}")
                .queryParam("cid", courseId)
                .buildAndExpand(communityId)
                .toUriString();
    }

    private String redirectCommunityDetail(Long communityId, Long courseId, String type, String q) {
        return "redirect:" + UriComponentsBuilder.fromPath("/community-ui/{communityId}")
                .queryParam("cid", courseId)
                .queryParam("type", type)
                .queryParam("q", q)
                .buildAndExpand(communityId)
                .toUriString();
    }

    private String redirectCommunityEdit(Long communityId, Long courseId, String type, String q) {
        return "redirect:" + UriComponentsBuilder.fromPath("/community-ui/{communityId}/edit")
                .queryParam("cid", courseId)
                .queryParam("type", type)
                .queryParam("q", q)
                .buildAndExpand(communityId)
                .toUriString();
    }
}
