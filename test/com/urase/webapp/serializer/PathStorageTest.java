package com.urase.webapp.serializer;

import com.urase.webapp.storage.AbstractStorageTest;

public class PathStorageTest extends AbstractStorageTest {

    public PathStorageTest() {
        super(new PathStorage(DIR, new ObjectStreamStrategy()));
    }
}