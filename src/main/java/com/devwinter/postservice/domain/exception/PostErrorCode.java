package com.devwinter.postservice.domain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@RequiredArgsConstructor
public enum PostErrorCode {

    POST_SECOND_MANY_TIMES_NOT_REGISTER(CONFLICT, "게시글을 한번에 여러번 등록할 수 없습니다."),
    POST_FIND_USER_NOT_FOUND(NOT_FOUND, "회원 정보가 없습니다.")

    ;

    private final HttpStatus httpStatus;
    private final String message;
}
