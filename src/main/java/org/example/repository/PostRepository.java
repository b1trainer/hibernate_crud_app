package org.example.repository;

import org.example.model.Post;

public interface PostRepository {
    Long createPost(Post post);

    Post getPostById(Long id);

    boolean deleteById(Long id);

    boolean updatePost(Long id, String content);
}
