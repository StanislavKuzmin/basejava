package com.urase.webapp.storage;

import com.urase.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    List<Resume> storage = new ArrayList<>();

    @Override
    protected int findIndexResume(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void updateOneResume(int indexResume, Resume resume) {
        storage.set(indexResume, resume);
    }

    @Override
    protected void deleteOneResume(int indexResume) {
        storage.remove(indexResume);
    }

    @Override
    protected Resume getResumeByIndex(int indexResume) {
        return storage.get(indexResume);
    }

    @Override
    protected void insertNewResume(Resume resume, int indexResume) {
        storage.add(resume);
    }

    @Override
    protected boolean isStorageFull() {
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
