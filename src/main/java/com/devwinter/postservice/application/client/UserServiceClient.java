package com.devwinter.postservice.application.client;

import com.devwinter.postservice.application.client.dto.BaseClientResponse;
import com.devwinter.postservice.application.client.dto.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "user-service",
        url = "${feign.url.prefix}" + "${feign.user-service.url}"
)
public interface UserServiceClient {
    @GetMapping("/api/v1/users/{userId}")
    BaseClientResponse<UserInfo> getUserInfo(@PathVariable("userId") Long userId);
}
