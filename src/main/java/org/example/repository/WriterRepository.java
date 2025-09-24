package org.example.repository;

import org.example.model.Writer;

public interface WriterRepository {
    boolean createWriter(Writer writer);

    Writer getById(Long id);

    boolean updateWriter(Long id, String firstName, String lastName);

    boolean deleteById(Long id);
}
