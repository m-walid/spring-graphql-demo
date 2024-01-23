package dev.walid.graphqldemo.dto;


import dev.walid.graphqldemo.model.Post;
import dev.walid.graphqldemo.model.User;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;

public interface UserPost {
    static Post toPost(UserPost userPost) {
        return Post.builder()
                .id(userPost.getId())
                .title(userPost.getTitle())
                .content(userPost.getContent())
                .image(userPost.getImage())
                .createdAt(userPost.getCreatedAt())
                .user(User.builder()
                        .id(userPost.getUserId())
                        .build())
                .build();
    }

    Long getId();

    String getTitle();

    String getContent();

    @Value("#{target.user_id}")
    Long getUserId();

    String getImage();

    @Value("#{target.created_at}")
    LocalDateTime getCreatedAt();

    @Value("#{target.total_user_posts}")
    Long getTotalUserPosts();

    @Value("#{target.user_post_number}")
    Long getUserPostNumber();

}
