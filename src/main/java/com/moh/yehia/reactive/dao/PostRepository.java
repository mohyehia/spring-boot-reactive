package com.moh.yehia.reactive.dao;

import com.moh.yehia.reactive.model.entity.Post;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PostRepository extends ReactiveCrudRepository<Post, String> {
}
