package com.urase.webapp.model;

public enum ContactType {
    PHONE("Телефон"),
    SKYPE("Скайп"),
    MAIL("Электронная почта"),
    LINKEDIN("Профиль Linkedin"),
    GITHUB("Профиль github"),
    STACKOVERFLOW("Профиль stackoverflow"),
    HOMEPAGE("Домашняя страница");

    private String nameContact;

    ContactType(String title) {
        this.nameContact = title;
    }

    public String getNameContact() {
        return nameContact;
    }
}
