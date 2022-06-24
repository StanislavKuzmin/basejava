package com.urase.webapp.model;

import java.util.Objects;

public class SimpleSection extends AbstractSection {

    private static final long serialVersionUID = 1L;
    private String text;

    public SimpleSection(String text) {
        this.text = text;
    }

    public SimpleSection() {
    }

    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleSection that = (SimpleSection) o;
        return Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }

    @Override
    public String toString() {
        return text;
    }
}
