package com.urase.webapp.storage;

import com.urase.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

    Resume[] storage = new Resume[10000];
    int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        int indexResume = findIndexResume(resume.uuid);
        if (size == storage.length) {
            System.out.println("Array of resumes is full, can't save resume");
        } else if (indexResume >= 0) {
            System.out.println("Can't save resume with uuid: " + resume.uuid + ", because it's already in the base");
        } else {
            storage[size] = resume;
            size++;
        }
    }

    public Resume get(String uuid) {
        int indexResume = findIndexResume(uuid);
        if (indexResume >= 0) {
            return storage[indexResume];
        } else {
            System.out.println("Can't get resume, because there is no such resume with uuid: " + uuid);
            return null;
        }
    }

    public void delete(String uuid) {
        int indexResume = findIndexResume(uuid);
        if (indexResume >= 0) {
            storage[indexResume] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Can't delete resume, because there is no such resume with uuid: " + uuid);
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    public void update(Resume resume) {
        int indexResume = findIndexResume(resume.uuid);
        if (indexResume >= 0) {
            storage[indexResume] = resume;
        } else {
            System.out.println("Can't update resume, because there is no such resume with uuid: " + resume.uuid);
        }
    }

    private int findIndexResume(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
