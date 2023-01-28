package com.devwinter.postservice.domain.dto;

import lombok.Builder;

@Builder
public record PostDto(
        String title,
        String contents
) {

}
