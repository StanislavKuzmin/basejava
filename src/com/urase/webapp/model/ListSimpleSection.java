package com.urase.webapp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListSimpleSection extends AbstractSection {

    private static final long serialVersionUID = 1L;

    private List<String> items;

    public ListSimpleSection() {
        items = new ArrayList<>();
    }

    public List<String> getItems() {
        return items;
    }

    public void addTextToList(String text) {
        items.add(text);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListSimpleSection that = (ListSimpleSection) o;
        return Objects.equals(items, that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(items);
    }

    @Override
    public String toString() {
        return items + "";
    }
}
