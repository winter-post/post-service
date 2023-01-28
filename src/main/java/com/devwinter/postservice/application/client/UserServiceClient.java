package com.devwinter.postservice.application.client;

import com.devwinter.postservice.application.client.dto.BaseClientResponse;
import com.devwinter.postservice.application.client.dto.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name = "user-service-client",
        url = "${feign.url.prefix}" + "${feign.user-service.url}"
)
public interface UserServiceClient {
    @GetMapping("/api/v1/users")
    BaseClientResponse<UserInfo> getUserInfo(Long memberId);
}
