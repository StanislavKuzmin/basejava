package com.urase.webapp.storage;

import com.urase.webapp.exception.StorageException;
import com.urase.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    protected void updateResume(Object index, Resume resume) {
        storage[(int) index] = resume;
    }

    @Override
    protected void deleteResume(Object searchKey) {
        int index = (int) searchKey;
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
        size--;
    }

    @Override
    protected Resume getResume(Object index) {
        return storage[(int) index];
    }

    @Override
    protected List<Resume> getListResume() {
        return new ArrayList<>(Arrays.asList(storage).subList(0, size));
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    protected void insertNewResume(Resume resume, Object index) {
        if (size == storage.length) {
            throw new StorageException("Storage overflow", resume.getUuid());
        }
        addToArray(resume, (int) index);
        size++;
    }

    @Override
    protected boolean isExist(Object index) {
        return (int) index >= 0;
    }

    protected abstract void addToArray(Resume resume, int index);
}
