package com.devwinter.postservice.application.client.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseClientResponse<T> {
    private String code;
    private String message;
    private T body;
}
