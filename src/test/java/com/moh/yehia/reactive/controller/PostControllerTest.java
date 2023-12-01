package com.moh.yehia.reactive.controller;

import com.moh.yehia.reactive.model.dto.PostDTO;
import com.moh.yehia.reactive.model.entity.Post;
import com.moh.yehia.reactive.service.design.PostService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@WebFluxTest(controllers = PostController.class)
class PostControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private PostService postService;

    @Test
    void testFindAllPosts() {
        // mock
        PostDTO postDTO1 = new PostDTO(UUID.randomUUID().toString(), "some random title 1", "some random description", "some random body", "some-random-slug", LocalDateTime.now());
        PostDTO postDTO2 = new PostDTO(UUID.randomUUID().toString(), "some random title 2", "some random description", "some random body", "some-random-slug", LocalDateTime.now());
        // given or when
        BDDMockito.given(postService.findAll())
                .willReturn(Flux.just(postDTO1, postDTO2));

        // assert
        webTestClient.get().uri("/api/v1/posts")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.[0].title").isEqualTo("some random title 1")
                .jsonPath("$.[1].title").isEqualTo("some random title 2");
    }

    @Test
    void shouldSavePost() {
        // mock
        PostDTO postDTO = new PostDTO("", "some random title", "some random description", "some random body", "slug", LocalDateTime.now());
        Post post = new Post(UUID.randomUUID().toString(), postDTO.title(), postDTO.description(), postDTO.body(), postDTO.slug(), postDTO.createdAt());

        // given
        BDDMockito.given(postService.save(ArgumentMatchers.any(PostDTO.class)))
                .willReturn(Mono.just(post));

        // when, verify & assertions
        webTestClient.post().uri("/api/v1/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(postDTO)
                .exchange()
                .expectStatus().isCreated()
                .returnResult(Post.class);
    }
//
//    @Test
//    void findById() {
//    }
//
//    @Test
//    void update() {
//    }
//
//    @Test
//    void deleteById() {
//    }
}