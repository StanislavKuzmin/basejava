package com.urase.webapp.storage;

import com.urase.webapp.exception.StorageException;
import com.urase.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {

    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected void updateResume(int indexResume, Resume resume) {
        storage[indexResume] = resume;
    }

    @Override
    protected void deleteResume(int indexResume) {
        storage[indexResume] = null;
        System.arraycopy(storage, indexResume + 1, storage, indexResume, size - indexResume - 1);
        size--;
    }

    @Override
    protected Resume getResumeByIndex(int indexResume) {
        return storage[indexResume];
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    protected void insertNewResume(Resume resume, int indexResume) {
        if (isStorageFull()) {
            throw new StorageException("Storage overflow", resume.getUuid());
        }
        addToArray(resume, indexResume);
        size++;
    }

    private boolean isStorageFull() {
        if (size == storage.length) {
            return true;
        }
        return false;
    }

    protected abstract void addToArray(Resume resume, int indexResume);
}
