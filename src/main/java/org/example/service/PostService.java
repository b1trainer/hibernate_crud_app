package org.example.service;

import org.example.model.Post;

public interface PostService {
    Long createPost(String writerId, String content);

    Post getPost(String id);

    boolean updatePost(String id, String content);

    boolean deletePost(String id);
}
