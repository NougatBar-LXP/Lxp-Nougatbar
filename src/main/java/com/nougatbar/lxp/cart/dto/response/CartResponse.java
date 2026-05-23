package com.nougatbar.lxp.cart.dto.response;

import com.nougatbar.lxp.member.dto.response.MemberDTO;
import java.net.URI;
import java.util.Objects;
import java.util.function.Function;

public record CartResponse(Long courseId,
                           MemberDTO member,
                           String title,
                           int price,
                           String description,
                           String thumbnailUrl) {
    public static CartResponse from(CartDTO cartDTO, Function<URI, String> uriConverter) {
        Objects.requireNonNull(cartDTO);
        Objects.requireNonNull(uriConverter);

        String thumbnailUrl =
                cartDTO.thumbnailUri() == null ? null : uriConverter.apply(cartDTO.thumbnailUri());

        return new CartResponse(cartDTO.courseId(),
                cartDTO.member(),
                cartDTO.title(),
                cartDTO.price(),
                cartDTO.description(),
                thumbnailUrl);
    }
}
