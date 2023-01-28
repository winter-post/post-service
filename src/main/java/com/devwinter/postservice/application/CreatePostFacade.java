package com.devwinter.postservice.application;

import com.devwinter.postservice.application.client.UserServiceClient;
import com.devwinter.postservice.application.client.dto.BaseClientResponse;
import com.devwinter.postservice.application.client.dto.UserInfo;
import com.devwinter.postservice.domain.dto.PostDto;
import com.devwinter.postservice.domain.exception.PostException;
import com.devwinter.postservice.domain.service.PostCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static com.devwinter.postservice.domain.exception.PostErrorCode.POST_FIND_USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CreatePostFacade {
    private final UserServiceClient userServiceClient;
    private final PostCommandService postCommandService;

    /**
     * methodName : createPost
     * <p>
     * description : FeignClient를 사용해서 외부 서비스(user-service)와 연동
     *
     * @param memberId the member id
     * @param postDto  the post dto
     * @throws PostException 회원이 존재하지 않을 경우 POST_FIND_USER_NOT_FOUND 에러 발생
     * @author DongHun Lee
     */
    public void createPost(Long memberId, PostDto postDto) {
        BaseClientResponse<UserInfo> userInfo = userServiceClient.getUserInfo(memberId);

        if (userInfo.getCode().equals(HttpStatus.OK.toString())) {
            postCommandService.createPost(userInfo.getBody().getMemberId(), postDto);
        } else {
            throw new PostException(POST_FIND_USER_NOT_FOUND);
        }
    }
}
