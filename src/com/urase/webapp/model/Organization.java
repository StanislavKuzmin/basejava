package com.urase.webapp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Organization implements Serializable {

    private static final long serialVersionUID = 1L;
    private String linkEmployer;
    private List<Period> periods;

    public Organization(String linkEmployer) {
        this.linkEmployer = linkEmployer;
        periods = new ArrayList<>();
    }

    public String getLinkEmployer() {
        return linkEmployer;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    public void addToPeriods(Period period) {
        periods.add(period);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(linkEmployer, that.linkEmployer) && Objects.equals(periods, that.periods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(linkEmployer, periods);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "linkEmployer='" + linkEmployer + '\'' +
                ", periods=" + periods +
                '}';
    }
}
