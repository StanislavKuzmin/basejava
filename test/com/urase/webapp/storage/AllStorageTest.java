package com.urase.webapp.storage;

import com.urase.webapp.serializer.FileStorageTest;
import com.urase.webapp.serializer.ObjectStreamPathStorageTest;
import com.urase.webapp.serializer.ObjectStreamStorageTest;
import com.urase.webapp.serializer.PathStorageTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ArrayStorageTest.class,
        SortedArrayStorageTest.class,
        ListStorageTest.class,
        MapUuidStorageTest.class,
        MapResumeStorageTest.class,
        ObjectStreamStorageTest.class,
        ObjectStreamPathStorageTest.class,
        FileStorageTest.class,
        PathStorageTest.class
})
public class AllStorageTest {
}