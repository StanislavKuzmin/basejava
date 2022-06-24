package com.urase.webapp.storage;

import com.urase.webapp.storage.serializer.XmlStreamSerializer;

public class XmlPathStorageTest extends AbstractStorageTest {

    public XmlPathStorageTest() {
        super(new PathStorage(DIR, new XmlStreamSerializer()));
    }
}