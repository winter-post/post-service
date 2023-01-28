package com.devwinter.postservice.domain.service;

import com.devwinter.postservice.domain.dto.PostDto;
import com.devwinter.postservice.domain.entity.Post;
import com.devwinter.postservice.domain.exception.PostException;
import com.devwinter.postservice.domain.repository.PostCommandRepository;
import com.devwinter.postservice.util.PostDtoMother;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.devwinter.postservice.domain.exception.PostErrorCode.POST_SECOND_MANY_TIMES_NOT_REGISTER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PostCommandServiceTest {

    @Mock
    private PostCommandRepository postCommandRepository;

    @InjectMocks
    private PostCommandService postCommandService;

    @Test
    @DisplayName("게시글 작성 시 3초 이내에 동일한 내용이 등록되는 경우 테스트")
    void createPostManyTimeRegisterValidTest() {
        // given
        PostDto postDto = PostDtoMother.complete()
                                       .build();

        given(postCommandRepository.findByMemberIdAndTitleAndCreatedAtBetween(anyLong(), anyString(), any(), any()))
                .willReturn(Optional.of(Post.builder()
                                            .build()));

        // when
        PostException postException = assertThrows(
                PostException.class,
                () -> postCommandService.createPost(1L, postDto)
        );

        // then
        verify(postCommandRepository, times(1)).findByMemberIdAndTitleAndCreatedAtBetween(anyLong(), anyString(), any(), any());
        assertThat(postException.getPostErrorCode().getHttpStatus()).isEqualTo(POST_SECOND_MANY_TIMES_NOT_REGISTER.getHttpStatus());
        assertThat(postException.getPostErrorCode().getMessage()).isEqualTo(POST_SECOND_MANY_TIMES_NOT_REGISTER.getMessage());
    }
    
    @Test
    @DisplayName("게시글 저장 테스트")
    void createPostTest() {
        // given
        Long memberId = 1L;
        PostDto postDto = PostDtoMother.complete()
                                       .build();

        given(postCommandRepository.findByMemberIdAndTitleAndCreatedAtBetween(anyLong(), anyString(), any(), any()))
                .willReturn(Optional.empty());

        ArgumentCaptor<Post> postCaptor = ArgumentCaptor.forClass(Post.class);

        // when
        postCommandService.createPost(memberId, postDto);
        
        // then
        verify(postCommandRepository, times(1)).findByMemberIdAndTitleAndCreatedAtBetween(anyLong(), anyString(), any(), any());
        verify(postCommandRepository, times(1)).save(postCaptor.capture());
        assertThat(postCaptor.getValue().getMemberId()).isEqualTo(memberId);
        assertThat(postCaptor.getValue().getTitle()).isEqualTo(PostDtoMother.DEFAULT_TITLE);
        assertThat(postCaptor.getValue().getContents()).isEqualTo(PostDtoMother.DEFAULT_CONTENTS);
    }

}