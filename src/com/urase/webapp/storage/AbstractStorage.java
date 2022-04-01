package com.urase.webapp.storage;

import com.urase.webapp.exception.ExistStorageException;
import com.urase.webapp.exception.NotExistStorageException;
import com.urase.webapp.exception.StorageException;
import com.urase.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public final void update(Resume resume) {
        int indexResume = findIndexResume(resume.getUuid());
        if (indexResume >= 0) {
            updateOneResume(indexResume, resume);
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    public final void delete(String uuid) {
        int indexResume = findIndexResume(uuid);
        if (indexResume >= 0) {
            deleteOneResume(indexResume);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    public final Resume get(String uuid) {
        int indexResume = findIndexResume(uuid);
        if (indexResume >= 0) {
            return getResumeByIndex(indexResume);
        }
        throw new NotExistStorageException(uuid);
    }

    public final void save(Resume resume) {
        int indexResume = findIndexResume(resume.getUuid());
        if (isStorageFull()) {
            throw new StorageException("Storage overflow", resume.getUuid());
        }
        if (indexResume >= 0) {
            throw new ExistStorageException(resume.getUuid());
        }
        insertNewResume(resume, indexResume);
    }

    protected abstract int findIndexResume(String uuid);

    protected abstract void updateOneResume(int indexResume, Resume resume);

    protected abstract void deleteOneResume(int indexResume);

    protected abstract Resume getResumeByIndex(int indexResume);

    protected abstract void insertNewResume(Resume resume, int indexResume);

    protected abstract boolean isStorageFull();
}