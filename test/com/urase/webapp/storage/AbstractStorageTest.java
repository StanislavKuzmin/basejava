package com.urase.webapp.storage;

import com.urase.webapp.exception.ExistStorageException;
import com.urase.webapp.exception.NotExistStorageException;
import com.urase.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public abstract class AbstractStorageTest {

    protected Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final int EXPECTED_SIZE = 3;
    private static final int EMPTY_SIZE = 0;
    private static final int AFTER_DELETE_SIZE = 2;
    private static final Resume[] RESUME_STORAGE = new Resume[]{new Resume(UUID_1), new Resume(UUID_2), new Resume(UUID_3)};
    private static final Resume[] ONE_DELETE_RESUME_STORAGE = new Resume[]{new Resume(UUID_2), new Resume(UUID_3)};
    private static final Resume[] EMPTY_STORAGE = new Resume[0];
    private static final Resume RESUME = new Resume(UUID_1);

    protected AbstractStorageTest(Storage storage) { this.storage = storage; }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void size_shouldReturnNumberOfResumes() {
        assertEquals(EXPECTED_SIZE, storage.size());
    }

    @Test
    public void clear_shouldResetToNullAllResumes() {
        storage.clear();
        assertArrayEquals(EMPTY_STORAGE, storage.getAll());
        assertEquals(EMPTY_SIZE, storage.size());
    }


    @Test
    public void update_shouldUpdateDefiniteResume() {
        storage.update(RESUME);
        assertSame(RESUME, storage.get(RESUME.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void update_shouldThrowNotExistStorageException_whenResumeIsntInArray() {
        storage.update(new Resume("dummy"));
    }

    @Test
    public void getAll_shouldReturnAllResumes() {
        assertArrayEquals(RESUME_STORAGE, storage.getAll());
    }

    @Test
    public void save_shouldSaveResumesInArray() {
        assertArrayEquals(RESUME_STORAGE, storage.getAll());
        assertEquals(EXPECTED_SIZE, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void save_shouldThrowExistStorageException_whenResumeIsAlreadyInArray() {
        storage.save(RESUME);
    }

    @Test
    public void delete_shouldDeleteDefiniteResume() {
        storage.delete(UUID_1);
        assertArrayEquals(ONE_DELETE_RESUME_STORAGE, storage.getAll());
        assertEquals(AFTER_DELETE_SIZE, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void delete_shouldThrowNotExistStorageException_whenResumeIsntInArray() {
        storage.delete("dummy");
    }

    @Test
    public void get_shouldGetDefiniteResume() {
        assertEquals(RESUME, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void get_shouldThrowNotExistStorageException_whenResumeIsntInArray() {
        storage.get("dummy");
    }

}