package org.example.service.impl;

import org.example.Status;
import org.example.model.Post;
import org.example.model.PostStatus;
import org.example.repository.PostRepository;
import org.example.repository.impl.PostRepositoryImpl;
import org.example.service.PostService;

import java.time.Instant;

public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl() {
        this.postRepository = new PostRepositoryImpl();
    }

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Long createPost(String writerId, String content) {
        try {
            Long authorId = Long.parseLong(writerId);

            PostStatus newStatus = new PostStatus();
            newStatus.setStatus(Status.UNDER_REVIEW.getValue());

            Post post = new Post();
            post.setWriterId(authorId);
            post.setContent(content);
            post.setCreated(Instant.now());
            post.setUpdated(Instant.now());
            post.setStatus(newStatus);

            return postRepository.createPost(post);
        } catch (NumberFormatException e) {
            System.out.println("Error when creating post: " + e.getMessage());
        }

        return null;
    }

    @Override
    public Post getPost(String id) {
        try {
            Long postId = Long.parseLong(id);

            return postRepository.getPostById(postId);
        } catch (NumberFormatException e) {
            System.out.println("Error when reading post: " + e.getMessage());
        }

        return null;
    }

    @Override
    public boolean updatePost(String id, String content) {
        try {
            Long postId = Long.parseLong(id);

            return postRepository.updatePost(postId, content);
        } catch (NumberFormatException e) {
            System.out.println("Error when updating post: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean deletePost(String id) {
        try {
            Long postId = Long.parseLong(id);

            return postRepository.deleteById(postId);
        } catch (NumberFormatException e) {
            System.out.println("Error when deleting post: " + e.getMessage());
        }

        return false;
    }
}
