package org.example;

public enum Entities {
    LABEL_ENTITY_ID(1),
    WRITER_ENTITY_ID(2),
    POST_ENTITY_ID(3),
    POST_STATUS_ID(4);

    private final int id;

    Entities(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static Entities getById(int id) {
        for (Entities entity : Entities.values()) {
            if (entity.getId() == id) {
                return entity;
            }
        }

        return null;
    }
}
