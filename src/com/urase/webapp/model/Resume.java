package com.urase.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Resume implements Serializable {

    private static final long serialVersionUID = 1L;
    private String uuid;
    private String fullName;
    private final Map<SectionType, AbstractSection> sections = new EnumMap<>(SectionType.class);
    private final Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);

    public static final Resume EMPTY_RESUME = new Resume();

    static {
        EMPTY_RESUME.saveSection(SectionType.PERSONAL, SimpleSection.EMPTY);
        EMPTY_RESUME.saveSection(SectionType.OBJECTIVE, SimpleSection.EMPTY);
        EMPTY_RESUME.saveSection(SectionType.ACHIEVEMENT, ListSimpleSection.EMPTY);
        EMPTY_RESUME.saveSection(SectionType.QUALIFICATIONS, ListSimpleSection.EMPTY);
        EMPTY_RESUME.saveSection(SectionType.EDUCATION, OrganizationSection.EMPTY);
        EMPTY_RESUME.saveSection(SectionType.EXPERIENCE, OrganizationSection.EMPTY);
    }

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public Resume() {
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public void saveSection(SectionType sectionType, AbstractSection sectionOfResume) {
        sections.put(sectionType, sectionOfResume);
    }

    public Map<SectionType, AbstractSection> getSections() {
        return sections;
    }

    public void saveContact(ContactType contactType, String contact) {
        contacts.put(contactType, contact);
    }

    public Map<ContactType, String> getContacts() {
        return contacts;
    }

    public String getContact(ContactType type) {
        return contacts.get(type);
    }

    public AbstractSection getSection(SectionType type) {
        return sections.get(type);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return Objects.equals(uuid, resume.uuid) && Objects.equals(fullName, resume.fullName) &&
                Objects.equals(sections, resume.sections) && Objects.equals(contacts, resume.contacts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fullName, sections, contacts);
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
