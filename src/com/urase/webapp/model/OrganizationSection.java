package com.urase.webapp.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class OrganizationSection extends AbstractSection {

    private static final long serialVersionUID = 1L;

    private final List<Organization> organizations;

    public static final OrganizationSection EMPTY = new OrganizationSection(Organization.EMPTY);

    public OrganizationSection() {
        organizations = new ArrayList<>();
    }

    public OrganizationSection(List<Organization> organizations) {
        Objects.requireNonNull(organizations, "organizations must not be null");
        this.organizations = organizations;
    }

    public OrganizationSection(Organization... organizations) {
        this.organizations = Arrays.asList(organizations);
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(Organization section) {
        organizations.add(section);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganizationSection that = (OrganizationSection) o;
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
