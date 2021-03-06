package com.urase.webapp.storage.serializer;

import com.urase.webapp.exception.StorageException;
import com.urase.webapp.model.Resume;

import java.io.*;

public class ObjectStreamStrategy implements SerializationStrategy {
    @Override
    public void serializeResume(Resume resume, OutputStream os) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(os)) {
            oos.writeObject(resume);
        }
    }

    @Override
    public Resume deserializeResume(InputStream is) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(is)) {
            return (Resume) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new StorageException("Error read resume", null, e);
        }
    }
}
