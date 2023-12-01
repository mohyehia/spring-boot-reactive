package com.moh.yehia.reactive.service.impl;

import com.moh.yehia.reactive.dao.PostRepository;
import com.moh.yehia.reactive.mapper.PostMapper;
import com.moh.yehia.reactive.model.dto.PostDTO;
import com.moh.yehia.reactive.model.entity.Post;
import com.moh.yehia.reactive.service.design.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;

    @Override
    public Flux<PostDTO> findAll() {
        return postRepository.findAll()
                .map(postMapper::mapToPostDTO)
                .switchIfEmpty(Flux.empty());
    }

    @Override
    public Mono<Post> save(PostDTO postDTO) {
        Post post = postMapper.mapToPost(postDTO);
        post.setCreatedAt(LocalDateTime.now());
        return postRepository.save(post);
    }

    @Override
    public Mono<PostDTO> findById(String postId) {
        return postRepository.findById(postId)
                .map(postMapper::mapToPostDTO)
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<Post> update(String id, PostDTO postDTO) {
        return postRepository.findById(id)
                .flatMap(retrievedPost -> {
                    if (postDTO.slug() != null) retrievedPost.setSlug(postDTO.slug());
                    if (postDTO.body() != null) retrievedPost.setBody(postDTO.body());
                    if (postDTO.title() != null) retrievedPost.setTitle(postDTO.title());
                    if (postDTO.description() != null) retrievedPost.setDescription(postDTO.description());
                    return postRepository.save(retrievedPost);
                });
    }

    @Override
    public Mono<Void> deleteById(String postId) {
        return postRepository.deleteById(postId);
    }
}
