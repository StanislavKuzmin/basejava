package com.urase.webapp.storage;

import com.urase.webapp.exception.StorageException;
import com.urase.webapp.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class StreamPathStrategy implements SerializationStrategy<Path> {

    @Override
    public void serializeResume(Resume resume, Path path) throws IOException{
        try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(Files.newOutputStream(path)))) {
            oos.writeObject(resume);
        }
    }

    @Override
    public Resume deserializeResume(Path path) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(Files.newInputStream(path)))) {
            return (Resume) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new StorageException("Error read resume", null, e);
        }
    }
}
