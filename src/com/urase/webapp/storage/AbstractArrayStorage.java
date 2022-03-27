package com.urase.webapp.storage;

import com.urase.webapp.model.Resume;
import com.urase.webapp.exception.ExistStorageException;
import com.urase.webapp.exception.NotExistStorageException;
import com.urase.webapp.exception.StorageException;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {

    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public final void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public final void save(Resume resume) {
        int indexResume = findIndexResume(resume.getUuid());
        if (size == storage.length) {
            throw new StorageException("Storage overflow", resume.getUuid());
        } else if (indexResume >= 0) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            insertNewResume(resume, indexResume);
            size++;
        }
    }

    public final void update(Resume resume) {
        int indexResume = findIndexResume(resume.getUuid());
        if (indexResume >= 0) {
            storage[indexResume] = resume;
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    public final void delete(String uuid) {
        int indexResume = findIndexResume(uuid);
        if (indexResume >= 0) {
            storage[indexResume] = null;
            System.arraycopy(storage, indexResume + 1, storage, indexResume, size - indexResume - 1);
            size--;
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    public final Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public final int size() {
        return size;
    }

    public final Resume get(String uuid) {
        int indexResume = findIndexResume(uuid);
        if (indexResume >= 0) {
            return storage[indexResume];
        }
        throw new NotExistStorageException(uuid);
    }

    protected abstract void insertNewResume(Resume resume, int indexResume);

    protected abstract int findIndexResume(String uuid);
}
