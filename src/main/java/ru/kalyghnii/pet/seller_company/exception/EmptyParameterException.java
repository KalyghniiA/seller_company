package ru.kalyghnii.pet.seller_company.exception;

public class EmptyParameterException extends RuntimeException {
    private final String message;

    public EmptyParameterException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
