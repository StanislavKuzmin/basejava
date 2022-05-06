package com.urase.webapp.model;

import java.util.EnumMap;
import java.util.Objects;
import java.util.UUID;

public class Resume {

    private String uuid;
    private String fullName;
    private EnumMap<SectionType, AbstractSection> sections;
    private EnumMap<ContactType, String> contacts;

    public Resume(String fullName) {
    this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
        sections = new EnumMap<>(SectionType.class);
        contacts = new EnumMap<>(ContactType.class);
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public void saveSection(SectionType sectionType, AbstractSection abstractSection) {
        switch (sectionType) {
            case PERSONAL:
                sections.put(SectionType.PERSONAL, abstractSection);
                break;
            case OBJECTIVE:
                sections.put(SectionType.OBJECTIVE, abstractSection);
                break;
            case ACHIEVEMENT:
                sections.put(SectionType.ACHIEVEMENT, abstractSection);
                break;
            case QUALIFICATIONS:
                sections.put(SectionType.QUALIFICATIONS, abstractSection);
            case EXPERIENCE:
                sections.put(SectionType.EXPERIENCE, abstractSection);
            case EDUCATION:
                sections.put(SectionType.EDUCATION, abstractSection);
        }
    }

    public AbstractSection getSection(SectionType sectionType) {
        return sections.get(sectionType);
    }

    public void saveContact(ContactType contactType, String contact) {
        switch (contactType) {
            case PHONE:
                contacts.put(ContactType.PHONE, contact);
                break;
            case SKYPE:
                contacts.put(ContactType.SKYPE, contact);
                break;
            case MAIL:
                contacts.put(ContactType.MAIL, contact);
                break;
            case LINKEDIN:
                contacts.put(ContactType.LINKEDIN, contact);
            case STACKOVERFLOW:
                contacts.put(ContactType.STACKOVERFLOW, contact);
            case HOMEPAGE:
                contacts.put(ContactType.HOMEPAGE, contact);
            case GITHUB:
                contacts.put(ContactType.GITHUB, contact);
        }
    }

    public String getContact(ContactType contactType) {
        return contacts.get(contactType);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return Objects.equals(uuid, resume.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

    @Override
    public String toString() {
        return "Resume{" +
                "uuid='" + uuid + '\'' +
                ", fullName='" + fullName + '\'' +
                ", sections=" + sections +
                ", contacts=" + contacts +
                '}';
    }
}
