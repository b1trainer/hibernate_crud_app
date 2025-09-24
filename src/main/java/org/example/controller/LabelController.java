package org.example.controller;

import org.example.Operations;
import org.example.model.Label;
import org.example.service.LabelService;
import org.example.service.impl.LabelServiceImpl;
import org.example.utils.Utils;

import java.util.Optional;

public class LabelController {

    private final LabelService labelService;

    public LabelController() {
        this.labelService = new LabelServiceImpl();
    }

    public void executeOperation() {
        int operationId = Utils.getIntFromInput(String.format("""
                        =============================
                            Label Controller
                        -----------------------------
                        %d. Create Label
                        %d. Read Label
                        %d. Add label to post
                        %d. Delete label
                        """,
                Operations.CREATE_ENTITY_OPERATION_ID.getId(),
                Operations.READ_ENTITY_OPERATION_ID.getId(),
                Operations.UPDATE_ENTITY_OPERATION_ID.getId(),
                Operations.DELETE_ENTITY_OPERATION_ID.getId()));

        switch (Operations.fromId(operationId)) {
            case CREATE_ENTITY_OPERATION_ID:
                createLabel();
                break;

            case READ_ENTITY_OPERATION_ID:
                getLabelById();
                break;

            case UPDATE_ENTITY_OPERATION_ID:
                updateLabel();
                break;

            case DELETE_ENTITY_OPERATION_ID:
                deleteLabel();
                break;

            case null:
            default:
                System.out.println("Incorrect operation ID!");
                break;
        }
    }

    private void createLabel() {
        System.out.println("Enter new label name:");
        String labelName = Utils.readLine();

        Long createdLabelId = labelService.createLabel(labelName);
        if (createdLabelId != null) {
            System.out.println("Created label id: " + createdLabelId);
        }
    }

    private void deleteLabel() {
        System.out.println("Enter label to delete:");
        String label = Utils.readLine();

        boolean isDeleted = labelService.deleteLabel(label);

        if (isDeleted) {
            System.out.println("Label correctly deleted");
        }
    }

    private void updateLabel() {
        System.out.println("Enter post ID:");
        String postId = Utils.readLine();

        System.out.println("Enter label name:");
        String label = Utils.readLine();

        boolean isUpdated = labelService.updateLabel(postId, label);

        if (isUpdated) {
            System.out.println("Label correctly added to post");
        }
    }

    private void getLabelById() {
        System.out.println("Enter label ID:");
        String labelId = Utils.readLine();

        Optional<Label> label = Optional.ofNullable(labelService.getLabelById(labelId));
        label.ifPresent(System.out::println);
    }
}
