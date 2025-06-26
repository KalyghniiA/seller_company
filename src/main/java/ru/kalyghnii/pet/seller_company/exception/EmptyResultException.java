package ru.kalyghnii.pet.seller_company.exception;

public class EmptyResultException extends RuntimeException {
    private final String message;

    public EmptyResultException(String message) {
        this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
