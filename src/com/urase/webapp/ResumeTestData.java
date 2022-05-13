package com.urase.webapp;

import com.urase.webapp.model.*;

import java.time.LocalDate;

import static com.urase.webapp.model.ContactType.*;
import static com.urase.webapp.model.SectionType.*;

public class ResumeTestData {

    public static void main(String[] args) {
        Resume resume = new Resume("Григорий Кислин");
        resume.saveContact(PHONE, "89211234567");
        resume.saveContact(SKYPE, "skype:grigory.kislin");
        resume.saveContact(MAIL, "gkislin@yandex.ru");
        resume.saveContact(LINKEDIN, "https://www.linkedin.com/in/gkislin");
        resume.saveContact(GITHUB, "https://github.com/gkislin");
        resume.saveContact(STACKOVERFLOW, "https://stackoverflow.com/users/548473/grigory-kislin");
        resume.saveContact(HOMEPAGE, "http://gkislin.ru/");

        SimpleSection position = new SimpleSection();
        position.setText("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");
        resume.saveSection(OBJECTIVE, position);

        SimpleSection personal = new SimpleSection();
        personal.setText("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры");
        resume.saveSection(PERSONAL, personal);

        ListSimpleSection achievement = new ListSimpleSection();
        achievement.addTextToList("Организация команды и успешная реализация Java проектов для сторонних заказчиков: приложения автопарк на стеке Spring Cloud/микросервисы, система мониторинга показателей спортсменов на Spring Boot, участие в проекте МЭШ на Play-2, многомодульный Spring Boot + Vaadin проект для комплексных DIY смет");
        achievement.addTextToList("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 3500 выпускников");
        resume.saveSection(ACHIEVEMENT, achievement);

        ListSimpleSection qualifications = new ListSimpleSection();
        qualifications.addTextToList("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qualifications.addTextToList("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        resume.saveSection(QUALIFICATIONS, qualifications);

        Experience experience_1 = new Experience();
        experience_1.setStartDate(LocalDate.of(2013, 10, 1));
        experience_1.setEndDate(LocalDate.now());
        experience_1.setPreviousPosition("Автор проекта");
        experience_1.setExperience("Создание, организация и проведение Java онлайн проектов и стажировок.");
        experience_1.setLinkEmployer("https://javaops.ru/");
        Experience experience_2 = new Experience();
        experience_2.setStartDate(LocalDate.of(2014, 10, 1));
        experience_2.setEndDate(LocalDate.of(2016, 1, 1));
        experience_2.setPreviousPosition("Старший разработчик (backend)");
        experience_2.setExperience("Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.");
        experience_2.setLinkEmployer("https://www.wrike.com/");
        Organization<Experience> listExperience = new Organization<>();
        listExperience.setOrganizations(experience_1);
        listExperience.setOrganizations(experience_2);
        resume.saveSection(EXPERIENCE, listExperience);

        Experience education_1 = new Experience();
        education_1.setStartDate(LocalDate.of(2013, 3, 1));
        education_1.setEndDate(LocalDate.of(2013, 5, 1));
        education_1.setLinkEmployer("Coursera");
        education_1.setExperience("Functional Programming Principles in Scala' by Martin Odersky");
        Experience education_2 = new Experience();
        education_2.setStartDate(LocalDate.of(2011, 3, 1));
        education_2.setEndDate(LocalDate.of(2011, 4, 1));
        education_2.setLinkEmployer("Luxoft");
        education_2.setExperience("Курс 'Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.");
        Organization<Experience> listEducation = new Organization<>();
        listEducation.setOrganizations(education_1);
        listEducation.setOrganizations(education_2);
        resume.saveSection(EDUCATION, listEducation);

        System.out.println(resume);

    }
}
