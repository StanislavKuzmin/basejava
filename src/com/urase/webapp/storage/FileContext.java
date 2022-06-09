package com.urase.webapp.storage;

import com.urase.webapp.model.Resume;

import java.io.File;
import java.io.IOException;

public class FileContext extends AbstractFileStorage {

    SerializationStrategy<File> serializationStrategy;

    protected FileContext(File directory, SerializationStrategy<File> serializationStrategy) {
        super(directory);
        this.serializationStrategy = serializationStrategy;
    }

    public void setSerializationStrategy(SerializationStrategy<File> serializationStrategy) {
        this.serializationStrategy = serializationStrategy;
    }

    @Override
    public void doWrite(Resume resume, File file) throws IOException {
        serializationStrategy.serializeResume(resume, file);
    }

    @Override
    public Resume doRead(File file) throws IOException {
        return serializationStrategy.deserializeResume(file);
    }
}
