package com.urase.webapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Education extends AbstractSection {

    private LocalDate startDate;
    private LocalDate endDate;
    private String educationalOrganization;
    private String educationDescription;

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

    public String getEducationalOrganization() {
        return educationalOrganization;
    }

    public void setEducationalOrganization(String educationalOrganization) {
        this.educationalOrganization = educationalOrganization;
    }

    public String getEducationDescription() {
        return educationDescription;
    }

    public void setEducationDescription(String educationDescription) {
        this.educationDescription = educationDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Education education = (Education) o;
        return Objects.equals(startDate, education.startDate) && Objects.equals(endDate, education.endDate) && Objects.equals(educationalOrganization, education.educationalOrganization) && Objects.equals(educationDescription, education.educationDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate, educationalOrganization, educationDescription);
    }

    @Override
    public String toString() {
        return "{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", educationalOrganization='" + educationalOrganization + '\'' +
                ", educationDescription='" + educationDescription + '\'' +
                '}';
    }
}
