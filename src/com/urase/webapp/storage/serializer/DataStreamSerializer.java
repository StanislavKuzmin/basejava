package com.urase.webapp.storage.serializer;

import com.urase.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements SerializationStrategy {
    @Override
    public void serializeResume(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            dos.writeInt(resume.getContacts().size());
            for (Map.Entry<ContactType, String> entry : resume.getContacts().entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }
            for (Map.Entry<SectionType, AbstractSection> entry : resume.getSections().entrySet()) {
                SectionType sectionType = entry.getKey();
                AbstractSection abstractSection = entry.getValue();
                dos.writeUTF(sectionType.name());
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        dos.writeUTF(abstractSection.toString());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        ListSimpleSection listSimpleSection = (ListSimpleSection) abstractSection;
                        List<String> items = listSimpleSection.getItems();
                        dos.writeInt(items.size());
                        for (String string : items) {
                            dos.writeUTF(string);
                        }
                        break;
                    case EDUCATION:
                    case EXPERIENCE:
                        OrganizationSection organizationSection = (OrganizationSection) abstractSection;
                        List<Organization> organizations = organizationSection.getOrganizations();
                        dos.writeInt(organizations.size());
                        for (Organization organization : organizations) {
                            dos.writeUTF(organization.getLinkEmployer());
                            List<Period> periods = organization.getPeriods();
                            dos.writeInt(periods.size());
                            for (Period period : periods) {
                                dos.writeUTF(period.getStartDate().toString());
                                dos.writeUTF(period.getEndDate().toString());
                                dos.writeUTF(period.getTitle());
                                dos.writeUTF(period.getDescription());
                            }
                        }
                        break;
                }
            }
        }
    }
    @Override
    public Resume deserializeResume(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                resume.saveContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }
            try {
                while (true) {
                    SectionType sectionType = SectionType.valueOf(dis.readUTF());
                    resume.saveSection(sectionType, createSection(dis, sectionType));
                }
            } catch (EOFException e) {
            }
            return resume;
        }
    }
    private AbstractSection createSection(DataInputStream dis, SectionType sectionType) throws IOException {
        switch (sectionType) {
            case PERSONAL:
            case OBJECTIVE:
                return new SimpleSection(dis.readUTF());
            case ACHIEVEMENT:
            case QUALIFICATIONS:
                int sizeListSimpleSection = dis.readInt();
                ListSimpleSection listSimpleSection = new ListSimpleSection();
                for (int i = 0; i < sizeListSimpleSection; i++) {
                    listSimpleSection.addTextToList(dis.readUTF());
                }
                return listSimpleSection;
            case EDUCATION:
            case EXPERIENCE:
                OrganizationSection organizationSection = new OrganizationSection();
                int sizeOrganizations = dis.readInt();
                for (int i = 0; i < sizeOrganizations; i++) {
                    Organization organization = new Organization(dis.readUTF());
                    int sizePeriods = dis.readInt();
                    for (int j = 0; j < sizePeriods; j++) {
                        LocalDate StartDate = LocalDate.parse(dis.readUTF());
                        LocalDate EndDate = LocalDate.parse(dis.readUTF());
                        String title = dis.readUTF();
                        String description = dis.readUTF();
                        Period period = new Period(StartDate, EndDate, title, description);
                        organization.addToPeriods(period);
                    }
                    organizationSection.setOrganizations(organization);
                }
                return organizationSection;
        }
        throw new RuntimeException("No such section in resume");
    }
}
