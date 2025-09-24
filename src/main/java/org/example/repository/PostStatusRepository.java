package org.example.repository;

import org.example.model.PostStatus;

public interface PostStatusRepository {
    PostStatus getById(Long postId);

    boolean updatePostStatus(Long postId, String status);
}
