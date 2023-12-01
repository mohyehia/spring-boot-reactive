package com.moh.yehia.reactive.mapper;

import com.moh.yehia.reactive.model.dto.PostDTO;
import com.moh.yehia.reactive.model.entity.Post;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {
    public PostDTO mapToPostDTO(Post post) {
        return new PostDTO(post.getId(),
                post.getTitle(),
                post.getDescription(),
                post.getBody(),
                post.getSlug(),
                post.getCreatedAt()
        );
    }

    public Post mapToPost(PostDTO postDTO) {
        return Post.builder()
                .title(postDTO.title())
                .body(postDTO.body())
                .description(postDTO.description())
                .slug(postDTO.slug())
                .build();
    }
}
