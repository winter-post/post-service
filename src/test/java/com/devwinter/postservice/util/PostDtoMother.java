package com.devwinter.postservice.util;

import com.devwinter.postservice.domain.dto.PostDto;

public class PostDtoMother {

    public static String DEFAULT_TITLE = "게시글 제목";
    public static String DEFAULT_CONTENTS = "게시글 본문";

    public static PostDto.PostDtoBuilder complete() {
        return PostDto.builder()
                .title(DEFAULT_TITLE)
                .contents(DEFAULT_CONTENTS);
    }
}
