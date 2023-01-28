package com.devwinter.postservice.application;

import com.devwinter.postservice.application.client.UserServiceClient;
import com.devwinter.postservice.application.client.dto.BaseClientResponse;
import com.devwinter.postservice.application.client.dto.UserInfo;
import com.devwinter.postservice.domain.exception.PostException;
import com.devwinter.postservice.domain.service.PostCommandService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static com.devwinter.postservice.domain.exception.PostErrorCode.POST_FIND_USER_NOT_FOUND;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CreatePostFacadeTest {

    @Mock
    private UserServiceClient userServiceClient;
    @Mock
    private PostCommandService postCommandService;
    @InjectMocks
    private CreatePostFacade createPostFacade;

    @Test
    @DisplayName("게시글 생성 시 회원이 없을 경우 테스트")
    void createPostNotFoundUserTest() {
        // given
        BaseClientResponse<UserInfo> userInfo = BaseClientResponse.<UserInfo>builder()
                                                                  .code(HttpStatus.NOT_FOUND.toString())
                                                                  .build();
        given(userServiceClient.getUserInfo(anyLong()))
                .willReturn(userInfo);

        // when
        PostException postException =
                assertThrows(PostException.class, () -> createPostFacade.createPost(1L, any()));

        // then
        verify(userServiceClient, times(1)).getUserInfo(anyLong());
        assertThat(postException.getPostErrorCode()
                                .getHttpStatus()).isEqualTo(POST_FIND_USER_NOT_FOUND.getHttpStatus());
        assertThat(postException.getPostErrorCode()
                                .getMessage()).isEqualTo(POST_FIND_USER_NOT_FOUND.getMessage());
    }

}