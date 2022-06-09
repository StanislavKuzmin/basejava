package com.urase.webapp.storage;

import com.urase.webapp.model.Resume;

import java.io.IOException;
import java.nio.file.Path;

public class PathContext extends AbstractPathStorage {
    SerializationStrategy<Path> serializationStrategy;

    protected PathContext(String dir, SerializationStrategy<Path> serializationStrategy) {
        super(dir);
        this.serializationStrategy = serializationStrategy;
    }

    public void setSerializationStrategy(SerializationStrategy<Path> serializationStrategy) {
        this.serializationStrategy = serializationStrategy;
    }

    @Override
    public void doWrite(Resume resume, Path path) throws IOException {
        serializationStrategy.serializeResume(resume, path);
    }

    @Override
    public Resume doRead(Path path) throws IOException {
        return serializationStrategy.deserializeResume(path);
    }
}
