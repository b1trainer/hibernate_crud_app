package org.example.controller;

import org.example.Operations;
import org.example.model.Writer;
import org.example.service.WriterService;
import org.example.service.impl.WriterServiceImpl;
import org.example.utils.Utils;

import java.util.Optional;

public class WriterController {

    private final WriterService writerService;

    public WriterController() {
        this.writerService = new WriterServiceImpl();
    }

    public void executeOperation() {
        int operationId = Utils.getIntFromInput(String.format("""
                        ========================================
                            Writers Controller
                        ----------------------------------------
                        %d. Create writer
                        %d. Read writer
                        %d. Update writer
                        %d. Delete writer
                        """,
                Operations.CREATE_ENTITY_OPERATION_ID.getId(),
                Operations.READ_ENTITY_OPERATION_ID.getId(),
                Operations.UPDATE_ENTITY_OPERATION_ID.getId(),
                Operations.DELETE_ENTITY_OPERATION_ID.getId()));

        switch (Operations.fromId(operationId)) {
            case CREATE_ENTITY_OPERATION_ID:
                createWriter();
                break;

            case READ_ENTITY_OPERATION_ID:
                readWriter();
                break;

            case UPDATE_ENTITY_OPERATION_ID:
                updateWriter();
                break;

            case DELETE_ENTITY_OPERATION_ID:
                deleteWriter();
                break;

            case null:
            default:
                System.out.println("Incorrect operation ID");
                break;
        }
    }

    private void createWriter() {
        System.out.println("Enter first name:");
        String firstName = Utils.readLine();

        System.out.println("Enter last name:");
        String lastName = Utils.readLine();

        boolean isCreated = writerService.createWriter(firstName, lastName);
        if (isCreated) {
            System.out.println("Writer " + firstName + " " + lastName + " created successfully");
        }
    }

    private void readWriter() {
        System.out.println("Enter writer ID:");
        String authorId = Utils.readLine();

        Optional<Writer> writer = Optional.ofNullable(writerService.getWriter(authorId));
        writer.ifPresent(System.out::println);
    }

    private void updateWriter() {
        System.out.println("Enter writer ID:");
        String authorId = Utils.readLine();

        System.out.println("Enter new first name:");
        String newFirstName = Utils.readLine();

        System.out.println("Enter new last name:");
        String newLastName = Utils.readLine();

        boolean isUpdated = writerService.updateWriter(authorId, newFirstName, newLastName);

        if (isUpdated) {
            System.out.println("Writer correctly updated");
        }
    }

    private void deleteWriter() {
        System.out.println("Enter writer ID:");
        String authorId = Utils.readLine();

        boolean isDeleted = writerService.deleteWriter(authorId);

        if (isDeleted) {
            System.out.println("Writer correctly deleted");
        }
    }
}
