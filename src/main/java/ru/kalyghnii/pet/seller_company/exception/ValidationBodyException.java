package ru.kalyghnii.pet.seller_company.exception;

public class ValidationBodyException extends RuntimeException {
    private final String message;
    public ValidationBodyException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
