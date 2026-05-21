package com.nougatbar.lxp.cart.dto.response;

import com.nougatbar.lxp.member.dto.response.MemberDTO;
import java.net.URL;

public record CartResponse(Long courseId,
                           MemberDTO member,
                           String title,
                           int price,
                           String description,
                           URL thumbnailUrl) {


}

