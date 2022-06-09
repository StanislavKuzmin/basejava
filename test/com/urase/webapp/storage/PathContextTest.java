package com.urase.webapp.storage;

public class PathContextTest extends AbstractStorageTest {

    public PathContextTest() {
        super(new PathContext(DIR, new StreamPathStrategy()));
    }
}