package org.example.repository;

import org.example.model.Label;

public interface LabelRepository {
    Label getLabelById(Long id);

    Long createLabel(Label newLabel);

    boolean deleteLabel(String label);

    boolean updateLabel(Long postId, String label);
}
