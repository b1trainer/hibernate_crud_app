package org.example.service;

import org.example.model.Label;

public interface LabelService {
    Long createLabel(String label);

    Label getLabelById(String id);

    boolean updateLabel(String id, String newLabel);

    boolean deleteLabel(String id);
}
