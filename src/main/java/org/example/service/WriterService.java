package org.example.service;

import org.example.model.Writer;

public interface WriterService {
    boolean createWriter(String firstName, String lastName);

    Writer getWriter(String id);

    boolean updateWriter(String id, String firstName, String lastName);

    boolean deleteWriter(String id);
}
