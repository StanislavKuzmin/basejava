package com.urase.webapp.storage;

import com.urase.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    private Map<Integer, Resume> storage = new HashMap<>();
    private int keyMap;

    @Override
    protected int findIndexResume(String uuid) {
        for(Map.Entry<Integer, Resume> entry: storage.entrySet()) {
            if (entry.getValue().getUuid().equals(uuid)) {
                return entry.getKey();
            }
        }
        return -1;
    }

    @Override
    protected void updateResume(int indexResume, Resume resume) {
        int keyResume = findIndexResume(resume.getUuid());
        storage.put(keyResume, resume);
    }

    @Override
    protected void deleteResume(int indexResume) {
        storage.remove(indexResume);
    }

    @Override
    protected Resume getResumeByIndex(int indexResume) {
        return storage.get(indexResume);
    }

    @Override
    protected void insertNewResume(Resume resume, int indexResume) {
        storage.put(keyMap, resume);
        keyMap++;
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
