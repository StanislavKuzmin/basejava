package com.urase.webapp.storage;

import com.urase.webapp.exception.StorageException;
import com.urase.webapp.model.Resume;

import java.io.*;

public class StreamFileStrategy implements SerializationStrategy<File> {

    @Override
    public void serializeResume(Resume resume, File file) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
            oos.writeObject(resume);
        }
    }

    @Override
    public Resume deserializeResume(File file) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
            return (Resume) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new StorageException("Error read resume", null, e);
        }
    }
}
