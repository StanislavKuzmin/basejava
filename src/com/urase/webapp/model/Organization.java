package com.urase.webapp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Organization<T> extends AbstractSection {

    private List<T> organizations;

    public Organization() {
        organizations = new ArrayList<>();
    }

    public List<T> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(T section) {
        organizations.add(section);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization<?> that = (Organization<?>) o;
        return Objects.equals(organizations, that.organizations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organizations);
    }

    @Override
    public String toString() {
        return organizations + "";
    }
}
