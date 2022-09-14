package com.urase.webapp;

import com.urase.webapp.model.*;
import com.urase.webapp.util.HtmlEditor;

import java.time.LocalDate;

import static com.urase.webapp.model.ContactType.*;
import static com.urase.webapp.model.SectionType.*;

public class ResumeTestData {
    public static void main(String[] args) {
        ResumeTestData resumeTestData = new ResumeTestData();
        Resume resume = resumeTestData.createResume("uuid1", "Grigoriy Kislin");
            System.out.println(HtmlEditor.toHtml(resume));

    }

    public Resume createResume(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);
        initializeContacts(resume);
        initializeObjective(resume);
        initializePersonal(resume);
        initializeAchievement(resume);
        initializeQualifications(resume);
        initializeExperience(resume);
        initializeEducation(resume);
        return resume;
    }

    private void initializeContacts(Resume resume) {
        resume.saveContact(PHONE, "89211234567");
        resume.saveContact(SKYPE, "skype:grigory.kislin");
        resume.saveContact(MAIL, "gkislin@yandex.ru");
        resume.saveContact(LINKEDIN, "https://www.linkedin.com/in/gkislin");
        resume.saveContact(GITHUB, "https://github.com/gkislin");
        resume.saveContact(STACKOVERFLOW, "https://stackoverflow.com/users/548473/grigory-kislin");
        resume.saveContact(HOMEPAGE, "http://gkislin.ru/");
    }

    private void initializeObjective(Resume resume) {
        SimpleSection position = new SimpleSection("text1");
        resume.saveSection(OBJECTIVE, position);
    }

    private void initializePersonal(Resume resume) {
        SimpleSection personal = new SimpleSection("text2");
        resume.saveSection(PERSONAL, personal);
    }

    private void initializeAchievement(Resume resume) {
        ListSimpleSection achievement = new ListSimpleSection();
        achievement.addTextToList("text4");
        achievement.addTextToList("text5");
        resume.saveSection(ACHIEVEMENT, achievement);
    }

    private void initializeQualifications(Resume resume) {
        ListSimpleSection qualifications = new ListSimpleSection();
        qualifications.addTextToList("text6");
        qualifications.addTextToList("text7");
        resume.saveSection(QUALIFICATIONS, qualifications);
    }

    private void initializeExperience(Resume resume) {
        Period period1 = new Period(LocalDate.of(2013, 10, 1), LocalDate.now(),
                "text8", "Автор проекта");
        Organization organization_1 = new Organization("Java Online Profects","https://javaops.ru/");
        organization_1.addToPeriods(period1);
        Period period2 = new Period(LocalDate.of(2014, 10, 1), LocalDate.of(2016, 1, 1),
                "text9",
                "Старший разработчик (backend)");
        Organization organization_2 = new Organization("Wrike","https://www.wrike.com/");
        organization_2.addToPeriods(period2);
        OrganizationSection listExperience = new OrganizationSection();
        listExperience.setOrganizations(organization_1);
        listExperience.setOrganizations(organization_2);
        resume.saveSection(EXPERIENCE, listExperience);
    }

    private void initializeEducation(Resume resume) {
        Period period1 = new Period(LocalDate.of(1993, 9, 1), LocalDate.of(1996, 7, 1), "Аспирантура",
                "программист С, С++");
        Period period2 = new Period(LocalDate.of(1987, 9, 1), LocalDate.of(1993, 7, 1), "Инженер",
                "программист Fortran, C");
        Organization education_1 = new Organization("Санкт-Петербургский национальный исследовательский университет" +
                " информационных технологий, механики и оптики", "www.ifmo.ru");
        education_1.addToPeriods(period1);
        education_1.addToPeriods(period2);
        OrganizationSection listEducation = new OrganizationSection();
        listEducation.setOrganizations(education_1);
        resume.saveSection(EDUCATION, listEducation);
    }
}
