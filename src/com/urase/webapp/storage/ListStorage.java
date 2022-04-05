package com.urase.webapp.storage;

import com.urase.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private List<Resume> storage = new ArrayList<>();

    @Override
    protected Object findSearchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void updateResume(Object searchKey, Resume resume) {
        storage.set((int) searchKey, resume);
    }

    @Override
    protected void deleteResume(Object searchKey) {
        storage.remove((int) searchKey);
    }

    @Override
    protected Resume getResumeByKey(Object searchKey) {
        return storage.get((int) searchKey);
    }

    @Override
    protected void insertNewResume(Resume resume, Object searchKey) {
        storage.add(resume);
    }

    @Override
    protected boolean isResumeInStorage(Object searchKey) {
        if ((Integer) searchKey >= 0) {
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[storage.size()]);
    }

    @Override
    public int size() {
        return storage.size();
    }
}
