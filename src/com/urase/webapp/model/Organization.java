package com.urase.webapp.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Organization extends AbstractSection {

    private LocalDate startDate;
    private LocalDate endDate;
    private String linkEmployer;
    private String title;
    private String description;
    private List<Period> periods;

    public Organization(LocalDate startDate, LocalDate endDate, String linkEmployer, String title, String description) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.linkEmployer = linkEmployer;
        this.title = title;
        this.description = description;
        periods = new ArrayList<>();
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getLinkEmployer() {
        return linkEmployer;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
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
        return Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate)
                && Objects.equals(linkEmployer, that.linkEmployer) && Objects.equals(title, that.title)
                && Objects.equals(description, that.description) && Objects.equals(periods, that.periods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate, linkEmployer, title, description, periods);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", linkEmployer='" + linkEmployer + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", periods=" + periods +
                '}';
    }
}
