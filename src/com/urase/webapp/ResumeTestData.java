package com.urase.webapp;

import com.urase.webapp.model.*;

import java.time.LocalDate;

import static com.urase.webapp.model.ContactType.*;
import static com.urase.webapp.model.SectionType.*;

public class ResumeTestData {
    public static void main(String[] args) {
        ResumeTestData resumeTestData = new ResumeTestData();
        Resume resume = resumeTestData.createResume("uuid1", "Grigoriy Kislin");
        System.out.println(resume);
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
        SimpleSection position = new SimpleSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");
        resume.saveSection(OBJECTIVE, position);
    }

    private void initializePersonal(Resume resume) {
        SimpleSection personal = new SimpleSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры");
        resume.saveSection(PERSONAL, personal);
    }

    private void initializeAchievement(Resume resume) {
        ListSimpleSection achievement = new ListSimpleSection();
        achievement.addTextToList("Организация команды и успешная реализация Java проектов для сторонних заказчиков: приложения автопарк на стеке" +
                " Spring Cloud/микросервисы, система мониторинга показателей спортсменов на Spring Boot, участие в проекте МЭШ на Play-2," +
                " многомодульный Spring Boot + Vaadin проект для комплексных DIY смет");
        achievement.addTextToList("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven." +
                " Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок" +
                " и ведение проектов. Более 3500 выпускников");
        resume.saveSection(ACHIEVEMENT, achievement);
    }

    private void initializeQualifications(Resume resume) {
        ListSimpleSection qualifications = new ListSimpleSection();
        qualifications.addTextToList("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qualifications.addTextToList("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        resume.saveSection(QUALIFICATIONS, qualifications);
    }

    private void initializeExperience(Resume resume) {
        Period period1 = new Period(LocalDate.of(2013, 10, 1), LocalDate.now(),
                "Создание, организация и проведение Java онлайн проектов и стажировок.", "Автор проекта");
        Organization organization_1 = new Organization("https://javaops.ru/");
        organization_1.addToPeriods(period1);
        Period period2 = new Period(LocalDate.of(2014, 10, 1), LocalDate.of(2016, 1, 1),
                "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API," +
                        " Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.",
                "Старший разработчик (backend)");
        Organization organization_2 = new Organization("https://www.wrike.com/");
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
                " информационных технологий, механики и оптики");
        education_1.addToPeriods(period1);
        education_1.addToPeriods(period2);
        OrganizationSection listEducation = new OrganizationSection();
        listEducation.setOrganizations(education_1);
        resume.saveSection(EDUCATION, listEducation);
    }
}
