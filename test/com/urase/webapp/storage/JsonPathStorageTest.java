package com.urase.webapp.storage;

import com.urase.webapp.storage.serializer.JsonStreamSerializer;

public class JsonPathStorageTest extends AbstractStorageTest {

    public JsonPathStorageTest() {
        super(new PathStorage(DIR, new JsonStreamSerializer()));
    }
}