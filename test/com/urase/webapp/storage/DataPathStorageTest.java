package com.urase.webapp.storage;

import com.urase.webapp.storage.serializer.DataStreamSerializer;

public class DataPathStorageTest extends AbstractStorageTest {

    public DataPathStorageTest() {
        super(new PathStorage(DIR, new DataStreamSerializer()));
    }
}