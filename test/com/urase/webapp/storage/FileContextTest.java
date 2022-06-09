package com.urase.webapp.storage;

public class FileContextTest extends AbstractStorageTest {

    public FileContextTest() {
        super(new FileContext(STORAGE_DIR, new StreamFileStrategy()));
    }
}