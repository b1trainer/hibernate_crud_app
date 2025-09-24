package org.example.mappers.impl;

import org.example.dao.LabelEntity;
import org.example.mappers.EntityMapper;
import org.example.model.Label;

public class LabelMapper implements EntityMapper<Label, LabelEntity> {

    @Override
    public Label createDto(LabelEntity entity) {
        Label label = new Label();
        label.setId(entity.getId());
        label.setName(entity.getName());

        return label;
    }

    @Override
    public LabelEntity createEntity(Label labelDto) {
        LabelEntity labelEntity = new LabelEntity();
        labelEntity.setName(labelDto.getName());

        return labelEntity;
    }
}
