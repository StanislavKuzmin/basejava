package com.urase.webapp.storage;

import com.urase.webapp.exception.StorageException;
import com.urase.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {

    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected void updateResume(Integer index, Resume resume) {
        storage[index] = resume;
    }

    @Override
    protected void deleteResume(Integer searchKey) {
        System.arraycopy(storage, searchKey + 1, storage, searchKey, size - searchKey - 1);
        size--;
    }

    @Override
    protected Resume getResume(Integer index) {
        return storage[index];
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
    protected void insertNewResume(Resume resume, Integer index) {
        if (size == storage.length) {
            throw new StorageException("Storage overflow", resume.getUuid());
        }
        addToArray(resume, (int) index);
        size++;
    }

    @Override
    protected boolean isExist(Integer index) {
        return index >= 0;
    }

    protected abstract void addToArray(Resume resume, int index);
}
