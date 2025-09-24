package org.example;

public enum Operations {
    EXIT_APPLICATION_OPERATION_ID(0),
    CREATE_ENTITY_OPERATION_ID(1),
    READ_ENTITY_OPERATION_ID(2),
    UPDATE_ENTITY_OPERATION_ID(3),
    DELETE_ENTITY_OPERATION_ID(4);

    private final int id;

    Operations(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static Operations fromId(int id) {
        for (Operations operation : Operations.values()) {
            if (operation.getId() == id) {
                return operation;
            }
        }

        return null;
    }
}
