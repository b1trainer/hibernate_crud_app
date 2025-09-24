package org.example.mappers.impl;

import org.example.dao.WriterEntity;
import org.example.mappers.EntityMapper;
import org.example.model.Writer;

public class WriterMapper implements EntityMapper<Writer, WriterEntity> {

    @Override
    public Writer createDto(WriterEntity writerEntity) {
        Writer writer = new Writer();
        writer.setId(writerEntity.getId());
        writer.setFirstName(writerEntity.getFirstName());
        writer.setLastName(writerEntity.getLastName());

        return writer;
    }

    @Override
    public WriterEntity createEntity(Writer writerDto) {
        WriterEntity writerEntity = new WriterEntity();
        writerEntity.setFirstName(writerDto.getFirstName());
        writerEntity.setLastName(writerDto.getLastName());

        return writerEntity;
    }
}
