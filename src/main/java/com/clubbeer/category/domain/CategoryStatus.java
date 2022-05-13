package com.clubbeer.category.domain;

public enum CategoryStatus {
    ARCHIVE(0),
    ACTIVE(1);

    private final int value;

    CategoryStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
