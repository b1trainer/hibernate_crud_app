package org.example.service.impl;

import org.example.model.Writer;
import org.example.repository.WriterRepository;
import org.example.repository.impl.WriterRepositoryImpl;
import org.example.service.WriterService;

public class WriterServiceImpl implements WriterService {

    private final WriterRepository writerRepository;

    public WriterServiceImpl() {
        this.writerRepository = new WriterRepositoryImpl();
    }

    public WriterServiceImpl(WriterRepository writerRepository) {
        this.writerRepository = writerRepository;
    }

    @Override
    public boolean createWriter(String firstName, String lastName) {
        Writer writer = new Writer();
        writer.setFirstName(firstName);
        writer.setLastName(lastName);

        return writerRepository.createWriter(writer);
    }

    @Override
    public Writer getWriter(String id) {
        try {
            Long writerId = Long.parseLong(id);

            return writerRepository.getById(writerId);
        } catch (NumberFormatException e) {
            System.out.println("Error when reading writer: " + e.getMessage());
        }

        return null;
    }

    @Override
    public boolean updateWriter(String id, String firstName, String lastName) {
        try {
            Long writerId = Long.parseLong(id);

            return writerRepository.updateWriter(writerId, firstName, lastName);
        } catch (NumberFormatException e) {
            System.out.println("Error when updating writer: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean deleteWriter(String id) {
        try {
            Long writerId = Long.parseLong(id);

            return writerRepository.deleteById(writerId);
        } catch (NumberFormatException e) {
            System.out.println("Error when deleting writer: " + e.getMessage());
        }

        return false;
    }
}
