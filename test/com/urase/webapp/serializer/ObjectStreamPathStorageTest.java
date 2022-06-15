package com.urase.webapp.serializer;

import com.urase.webapp.storage.AbstractStorageTest;

public class ObjectStreamPathStorageTest extends AbstractStorageTest {

    public ObjectStreamPathStorageTest() {
        super(new ObjectStreamPathStorage(DIR));
    }
}