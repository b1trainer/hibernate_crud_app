package org.example.repository.impl;

import org.example.dao.PostStatusEntity;
import org.example.mappers.EntityMapper;
import org.example.mappers.impl.PostStatusMapper;
import org.example.model.PostStatus;
import org.example.repository.PostStatusRepository;
import org.example.utils.SessionManager;
import org.hibernate.Transaction;

public class PostStatusRepositoryImpl implements PostStatusRepository {

    private final EntityMapper<PostStatus, PostStatusEntity> postStatusMapper = new PostStatusMapper();

    @Override
    public PostStatus getById(Long postId) {
        try (var session = SessionManager.getSession()) {
            session.beginTransaction();

            PostStatus foundStatus = null;
            PostStatusEntity status = session.find(PostStatusEntity.class, postId);
            if (status != null) {
                foundStatus = postStatusMapper.createDto(status);
            }

            return foundStatus;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updatePostStatus(Long postId, String newStatus) {
        Transaction transaction = null;

        try (var session = SessionManager.getSession()) {
            transaction = session.beginTransaction();
            PostStatusEntity status = session.find(PostStatusEntity.class, postId);
            status.setStatus(newStatus);
            transaction.commit();

            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            throw new RuntimeException(e);
        }
    }
}
