package org.example.service.impl;

import org.example.model.Label;
import org.example.repository.LabelRepository;
import org.example.repository.impl.LabelRepositoryImpl;
import org.example.service.LabelService;

public class LabelServiceImpl implements LabelService {

    private final LabelRepository labelRepository;

    public LabelServiceImpl() {
        this.labelRepository = new LabelRepositoryImpl();
    }

    public LabelServiceImpl(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }

    @Override
    public Label getLabelById(String id) {
        try {
            long labelId = Long.parseLong(id);

            return labelRepository.getLabelById(labelId);
        } catch (NumberFormatException e) {
            System.out.println("Error when reading label: " + e.getMessage());
        }

        return null;
    }

    @Override
    public Long createLabel(String label) {
        Label newLabel = new Label();
        newLabel.setName(label);

        return labelRepository.createLabel(newLabel);
    }

    @Override
    public boolean deleteLabel(String label) {
        return labelRepository.deleteLabel(label);
    }

    @Override
    public boolean updateLabel(String postId, String label) {
        try {
            long post = Long.parseLong(postId);

            return labelRepository.updateLabel(post, label);
        } catch (NumberFormatException e) {
            System.out.println("Error when updating label: " + e.getMessage());
        }

        return false;
    }
}
