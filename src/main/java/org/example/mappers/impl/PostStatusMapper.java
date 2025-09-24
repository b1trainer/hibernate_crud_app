package org.example.mappers.impl;

import org.example.dao.PostStatusEntity;
import org.example.mappers.EntityMapper;
import org.example.model.PostStatus;

public class PostStatusMapper implements EntityMapper<PostStatus, PostStatusEntity> {

    @Override
    public PostStatus createDto(PostStatusEntity statusEntity) {
        PostStatus postStatus = new PostStatus();
        postStatus.setId(statusEntity.getId());
        postStatus.setPostId(statusEntity.getPost().getId());
        postStatus.setStatus(statusEntity.getStatus());

        return postStatus;
    }

    @Override
    public PostStatusEntity createEntity(PostStatus statusDto) {
        PostStatusEntity postStatusEntity = new PostStatusEntity();
        postStatusEntity.setStatus(statusDto.getStatus());

        return postStatusEntity;
    }
}
