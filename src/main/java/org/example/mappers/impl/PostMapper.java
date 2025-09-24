package org.example.mappers.impl;

import org.example.dao.LabelEntity;
import org.example.dao.PostEntity;
import org.example.dao.PostStatusEntity;
import org.example.mappers.EntityMapper;
import org.example.model.Label;
import org.example.model.Post;
import org.example.model.PostStatus;

import java.util.List;

public class PostMapper implements EntityMapper<Post, PostEntity> {

    private final EntityMapper<PostStatus, PostStatusEntity> postStatusMapper = new PostStatusMapper();
    private final EntityMapper<Label, LabelEntity> labelMapper = new LabelMapper();

    @Override
    public Post createDto(PostEntity entity) {
        Post post = new Post();
        post.setId(entity.getId());
        post.setContent(entity.getContent());
        post.setCreated(entity.getCreated());
        post.setUpdated(entity.getUpdated());
        post.setWriterId(entity.getWriter().getId());

        List<Label> labels = entity.getLabels().stream().map(labelMapper::createDto).toList();
        post.setLabels(labels);

        PostStatus status = postStatusMapper.createDto(entity.getStatus());
        post.setStatus(status);

        return post;
    }

    @Override
    public PostEntity createEntity(Post postDto) {
        PostEntity postEntity = new PostEntity();
        postEntity.setContent(postDto.getContent());
        postEntity.setCreated(postDto.getCreated());
        postEntity.setUpdated(postDto.getUpdated());

        PostStatusEntity status = postStatusMapper.createEntity(postDto.getStatus());
        status.setPost(postEntity);

        postEntity.setStatus(status);

        return postEntity;
    }
}
