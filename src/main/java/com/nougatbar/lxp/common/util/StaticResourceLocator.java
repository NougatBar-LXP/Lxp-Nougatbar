package com.nougatbar.lxp.common.util;

import java.net.URI;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * 정적 리소스의 URL을 생성하는 유틸리티 클래스입니다.
 */
@Component
public class StaticResourceLocator {
    private static final Logger LOGGER = LoggerFactory.getLogger(StaticResourceLocator.class);

    private final String basePath;

    public StaticResourceLocator(@Value("${app.static-resource-base-path:/static/}") String basePath) {
        this.basePath = basePath;

        LOGGER.atInfo().log("StaticResourceLocator initialized with basePath: {}", this.basePath);
    }

    /**
     * 주어진 리소스 URI를 정적 리소스 URL로 변환하여 반환합니다.
     *
     * @param resourceUri 리소스의 URI
     * @return 생성된 URL 문자열
     * @throws NullPointerException  resourceUri가 null인 경우
     * @throws IllegalStateException 현재 요청 컨텍스트가 없는 경우
     */
    public String locate(URI resourceUri) {
        Objects.requireNonNull(resourceUri);

        String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(basePath)
                .path(resourceUri.getPath())
                .toUriString();

        LOGGER.atDebug().log("Locating static resource: {} -> {}", resourceUri, url);

        return url;
    }
}
