package com.urase.webapp.storage;

import com.urase.webapp.model.Resume;

import java.util.*;

public class MapResumeStorage extends AbstractStorage {

    private Map<String, Resume> storage = new HashMap<>();

    @Override
    protected Object findSearchKey(String uuid) {
        if(storage.get(uuid) == null) {
            return new Resume(uuid, null);
        }
        return storage.get(uuid);
    }

    @Override
    protected void updateResume(Object searchKey, Resume resume) {
        Resume key = (Resume) searchKey;
        storage.put(key.getUuid(), resume);
    }

    @Override
    protected void deleteResume(Object searchKey) {
        Resume key = (Resume) searchKey;
        storage.remove(key.getUuid());
    }

    @Override
    protected Resume getResume(Object searchKey) {
        return (Resume) searchKey;
    }

    @Override
    protected void insertNewResume(Resume resume, Object searchKey) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        Resume key = (Resume) searchKey;
        return storage.containsKey(key.getUuid());
    }

    @Override
    protected List<Resume> getListResume() {
        return new ArrayList<>(storage.values());
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
