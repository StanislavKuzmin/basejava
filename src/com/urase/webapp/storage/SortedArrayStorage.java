package com.urase.webapp.storage;

import com.urase.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void insertNewResume(Resume resume, int indexResume) {
        indexResume = -indexResume - 1;
        System.arraycopy(storage, indexResume, storage, indexResume + 1, size - indexResume);
        storage[indexResume] = resume;
    }

    @Override
    protected int findIndexResume(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
