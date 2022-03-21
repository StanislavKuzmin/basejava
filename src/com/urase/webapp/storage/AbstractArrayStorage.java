package com.urase.webapp.storage;

import com.urase.webapp.model.Resume;

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
            System.out.println("Array of resumes is full, can't save resume");
        } else if (indexResume >= 0) {
            System.out.println("Can't save resume with uuid: " + resume.getUuid() + ", because it's already in the base");
        } else {
            insertNewResume(resume, indexResume);
        }
    }

    public final void update(Resume resume) {
        int indexResume = findIndexResume(resume.getUuid());
        if (indexResume >= 0) {
            storage[indexResume] = resume;
        } else {
            System.out.println("Can't update resume, because there is no such resume with uuid: " + resume.getUuid());
        }
    }

    public final void delete(String uuid) {
        int indexResume = findIndexResume(uuid);
        if (indexResume >= 0) {
            storage[indexResume] = null;
            System.arraycopy(storage, indexResume + 1, storage, indexResume, size - indexResume - 1);
            size--;
        } else {
            System.out.println("Can't delete resume, because there is no such resume with uuid: " + uuid);
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
        System.out.println("Can't get resume, because there is no such resume with uuid: " + uuid);
        return null;
    }

    protected abstract void insertNewResume(Resume resume, int indexResume);

    protected abstract int findIndexResume(String uuid);
}
