package com.urase.webapp.storage;

import com.urase.webapp.model.Resume;
import com.urase.webapp.exception.ExistStorageException;
import com.urase.webapp.exception.NotExistStorageException;
import com.urase.webapp.exception.StorageException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public abstract class AbstractArrayStorageTest {

    private Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final int EXAMPLE_SIZE = 3;
    private static final Resume[] RESUME_STORAGE = new Resume[]{new Resume(UUID_1), new Resume(UUID_2), new Resume(UUID_3)};
    private static final Resume[] ONE_DELETE_RESUME_STORAGE = new Resume[]{new Resume(UUID_2), new Resume(UUID_3)};
    private static final Resume[] EMPTY_STORAGE = new Resume[0];
    private static final Resume RESUME = new Resume(UUID_1);
    private static final int STORAGE_LIMIT = 10000;

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void size_shouldReturnNumberOfResumes_whenItCalls() {
        Assert.assertEquals(EXAMPLE_SIZE, storage.size());
    }

    @Test
    public void clear_shouldResetToNullAllResumes_whenItCalls() {
        storage.clear();
        Assert.assertArrayEquals(EMPTY_STORAGE, storage.getAll());
    }


    @Test
    public void update_shouldUpdateDefiniteResume_whenResumeInInput() {
        storage.update(RESUME);
        Assert.assertEquals(RESUME, storage.get(RESUME.getUuid()));
    }

    @Test
    public void getAll_shouldReturnAllResumes_whenItCalls() {
        Assert.assertArrayEquals(RESUME_STORAGE, storage.getAll());
    }

    @Test(expected = StorageException.class)
    public void save_shouldSaveNewResumesWithoutExceptionAndThrowsStorageException_whenArrayIsFull() {
        storage.clear();
        try {
            for (int i = 0; i < STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            Assert.fail("Array overflow ahead of time");
        }
        storage.save(new Resume());
    }

    @Test
    public void delete_shouldDeleteDefiniteResume_whenItsUuidInInputs() {
        storage.delete(UUID_1);
        Assert.assertArrayEquals(ONE_DELETE_RESUME_STORAGE, storage.getAll());
    }

    @Test
    public void get_shouldGetDefiniteResume_whenItUuidInInput() {
        Assert.assertEquals(RESUME, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void get_shouldThrowNotExistStorageException_whenResumeIsntInArray() {
        storage.get("dummy");
    }

    @Test(expected = NotExistStorageException.class)
    public void delete_shouldThrowNotExistStorageException_whenResumeIsntInArray() {
        storage.delete("dummy");
    }

    @Test(expected = NotExistStorageException.class)
    public void update_shouldThrowNotExistStorageException_whenResumeIsntInArray() {
        storage.update(new Resume("dummy"));
    }

    @Test(expected = ExistStorageException.class)
    public void save_shouldThrowExistStorageException_whenResumeIsAlreadyInArray() {
        storage.save(RESUME);
    }
}