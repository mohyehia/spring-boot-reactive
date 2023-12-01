package com.moh.yehia.reactive.service.design;

import com.moh.yehia.reactive.model.dto.PostDTO;
import com.moh.yehia.reactive.model.entity.Post;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PostService {

    Flux<PostDTO> findAll();

    Mono<Post> save(PostDTO postDTO);

    Mono<PostDTO> findById(String postId);

    Mono<Post> update(String id, PostDTO postDTO);

    Mono<Void> deleteById(String postId);

}
