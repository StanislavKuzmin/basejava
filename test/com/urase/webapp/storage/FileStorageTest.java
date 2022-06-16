package com.urase.webapp.storage;

import com.urase.webapp.storage.serializer.ObjectStreamStrategy;

public class FileStorageTest extends AbstractStorageTest {

    public FileStorageTest() {
        super(new FileStorage(STORAGE_DIR, new ObjectStreamStrategy()));
    }
}