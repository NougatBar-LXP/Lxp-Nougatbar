package com.nougatbar.lxp.community.controller;

import com.nougatbar.lxp.community.application.CommunityAppService;
import com.nougatbar.lxp.community.dto.request.CommunityCreateRequest;
import com.nougatbar.lxp.community.dto.request.CommunityUpdateRequest;
import com.nougatbar.lxp.community.dto.response.CommunityResponse;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/community")
public class CommunityController {

    private final CommunityAppService communityAppService;

    public CommunityController(CommunityAppService communityAppService) {
        this.communityAppService = communityAppService;
    }

    @GetMapping
    public ResponseEntity<List<CommunityResponse>> findCommunities(@RequestParam(required = false) Long courseId) {
        return ResponseEntity.ok(communityAppService.findCommunities(courseId));
    }

    @PostMapping
    public ResponseEntity<CommunityResponse> createCommunity(@RequestBody CommunityCreateRequest request) {
        CommunityResponse response = communityAppService.createCommunity(request);
        return ResponseEntity.created(URI.create("/community/" + response.communityId()))
                .body(response);
    }

    @GetMapping("/{communityId}")
    public ResponseEntity<CommunityResponse> findCommunity(@PathVariable Long communityId) {
        return ResponseEntity.ok(communityAppService.findCommunity(communityId));
    }

    @PutMapping("/{communityId}")
    public ResponseEntity<CommunityResponse> updateCommunity(
            @PathVariable Long communityId,
            @RequestBody CommunityUpdateRequest request
    ) {
        return ResponseEntity.ok(communityAppService.updateCommunity(communityId, request));
    }

    @DeleteMapping("/{communityId}")
    public ResponseEntity<Void> deleteCommunity(@PathVariable Long communityId) {
        communityAppService.deleteCommunity(communityId);
        return ResponseEntity.noContent().build();
    }
}
