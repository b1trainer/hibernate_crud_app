package org.example.repository.impl;

import org.example.dao.LabelEntity;
import org.example.dao.PostEntity;
import org.example.mappers.EntityMapper;
import org.example.mappers.impl.LabelMapper;
import org.example.model.Label;
import org.example.repository.LabelRepository;
import org.example.utils.SessionManager;
import org.hibernate.Transaction;

public class LabelRepositoryImpl implements LabelRepository {

    private final EntityMapper<Label, LabelEntity> labelMapper = new LabelMapper();

    @Override
    public Label getLabelById(Long id) {
        try (var session = SessionManager.getSession()) {
            session.beginTransaction();

            Label foundLabel = null;
            LabelEntity labelEntity = session.find(LabelEntity.class, id);
            if (labelEntity != null) {
                foundLabel = labelMapper.createDto(labelEntity);
            }

            return foundLabel;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Long createLabel(Label newLabel) {
        Transaction transaction = null;

        try (var session = SessionManager.getSession()) {
            LabelEntity labelEntity = labelMapper.createEntity(newLabel);

            transaction = session.beginTransaction();
            session.persist(labelEntity);
            transaction.commit();

            return labelEntity.getId();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteLabel(String label) {
        Transaction transaction = null;

        try (var session = SessionManager.getSession()) {
            transaction = session.beginTransaction();

            String hql = "FROM LabelEntity WHERE name = :labelName";
            LabelEntity entity = session.createQuery(hql, LabelEntity.class)
                    .setParameter("labelName", label)
                    .getSingleResult();

            if (entity != null) {
                session.remove(entity);
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
    public boolean updateLabel(Long postId, String label) {
        Transaction transaction = null;

        try (var session = SessionManager.getSession()) {
            transaction = session.beginTransaction();

            String hql = "FROM LabelEntity WHERE name = :labelName";
            LabelEntity labelEntity = session.createQuery(hql, LabelEntity.class)
                    .setParameter("labelName", label)
                    .getSingleResult();

            if (labelEntity != null) {
                session.find(PostEntity.class, postId).getLabels().add(labelEntity);
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
