package com.nougatbar.lxp.community.controller;

import com.nougatbar.lxp.common.util.StaticResourceLocator;
import com.nougatbar.lxp.community.application.CommunityAppService;
import com.nougatbar.lxp.community.controller.form.CommunityPageCreateForm;
import com.nougatbar.lxp.community.controller.form.CommunityPageUpdateForm;
import com.nougatbar.lxp.community.controller.support.CommunityPageModelAssembler;
import com.nougatbar.lxp.community.dto.response.CommunityResponse;
import com.nougatbar.lxp.course.application.CourseAppService;
import com.nougatbar.lxp.course.dto.response.CourseDetailViewModel;
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

    private final CommunityAppService communityAppService;
    private final CourseAppService courseAppService;
    private final CommunityPageModelAssembler communityPageModelAssembler;
    private final StaticResourceLocator staticResourceLocator;

    public CommunityPageController(
            CommunityAppService communityAppService,
            CourseAppService courseAppService,
            CommunityPageModelAssembler communityPageModelAssembler,
            StaticResourceLocator staticResourceLocator
    ) {
        this.communityAppService = communityAppService;
        this.courseAppService = courseAppService;
        this.communityPageModelAssembler = communityPageModelAssembler;
        this.staticResourceLocator = staticResourceLocator;
    }

    @GetMapping("/courses/{courseId}/community")
    public String courseCommunity(
            Model model,
            @PathVariable Long courseId,
            @RequestParam(defaultValue = "ALL") String type,
            @RequestParam(defaultValue = "") String q,
            @RequestParam(required = false) Long communityId,
            @RequestParam(defaultValue = "") String communityMode
    ) {
        CourseDetailViewModel course = courseAppService.getCourseDetail(courseId);
        model.addAttribute("course", course);
        model.addAttribute("thumbnailUrl", staticResourceLocator.locate(course.courseThumbnailUri()));

        List<CommunityResponse> communities = communityAppService.findCommunities(courseId);
        CommunityResponse selectedCommunity = resolveCommunity(courseId, communityId);
        boolean newMode = "new".equalsIgnoreCase(communityMode);
        boolean editMode = "edit".equalsIgnoreCase(communityMode) && selectedCommunity != null;

        communityPageModelAssembler.addCommunityAttributes(
                model,
                communities,
                selectedCommunity,
                type,
                q,
                newMode || editMode,
                editMode
        );

        return "community/community";
    }

    @GetMapping("/courses/{courseId}/community/{communityId}")
    public String courseCommunityDetail(
            Model model,
            @PathVariable Long courseId,
            @PathVariable Long communityId,
            @RequestParam(defaultValue = "ALL") String type,
            @RequestParam(defaultValue = "") String q
    ) {
        CommunityResponse selectedCommunity = communityAppService.findCommunity(communityId);
        if (!selectedCommunity.courseId().equals(courseId)) {
            return redirectCommunityDetail(selectedCommunity.communityId(), selectedCommunity.courseId(), type, q);
        }

        addCourseModel(model, courseId);
        model.addAttribute("selectedCommunity", selectedCommunity);
        model.addAttribute("currentType", type);
        model.addAttribute("keyword", q);

        return "community/community-detail";
    }

    @GetMapping("/community-ui")
    public String community(
            @RequestParam(name = "cid", defaultValue = "1") Long courseId,
            @RequestParam(defaultValue = "ALL") String type,
            @RequestParam(defaultValue = "") String q
    ) {
        return redirectCommunityList(courseId, type, q);
    }

    @GetMapping("/community-ui/new")
    public String newCommunity(
            @RequestParam(name = "cid", defaultValue = "1") Long courseId,
            @RequestParam(defaultValue = "ALL") String type,
            @RequestParam(defaultValue = "") String q
    ) {
        return redirectCommunityNew(courseId, type, q);
    }

    @PostMapping("/community-ui")
    public String createCommunity(@ModelAttribute CommunityPageCreateForm form) {
        CommunityResponse created = communityAppService.createCommunity(form.toRequest());

        return redirectCommunityList(created.courseId(), "ALL", "");
    }

    @GetMapping("/community-ui/{communityId}")
    public String detailCommunity(
            @PathVariable Long communityId,
            @RequestParam(name = "cid", defaultValue = "1") Long courseId,
            @RequestParam(defaultValue = "ALL") String type,
            @RequestParam(defaultValue = "") String q
    ) {
        CommunityResponse selectedCommunity = communityAppService.findCommunity(communityId);
        if (!selectedCommunity.courseId().equals(courseId)) {
            return redirectCommunityDetail(selectedCommunity.communityId(), selectedCommunity.courseId(), type, q);
        }

        return redirectCommunityDetail(selectedCommunity.communityId(), courseId, type, q);
    }

    @GetMapping("/community-ui/{communityId}/edit")
    public String editCommunity(
            @PathVariable Long communityId,
            @RequestParam(name = "cid", defaultValue = "1") Long courseId,
            @RequestParam(defaultValue = "ALL") String type,
            @RequestParam(defaultValue = "") String q
    ) {
        CommunityResponse selectedCommunity = communityAppService.findCommunity(communityId);
        if (!selectedCommunity.courseId().equals(courseId)) {
            return redirectCommunityEdit(selectedCommunity.communityId(), selectedCommunity.courseId(), type, q);
        }

        return redirectCommunityEdit(selectedCommunity.communityId(), courseId, type, q);
    }

    @PostMapping("/community-ui/{communityId}")
    public String updateCommunity(
            @PathVariable Long communityId,
            @ModelAttribute CommunityPageUpdateForm form
    ) {
        CommunityResponse updated = communityAppService.updateCommunity(communityId, form.toRequest());
        return redirectCommunityDetail(updated.communityId(), updated.courseId());
    }

    @PostMapping("/community-ui/{communityId}/delete")
    public String deleteCommunity(@PathVariable Long communityId) {
        CommunityResponse deleted = communityAppService.findCommunity(communityId);
        communityAppService.deleteCommunity(communityId);
        return redirectCommunityList(deleted.courseId(), "ALL", "");
    }

    private String redirectCommunityDetail(Long communityId, Long courseId) {
        return "redirect:" + UriComponentsBuilder.fromPath("/courses/{courseId}/community/{communityId}")
                .buildAndExpand(courseId, communityId)
                .toUriString();
    }

    private String redirectCommunityDetail(Long communityId, Long courseId, String type, String q) {
        return "redirect:" + UriComponentsBuilder.fromPath("/courses/{courseId}/community/{communityId}")
                .queryParam("type", type)
                .queryParam("q", q)
                .buildAndExpand(courseId, communityId)
                .toUriString();
    }

    private String redirectCommunityEdit(Long communityId, Long courseId, String type, String q) {
        return "redirect:" + UriComponentsBuilder.fromPath("/courses/{courseId}/community")
                .queryParam("communityId", communityId)
                .queryParam("communityMode", "edit")
                .queryParam("type", type)
                .queryParam("q", q)
                .buildAndExpand(courseId)
                .toUriString();
    }

    private String redirectCommunityList(Long courseId, String type, String q) {
        return "redirect:" + UriComponentsBuilder.fromPath("/courses/{courseId}/community")
                .queryParam("type", type)
                .queryParam("q", q)
                .buildAndExpand(courseId)
                .toUriString();
    }

    private String redirectCommunityNew(Long courseId, String type, String q) {
        return "redirect:" + UriComponentsBuilder.fromPath("/courses/{courseId}/community")
                .queryParam("communityMode", "new")
                .queryParam("type", type)
                .queryParam("q", q)
                .buildAndExpand(courseId)
                .toUriString();
    }

    private CommunityResponse resolveCommunity(Long courseId, Long communityId) {
        if (communityId == null) {
            return null;
        }

        CommunityResponse community = communityAppService.findCommunity(communityId);
        if (!community.courseId().equals(courseId)) {
            return null;
        }

        return community;
    }

    private void addCourseModel(Model model, Long courseId) {
        CourseDetailViewModel course = courseAppService.getCourseDetail(courseId);
        model.addAttribute("course", course);
        model.addAttribute("thumbnailUrl", staticResourceLocator.locate(course.courseThumbnailUri()));
    }
}
