package com.urase.webapp.storage.serializer;

import com.urase.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.Collection;

public class DataStreamSerializer implements SerializationStrategy {
    @Override
    public void serializeResume(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            writeWithException(resume.getContacts().entrySet(), dos, writer -> {
                dos.writeUTF(writer.getKey().name());
                dos.writeUTF(writer.getValue());
            });
            writeWithException(resume.getSections().entrySet(), dos, writer -> {
                SectionType sectionType = writer.getKey();
                AbstractSection abstractSection = writer.getValue();
                dos.writeUTF(sectionType.name());
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        dos.writeUTF(abstractSection.toString());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        ListSimpleSection listSimpleSection = (ListSimpleSection) abstractSection;
                        writeWithException(listSimpleSection.getItems(), dos, dos::writeUTF);
                        break;
                    case EDUCATION:
                    case EXPERIENCE:
                        OrganizationSection organizationSection = (OrganizationSection) abstractSection;
                        writeWithException(organizationSection.getOrganizations(), dos, writerOrg -> {
                            dos.writeUTF(writerOrg.getLinkEmployer());
                            writeWithException(writerOrg.getPeriods(), dos, writerPer -> {
                                dos.writeUTF(writerPer.getStartDate().toString());
                                dos.writeUTF(writerPer.getEndDate().toString());
                                dos.writeUTF(writerPer.getTitle());
                                dos.writeUTF(writerPer.getDescription());
                            });
                        });
                        break;
                }
            });
        }
    }

    @Override
    public Resume deserializeResume(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            readWithException(dis, () -> resume.saveContact(ContactType.valueOf(dis.readUTF()), dis.readUTF()));
            readWithException(dis, () -> {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                AbstractSection abstractSection = null;
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        abstractSection = readListWithException(dis, () -> new SimpleSection(dis.readUTF()));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        ListSimpleSection listSimpleSection = new ListSimpleSection();
                        readWithException(dis, () -> listSimpleSection.addTextToList(dis.readUTF()));
                        abstractSection = listSimpleSection;
                        break;
                    case EDUCATION:
                    case EXPERIENCE:
                        OrganizationSection organizationSection = new OrganizationSection();
                        readWithException(dis, () -> {
                            Organization organization = new Organization(dis.readUTF());
                            readWithException(dis, () -> organization.addToPeriods(new Period(LocalDate.parse(dis.readUTF()),
                                    LocalDate.parse(dis.readUTF()), dis.readUTF(), dis.readUTF())));
                            organizationSection.setOrganizations(organization);
                        });
                        abstractSection = organizationSection;
                        break;
                }
                resume.saveSection(sectionType, abstractSection);
            });
            return resume;
        }
    }

    private <T> void writeWithException(Collection<T> collection, DataOutputStream dos, WriteCollection<T> write) throws IOException {
        dos.writeInt(collection.size());
        for (T element : collection) {
            write.write(element);
        }
    }

    private void readWithException(DataInputStream dis, ReadItems read) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            read.read();
        }
    }
    private <T> T readListWithException(DataInputStream dis, ReadList<T> read) throws IOException{
        return read.read();
    }
}


