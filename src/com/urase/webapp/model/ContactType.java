package com.urase.webapp.model;

public enum ContactType {
    PHONE("Телефон"),
    SKYPE("Скайп") {
        @Override
        public String toHtml0(String value) {
            return getNameContact() + ": " + toLink("skype:" + value, value);
        }
    },
    MAIL("Электронная почта") {
        @Override
        public String toHtml0(String value) {
            return getNameContact() + ": " + toLink("mailto:" + value, value);
        }
    },
    LINKEDIN("Профиль Linkedin") {
        @Override
        public String toHtml0(String value) {
            return toLink(value);
        }
    },
    GITHUB("Профиль github") {
        @Override
        public String toHtml0(String value) {
            return toLink(value);
        }
    },
    STACKOVERFLOW("Профиль stackoverflow") {
        @Override
        public String toHtml0(String value) {
            return toLink(value);
        }
    },
    HOMEPAGE("Домашняя страница") {
        @Override
        public String toHtml0(String value) {
            return toLink(value);
        }
    };

    private final String nameContact;

    ContactType(String title) {
        this.nameContact = title;
    }

    public String getNameContact() {
        return nameContact;
    }

    protected String toHtml0(String value) {
        return nameContact + ": " + value;
    }

    public String toHtml(String value) {
        return (value == null) ? "" : toHtml0(value);
    }

    public String toLink(String href) {
        return toLink(href, nameContact);
    }

    public static String toLink(String href, String title) {
        return "<a href='" + href + "'>" + title + "</a>";
    }
}
