package ru.kalyghnii.pet.seller_company.util;

public enum StatusCode {
    OK(200),
    CREATED(201),
    BAD_REQUEST(400),
    NOT_FOUND(404),
    INTERNAL_SERVER_ERROR(500);

    private final int title;

    StatusCode(int title) {
        this.title = title;
    }

    public int getTitle() {
        return title;
    }
}
