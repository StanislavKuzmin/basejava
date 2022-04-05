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
    protected void updateResume(Object searchKey, Resume resume) {
        storage[(int) searchKey] = resume;
    }

    @Override
    protected void deleteResume(Object searchKey) {
        storage[(int) searchKey] = null;
        System.arraycopy(storage, (int) searchKey + 1, storage, (int) searchKey, size - (int) searchKey - 1);
        size--;
    }

    @Override
    protected Resume getResumeByKey(Object searchKey) {
        return storage[(int) searchKey];
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
    protected void insertNewResume(Resume resume, Object searchKey) {
        if (size == storage.length) {
            throw new StorageException("Storage overflow", resume.getUuid());
        }
        addToArray(resume, (int) searchKey);
        size++;
    }

    @Override
    protected boolean isResumeInStorage(Object searchKey) {
        if ((int) searchKey >= 0) {
            return true;
        }
        return false;
    }

    protected abstract void addToArray(Resume resume, int indexResume);
}
