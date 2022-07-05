package com.urase.webapp.storage.serializer;

import java.io.IOException;

public interface ReadList<T> {
    T read() throws IOException;
}
