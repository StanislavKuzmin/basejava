package com.urase.webapp.storage;

import com.urase.webapp.model.Resume;

import java.util.*;

public class MapUuidStorage extends AbstractStorage {

    private Map<String, Resume> storage = new HashMap<>();

    @Override
    protected Object findSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected void updateResume(Object key, Resume resume) {
        storage.put((String) key, resume);
    }

    @Override
    protected void deleteResume(Object key) {
        storage.remove((String) key);
    }

    @Override
    protected Resume getResume(Object key) {
        return storage.get((String) key);
    }

    @Override
    protected void insertNewResume(Resume resume, Object key) {
        storage.put((String) key, resume);
    }

    @Override
    protected boolean isExist(Object key) {
        return storage.containsKey((String) key);
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
