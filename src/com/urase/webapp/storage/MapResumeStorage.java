package com.urase.webapp.storage;

import com.urase.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage {

    private Map<Resume, Resume> storage = new HashMap<>();

    @Override
    protected Object findSearchKey(String uuid) {
        return new Resume(uuid);
    }

    @Override
    protected void updateResume(Object key, Resume resume) {
        storage.put((Resume) key, resume);
    }

    @Override
    protected void deleteResume(Object key) {
        storage.remove((Resume) key);
    }

    @Override
    protected Resume getResume(Object key) {
        return storage.get((Resume) key);
    }

    @Override
    protected void insertNewResume(Resume resume, Object key) {
        storage.put((Resume) key, resume);
    }

    @Override
    protected boolean isExist(Object key) {
        return storage.containsKey((Resume) key);
    }

    @Override
    protected void addToList(List<Resume> sortStorage) {
        sortStorage.addAll(new ArrayList<>(storage.values()));
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }
}
