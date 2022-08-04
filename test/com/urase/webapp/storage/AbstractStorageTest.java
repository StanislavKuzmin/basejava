package com.urase.webapp.storage;

import com.urase.webapp.Config;
import com.urase.webapp.ResumeTestData;
import com.urase.webapp.exception.ExistStorageException;
import com.urase.webapp.exception.NotExistStorageException;
import com.urase.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public abstract class AbstractStorageTest {

    protected static final File STORAGE_DIR = Config.get().getStorageDir();

    protected Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String FULL_NAME_1 = "Obi-Wan Kenobi";
    private static final String FULL_NAME_2 = "Dart Weider";
    private static final String FULL_NAME_3 = "Anakin Skywalker";
    private static final int EXPECTED_SIZE = 3;
    private static final int EMPTY_SIZE = 0;
    private static final int AFTER_DELETE_SIZE = 2;
    private static final ResumeTestData resumeTestData = new ResumeTestData();
    private static final List<Resume> RESUME_STORAGE = new ArrayList<>(Arrays.asList(resumeTestData.createResume(UUID_3, FULL_NAME_3),
            resumeTestData.createResume(UUID_2, FULL_NAME_2), resumeTestData.createResume(UUID_1, FULL_NAME_1)));
    private static final List<Resume> ONE_DELETE_RESUME_STORAGE = new ArrayList<>(Arrays.asList(resumeTestData.createResume(UUID_3, FULL_NAME_3),
            resumeTestData.createResume(UUID_2, FULL_NAME_2)));
    private static final List<Resume> EMPTY_STORAGE = new ArrayList<>();
    private static final Resume RESUME = resumeTestData.createResume(UUID_1, FULL_NAME_1);

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(resumeTestData.createResume(UUID_1, FULL_NAME_1));
        storage.save(resumeTestData.createResume(UUID_2, FULL_NAME_2));
        storage.save(resumeTestData.createResume(UUID_3, FULL_NAME_3));
    }

    @Test
    public void size_shouldReturnNumberOfResumes() {
        assertEquals(EXPECTED_SIZE, storage.size());
    }

    @Test
    public void clear_shouldResetToNullAllResumes() {
        storage.clear();
        assertEquals(EMPTY_STORAGE, storage.getAllSorted());
        assertEquals(EMPTY_SIZE, storage.size());
    }

    @Test
    public void update_shouldUpdateDefiniteResume() {
        storage.update(RESUME);
        assertEquals(RESUME, storage.get(RESUME.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void update_shouldThrowNotExistStorageException_whenResumeIsntInArray() {
        storage.update(new Resume("dummy", null));
    }

    @Test
    public void getAll_shouldReturnAllResumes() {
        assertEquals(RESUME_STORAGE, storage.getAllSorted());
    }

    @Test
    public void save_shouldSaveResumesInArray() {
        assertEquals(RESUME_STORAGE, storage.getAllSorted());
        assertEquals(EXPECTED_SIZE, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void save_shouldThrowExistStorageException_whenResumeIsAlreadyInArray() {
        storage.save(RESUME);
    }

    @Test
    public void delete_shouldDeleteDefiniteResume() {
        storage.delete(UUID_1);
        assertEquals(ONE_DELETE_RESUME_STORAGE, storage.getAllSorted());
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