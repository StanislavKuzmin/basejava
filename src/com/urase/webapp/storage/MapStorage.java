package com.urase.webapp.storage;

import com.urase.webapp.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage {

    private Map<String, Resume> storage = new LinkedHashMap<>();

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
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        List<Resume> resumesList = new ArrayList<>(storage.values());
        return resumesList.toArray(new Resume[storage.size()]);
    }

    @Override
    public int size() {
        return storage.size();
    }
}
