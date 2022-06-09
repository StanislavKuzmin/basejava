package com.urase.webapp.storage;

import com.urase.webapp.model.Resume;

import java.io.IOException;

public interface SerializationStrategy<T> {

    void serializeResume(Resume resume, T file) throws IOException;

    Resume deserializeResume(T file) throws IOException;
}
