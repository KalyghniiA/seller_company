package ru.kalyghnii.pet.seller_company.util;

public enum PersonRole {
    MANAGER("manager"),
    PROVISION("provision"),
    CLIENT("client");

    private final String title;

    PersonRole(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }


}
