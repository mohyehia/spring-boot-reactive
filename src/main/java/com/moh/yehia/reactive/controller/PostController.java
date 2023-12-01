package com.moh.yehia.reactive.controller;

import com.moh.yehia.reactive.model.dto.PostDTO;
import com.moh.yehia.reactive.model.entity.Post;
import com.moh.yehia.reactive.service.design.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
@Log4j2
public class PostController {
    private final PostService postService;

    @GetMapping
    public Flux<PostDTO> findAll() {
        log.info("PostController :: findAll :: start");
        return postService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Post> save(@RequestBody PostDTO postDTO) {
        log.info("PostController :: save :: start");
        return postService.save(postDTO);
    }

    @GetMapping("/{id}")
    public Mono<PostDTO> findById(@PathVariable("id") String postId) {
        log.info("PostController :: findById :: start");
        return postService.findById(postId);
    }

    @PutMapping("/{id}")
    public Mono<Post> update(@PathVariable("id") String postId, @RequestBody PostDTO postDTO) {
        log.info("PostController :: update :: start");
        return postService.update(postId, postDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteById(@PathVariable("id") String postId) {
        log.info("PostController :: deleteById :: start");
        return postService.deleteById(postId);
    }

}
