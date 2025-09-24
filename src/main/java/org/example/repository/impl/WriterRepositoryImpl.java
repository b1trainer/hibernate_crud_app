package org.example.repository.impl;

import org.example.dao.WriterEntity;
import org.example.mappers.EntityMapper;
import org.example.mappers.impl.WriterMapper;
import org.example.model.Writer;
import org.example.repository.WriterRepository;
import org.example.utils.SessionManager;
import org.hibernate.Transaction;

public class WriterRepositoryImpl implements WriterRepository {

    private final EntityMapper<Writer, WriterEntity> writerMapper = new WriterMapper();

    @Override
    public boolean createWriter(Writer writer) {
        Transaction transaction = null;

        try (var session = SessionManager.getSession()) {
            WriterEntity writerEntity = writerMapper.createEntity(writer);

            transaction = session.beginTransaction();
            session.persist(writerEntity);
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
    public Writer getById(Long id) {
        try (var session = SessionManager.getSession()) {
            session.beginTransaction();

            Writer foundWriter = null;
            WriterEntity writer = session.find(WriterEntity.class, id);
            if (writer != null) {
                foundWriter = writerMapper.createDto(writer);
            }

            return foundWriter;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateWriter(Long id, String firstName, String lastName) {
        Transaction transaction = null;

        try (var session = SessionManager.getSession()) {
            transaction = session.beginTransaction();

            WriterEntity writer = session.find(WriterEntity.class, id);
            if (writer != null) {
                writer.setFirstName(firstName);
                writer.setLastName(lastName);
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
    public boolean deleteById(Long id) {
        Transaction transaction = null;

        try (var session = SessionManager.getSession()) {
            transaction = session.beginTransaction();

            WriterEntity writer = session.find(WriterEntity.class, id);
            if (writer != null) {
                session.remove(writer);
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
