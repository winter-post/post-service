package com.devwinter.postservice.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    private Long memberId;
    private String title;
    private String contents;

    @Builder
    private Post(Long memberId, String title, String contents) {
        this.memberId = memberId;
        this.title = title;
        this.contents = contents;
    }
}
