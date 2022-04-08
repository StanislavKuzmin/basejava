package com.urase.webapp.storage;

import com.urase.webapp.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void addToArray(Resume resume, int index) {
        storage[size] = resume;
    }

    @Override
    protected Object findSearchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
