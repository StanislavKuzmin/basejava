package com.urase.webapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Experience extends AbstractSection {

    private LocalDate startDate;
    private LocalDate endDate;
    private String linkEmployer;
    private String experience;
    private String previousPosition;

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getLinkEmployer() {
        return linkEmployer;
    }

    public void setLinkEmployer(String linkEmployer) {
        this.linkEmployer = linkEmployer;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getPreviousPosition() {
        return previousPosition;
    }

    public void setPreviousPosition(String previousPosition) {
        this.previousPosition = previousPosition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Experience that = (Experience) o;
        return Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate) && Objects.equals(linkEmployer, that.linkEmployer) && Objects.equals(experience, that.experience) && Objects.equals(previousPosition, that.previousPosition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate, linkEmployer, experience, previousPosition);
    }

    @Override
    public String toString() {
        return "{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", linkEmployer='" + linkEmployer + '\'' +
                ", experience='" + experience + '\'' +
                ", previousPosition='" + previousPosition + '\'' +
                '}';
    }
}
