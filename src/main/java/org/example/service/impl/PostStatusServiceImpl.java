package org.example.service.impl;

import org.example.Status;
import org.example.model.PostStatus;
import org.example.repository.PostStatusRepository;
import org.example.repository.impl.PostStatusRepositoryImpl;
import org.example.service.PostStatusService;

public class PostStatusServiceImpl implements PostStatusService {

    private final PostStatusRepository postStatusRepository;

    public PostStatusServiceImpl() {
        this.postStatusRepository = new PostStatusRepositoryImpl();
    }

    public PostStatusServiceImpl(PostStatusRepository postStatusRepository) {
        this.postStatusRepository = postStatusRepository;
    }

    @Override
    public PostStatus getPostStatus(String postId) {
        try {
            Long id = Long.parseLong(postId);

            return postStatusRepository.getById(id);
        } catch (NumberFormatException e) {
            System.out.println("Error when reading post status: " + e.getMessage());
        }

        return null;
    }

    @Override
    public boolean updatePostStatus(String postId, Status status) {
        try {
            Long id = Long.parseLong(postId);

            return postStatusRepository.updatePostStatus(id, status.getValue());
        } catch (NumberFormatException e) {
            System.out.println("Error when updating post status: " + e.getMessage());
        }

        return false;
    }
}
