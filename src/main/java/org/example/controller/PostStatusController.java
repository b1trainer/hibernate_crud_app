package org.example.controller;

import org.example.Status;
import org.example.model.PostStatus;
import org.example.service.PostStatusService;
import org.example.service.impl.PostStatusServiceImpl;
import org.example.utils.Utils;
import org.example.Operations;

import java.util.Optional;

public class PostStatusController {

    private final PostStatusService postStatusService;

    public PostStatusController() {
        this.postStatusService = new PostStatusServiceImpl();
    }

    public void executeOperation() {
        int operationId = Utils.getIntFromInput(String.format("""
                        ======================================
                            Post status controller
                        --------------------------------------
                        %d. Read post status
                        %d. Update post status
                        """,
                Operations.READ_ENTITY_OPERATION_ID.getId(),
                Operations.UPDATE_ENTITY_OPERATION_ID.getId()
        ));

        switch (Operations.fromId(operationId)) {
            case READ_ENTITY_OPERATION_ID:
                getStatusById();
                break;

            case UPDATE_ENTITY_OPERATION_ID:
                updateStatus();
                break;

            case null:
            default:
                System.out.println("Incorrect operation ID!");
                break;
        }
    }

    private void getStatusById() {
        System.out.println("Enter post id:");
        String postId = Utils.readLine();

        Optional<PostStatus> status = Optional.ofNullable(postStatusService.getPostStatus(postId));
        status.ifPresent(System.out::println);
    }

    private void updateStatus() {
        System.out.println("Enter post id:");
        String postId = Utils.readLine();

        String selectStatusMessage = String.format("""
                        =============
                        Choose status
                        -------------
                        1. %s
                        2. %s
                        """,
                Status.ACTIVE.getValue(),
                Status.UNDER_REVIEW.getValue());

        Status status = null;
        int newStatus = Utils.getIntFromInput(selectStatusMessage);
        switch (newStatus) {
            case 1:
                status = Status.ACTIVE;
                break;
            case 2:
                status = Status.UNDER_REVIEW;
                break;
            default:
                System.out.println("Incorrect status");
        }

        if (status != null) {
            boolean isUpdated = postStatusService.updatePostStatus(postId, status);

            if (isUpdated) {
                System.out.println("Post status updated");
            }
        }
    }
}
