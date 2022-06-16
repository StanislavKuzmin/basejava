package com.urase.webapp.storage.serializer;

import com.urase.webapp.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface SerializationStrategy {

    void serializeResume(Resume resume, OutputStream os) throws IOException;

    Resume deserializeResume(InputStream is) throws IOException;
}
