package org.example.repository.impl;

import org.example.dao.PostEntity;
import org.example.dao.WriterEntity;
import org.example.mappers.EntityMapper;
import org.example.mappers.impl.PostMapper;
import org.example.model.Post;
import org.example.repository.PostRepository;
import org.example.utils.SessionManager;
import org.hibernate.Transaction;

import java.time.Instant;

public class PostRepositoryImpl implements PostRepository {

    private final EntityMapper<Post, PostEntity> postMapper = new PostMapper();

    @Override
    public Long createPost(Post post) {
        Transaction transaction = null;

        try (var session = SessionManager.getSession()) {
            PostEntity postEntity = postMapper.createEntity(post);

            transaction = session.beginTransaction();
            WriterEntity writer = session.find(WriterEntity.class, post.getWriterId());
            postEntity.setWriter(writer);
            session.persist(postEntity);
            transaction.commit();

            return postEntity.getId();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            throw new RuntimeException(e);
        }
    }

    @Override
    public Post getPostById(Long postId) {
        try (var session = SessionManager.getSession()) {
            session.beginTransaction();

            String hql = "FROM PostEntity p LEFT JOIN FETCH p.labels WHERE p.id = :postId";

            Post post = null;
            PostEntity foundPost = session.createQuery(hql, PostEntity.class)
                    .setParameter("postId", postId)
                    .getSingleResult();

            if (foundPost != null) {
                post = postMapper.createDto(foundPost);
            }

            return post;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        Transaction transaction = null;

        try (var session = SessionManager.getSession()) {
            transaction = session.beginTransaction();

            PostEntity foundPost = session.find(PostEntity.class, id);
            if (foundPost != null) {
                session.remove(foundPost);
            }

            transaction.commit();

            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updatePost(Long id, String content) {
        Transaction transaction = null;

        try (var session = SessionManager.getSession()) {
            transaction = session.beginTransaction();

            PostEntity foundPost = session.find(PostEntity.class, id);
            if (foundPost != null) {
                foundPost.setUpdated(Instant.now());
                foundPost.setContent(content);
            }

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
