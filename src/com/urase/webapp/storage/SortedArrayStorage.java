package com.urase.webapp.storage;

import com.urase.webapp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void addToArray(Resume resume, int index) {
        index = -index - 1;
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = resume;
    }

    @Override
    protected Integer findSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid, null);
        Comparator<Resume> comparatorUuid = Comparator.comparing(Resume::getUuid);
        return Arrays.binarySearch(storage, 0, size, searchKey, comparatorUuid);
    }
}
