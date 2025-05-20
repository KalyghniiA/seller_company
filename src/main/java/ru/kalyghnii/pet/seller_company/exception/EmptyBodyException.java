package ru.kalyghnii.pet.seller_company.exception;

public class EmptyBodyException extends RuntimeException {
    private final String message;

    public EmptyBodyException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
