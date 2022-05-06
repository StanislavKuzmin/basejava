package com.urase.webapp.storage;

import com.urase.webapp.exception.StorageException;
import com.urase.webapp.model.Resume;
import org.junit.Test;

import static org.junit.Assert.fail;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {

    private static final int STORAGE_LIMIT = 10000;
    private static final String FULL_NAME = "unknown person";
    protected AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void save_shouldThrowException_whenArrayIsFull() {
        storage.clear();
        try {
            for (int i = 0; i < STORAGE_LIMIT; i++) {
                storage.save(new Resume(FULL_NAME));
            }
        } catch (StorageException e) {
            fail("Array overflow ahead of time");
        }
        storage.save(new Resume(FULL_NAME));
    }

}