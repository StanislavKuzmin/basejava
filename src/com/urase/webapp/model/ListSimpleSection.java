package com.urase.webapp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListSimpleSection extends AbstractSection {

    private List<String> simpleSectionList;

    public ListSimpleSection() {
        simpleSectionList = new ArrayList<>();
    }

    public List<String> getSimpleSectionList() {
        return simpleSectionList;
    }

    public void addTextToList(String text) {
        simpleSectionList.add(text);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListSimpleSection that = (ListSimpleSection) o;
        return Objects.equals(simpleSectionList, that.simpleSectionList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(simpleSectionList);
    }

    @Override
    public String toString() {
        return simpleSectionList + "";
    }
}
