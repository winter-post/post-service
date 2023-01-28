package com.devwinter.postservice.presentation.dto;

import com.devwinter.postservice.domain.dto.PostDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CreatePost {
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request {
        @NotBlank(message = "제목은 필수 입니다.")
        @Size(max = 50, message = "제목은 최대 50자까지 입력해주세요.")
        private String title;

        @NotBlank(message = "본문은 필수 입니다.")
        private String contents;

        public PostDto toDto() {
            return PostDto.builder()
                          .title(this.title)
                          .contents(this.contents)
                          .build();
        }
    }

    public static class Response {
        public static BaseResponse<CreatePost.Response> success() {
            return BaseResponse.<CreatePost.Response>builder()
                               .code(HttpStatus.OK.toString())
                               .message("게시글 작성에 성공하였습니다.")
                               .build();
        }
    }
}
