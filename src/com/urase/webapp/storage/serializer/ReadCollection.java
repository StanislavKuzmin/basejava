package com.urase.webapp.storage.serializer;

import java.io.IOException;
import java.util.List;

public interface ReadCollection<T> {
    List<T> read() throws IOException;
}
