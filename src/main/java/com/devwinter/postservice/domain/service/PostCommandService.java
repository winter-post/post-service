package com.devwinter.postservice.domain.service;

import com.devwinter.postservice.domain.dto.PostDto;
import com.devwinter.postservice.domain.entity.Post;
import com.devwinter.postservice.domain.exception.PostException;
import com.devwinter.postservice.domain.repository.PostCommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.devwinter.postservice.domain.exception.PostErrorCode.POST_SECOND_MANY_TIMES_NOT_REGISTER;

@Service
@RequiredArgsConstructor
public class PostCommandService {
    private final PostCommandRepository postCommandRepository;

    /**
     * methodName : createPost
     * <p>
     * description : 게시글 등록 메서드
     *
     * @param memberDto the member dto
     * @param postDto   the post dto
     * @throws PostException 게시글 3초 이내에 동일한 값에 대해서 등록 시
     *                       POST_SECOND_MANY_TIMES_NOT_REGISTER 발생
     * @author DongHun Lee
     */
    public void createPost(Long memberId, PostDto postDto) {
        manyTimesRegisterValid(memberId, postDto.title());
        postCommandRepository.save(createPostEntity(memberId, postDto));
    }
    private void manyTimesRegisterValid(Long memberId, String title) {
        LocalDateTime currentTime = LocalDateTime.now();
        postCommandRepository.findByMemberIdAndTitleAndCreatedAtBetween(memberId, title, currentTime.minusSeconds(3), currentTime)
                             .ifPresent(p -> {
                                 throw new PostException(POST_SECOND_MANY_TIMES_NOT_REGISTER);
                             });
    }
    private Post createPostEntity(Long memberId, PostDto postDto) {
        return Post.builder()
                   .memberId(memberId)
                   .title(postDto.title())
                   .contents(postDto.contents())
                   .build();
    }
}
