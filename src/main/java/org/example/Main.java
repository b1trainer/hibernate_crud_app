package org.example;

import org.example.controller.LabelController;
import org.example.controller.PostController;
import org.example.controller.PostStatusController;
import org.example.controller.WriterController;

import org.example.utils.SessionManager;
import org.example.utils.DatabaseMigrator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            DatabaseMigrator migrator = new DatabaseMigrator();
            migrator.runMigrations();

            LabelController labelController = new LabelController();
            WriterController writerController = new WriterController();
            PostController postController = new PostController();
            PostStatusController postStatusController = new PostStatusController();

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.printf("""
                                =========================
                                    Blog-platform
                                -------------------------
                                %d. Labels control
                                %d. Writers control
                                %d. Posts control
                                %d. Post status control
                                =========================
                                %d. Exit
                                %n""",
                        Entities.LABEL_ENTITY_ID.getId(),
                        Entities.WRITER_ENTITY_ID.getId(),
                        Entities.POST_ENTITY_ID.getId(),
                        Entities.POST_STATUS_ID.getId(),
                        0);

                int entityNumber = scanner.nextInt();
                scanner.nextLine();

                if (entityNumber == 0) {
                    System.out.println("Program shutdown...");
                    SessionManager.closeConnection();
                    System.exit(0);
                }

                switch (Entities.getById(entityNumber)) {
                    case LABEL_ENTITY_ID:
                        labelController.executeOperation();
                        break;

                    case WRITER_ENTITY_ID:
                        writerController.executeOperation();
                        break;

                    case POST_ENTITY_ID:
                        postController.executeOperation();
                        break;

                    case POST_STATUS_ID:
                        postStatusController.executeOperation();
                        break;

                    case null:
                    default:
                        System.out.println("Try again");
                        break;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Application startup error: " + e.getMessage());
        }
    }
}