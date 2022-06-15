package com.urase.webapp.serializer;

import com.urase.webapp.storage.AbstractStorageTest;

public class FileStorageTest extends AbstractStorageTest {

    public FileStorageTest() {
        super(new FileStorage(STORAGE_DIR, new ObjectStreamStrategy()));
    }
}