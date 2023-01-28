package com.devwinter.postservice.application.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfo {
    @JsonProperty("userId")
    private Long memberId;
    private String email;
}
