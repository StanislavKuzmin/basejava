package com.urase.webapp.serializer;

import com.urase.webapp.storage.AbstractStorageTest;

public class ObjectStreamStorageTest extends AbstractStorageTest {

    public ObjectStreamStorageTest() {
        super(new ObjectStreamStorage(STORAGE_DIR));
    }
}