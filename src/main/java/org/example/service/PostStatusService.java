package org.example.service;

import org.example.Status;
import org.example.model.PostStatus;

public interface PostStatusService {
    PostStatus getPostStatus(String postId);

    boolean updatePostStatus(String postId, Status status);
}
