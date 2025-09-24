package org.example;

public enum Status {
    ACTIVE("ACTIVE"),
    UNDER_REVIEW("UNDER_REVIEW");

    private String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
