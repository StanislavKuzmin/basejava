package com.urase.webapp.storage.serializer;

import com.urase.webapp.exception.StorageException;
import com.urase.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DataStreamSerializer implements SerializationStrategy {

    private static final String EMPTY_SECTION = "";
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
                            writeEmptyString(dos, writerOrg.getLinkEmployer());
                            writeWithException(writerOrg.getPeriods(), dos, writerPer -> {
                                dos.writeUTF(writerPer.getStartDate().toString());
                                dos.writeUTF(writerPer.getEndDate().toString());
                                dos.writeUTF(writerPer.getTitle());
                                writeEmptyString(dos, writerPer.getDescription());
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
                resume.saveSection(sectionType, readSectionWithException(dis, () -> {
                    switch (sectionType) {
                        case PERSONAL:
                        case OBJECTIVE:
                            return readSectionWithException(dis, () -> new SimpleSection(dis.readUTF()));
                        case ACHIEVEMENT:
                        case QUALIFICATIONS:
                            return readSectionWithException(dis, () -> {
                                ListSimpleSection listSimpleSection = new ListSimpleSection();
                                readListWithException(dis, dis::readUTF).forEach(listSimpleSection::addTextToList);
                                return listSimpleSection;
                            });
                        case EDUCATION:
                        case EXPERIENCE:
                            return readSectionWithException(dis, () -> {
                                OrganizationSection organizationSection = new OrganizationSection();
                                readListWithException(dis, () -> {
                                    Organization organization = new Organization(readEmptyString(dis));
                                    readListWithException(dis, () -> new Period(LocalDate.parse(dis.readUTF()),
                                            LocalDate.parse(dis.readUTF()), dis.readUTF(), readEmptyString(dis))).
                                            forEach(organization::addToPeriods);
                                    return organization;
                                }).forEach(organizationSection::setOrganizations);
                                return organizationSection;
                            });
                        default:
                            throw new StorageException("Unknown type of section in file", null);
                    }
                }));
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

    private void readWithException(DataInputStream dis, ReadItems reader) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            reader.read();
        }
    }
    private <T> T readSectionWithException(DataInputStream dis, ReadList<T> reader) throws IOException{
        return reader.read();
    }

    private <T> List<T> readListWithException(DataInputStream dis, ReadList<T> reader) throws IOException{
        List<T> listRead = new ArrayList<>();
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            listRead.add(reader.read());
        }
        return listRead;
    }

    private void writeEmptyString(DataOutputStream dos, String text) throws IOException {
        if(text == null) {
            dos.writeUTF(EMPTY_SECTION);
        } else {
            dos.writeUTF(text);
        }
    }

    private String readEmptyString(DataInputStream dis) throws IOException {
        String text = dis.readUTF();
        if (text.equals(EMPTY_SECTION)) {
            return null;
        } else {
            return text;
        }
    }
}


