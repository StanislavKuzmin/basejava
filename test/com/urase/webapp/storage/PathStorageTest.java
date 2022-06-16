package com.urase.webapp.storage;

import com.urase.webapp.storage.serializer.ObjectStreamStrategy;

public class PathStorageTest extends AbstractStorageTest {

    public PathStorageTest() {
        super(new PathStorage(DIR, new ObjectStreamStrategy()));
    }
}