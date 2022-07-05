package com.urase.webapp.storage.serializer;

import java.io.IOException;

public interface WriteCollection<T> {
    void write(T t) throws IOException;
}
