package org.example.controller;

import org.example.model.Post;
import org.example.service.PostService;
import org.example.service.impl.PostServiceImpl;
import org.example.utils.Utils;

import org.example.Operations;

import java.util.Optional;

public class PostController {
    private final PostService postService;

    public PostController() {
        this.postService = new PostServiceImpl();
    }

    public void executeOperation() {
        int operationId = Utils.getIntFromInput(String.format("""
                        =============================
                            Post Controller
                        -----------------------------
                        %d. Create Post
                        %d. Read Post
                        %d. Update Post
                        %d. Delete Post
                        """,
                Operations.CREATE_ENTITY_OPERATION_ID.getId(),
                Operations.READ_ENTITY_OPERATION_ID.getId(),
                Operations.UPDATE_ENTITY_OPERATION_ID.getId(),
                Operations.DELETE_ENTITY_OPERATION_ID.getId()));

        switch (Operations.fromId(operationId)) {
            case CREATE_ENTITY_OPERATION_ID:
                createPost();
                break;

            case READ_ENTITY_OPERATION_ID:
                getPostById();
                break;

            case UPDATE_ENTITY_OPERATION_ID:
                updatePost();
                break;

            case DELETE_ENTITY_OPERATION_ID:
                deletePost();
                break;

            case null:
            default:
                System.out.println("Incorrect operation ID!");
                break;
        }
    }

    private void createPost() {
        System.out.println("Enter writer ID:");
        String writerId = Utils.readLine();

        System.out.println("Enter post content:");
        String content = Utils.readLine();

        Optional<Long> postId = Optional.ofNullable(postService.createPost(writerId, content));

        if (postId.isPresent()) {
            System.out.println("Post correctly created");
        }
    }

    private void getPostById() {
        System.out.println("Enter post ID:");
        String postId = Utils.readLine();

        Optional<Post> post = Optional.ofNullable(postService.getPost(postId));

        post.ifPresent(System.out::println);
    }

    private void updatePost() {
        System.out.println("Enter post ID:");
        String postId = Utils.readLine();

        System.out.println("Enter new post content:");
        String newContent = Utils.readLine();

        boolean isUpdated = postService.updatePost(postId, newContent);

        if (isUpdated) {
            System.out.println("Post correctly updated");
        }
    }

    private void deletePost() {
        System.out.println("Enter post ID:");
        String postId = Utils.readLine();

        boolean isDeleted = postService.deletePost(postId);

        if (isDeleted) {
            System.out.println("Post correctly deleted");
        }
    }
}
