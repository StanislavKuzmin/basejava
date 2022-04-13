package com.urase.webapp.storage;

import com.urase.webapp.exception.ExistStorageException;
import com.urase.webapp.exception.NotExistStorageException;
import com.urase.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    private static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);
    private Resume foundResume;

    public final void update(Resume resume) {
        crudStorage(NameOfOperation.UPDATE, resume);
    }

    public final void delete(String uuid) {
        crudStorage(NameOfOperation.DELETE, new Resume(uuid, null));
    }

    public final Resume get(String uuid) {
        crudStorage(NameOfOperation.GET, new Resume(uuid, null));
        return foundResume;
    }

    public final void save(Resume resume) {
        crudStorage(NameOfOperation.SAVE, resume);
    }

    public final List<Resume> getAllSorted() {
        List<Resume> sortStorage = getListResume();
        sortStorage.sort(RESUME_COMPARATOR);
        return sortStorage;
    }

    private void crudStorage(NameOfOperation nameOfOperation, Resume resume) {
        Object searchKey = findSearchKey(resume.getUuid());
        if (isExist(searchKey)) {
            switch (nameOfOperation) {
                case UPDATE:
                    updateResume(searchKey, resume);
                    break;
                case DELETE:
                    deleteResume(searchKey);
                    break;
                case GET:
                    foundResume = getResume(searchKey);
                    break;
                case SAVE:
                    throw new ExistStorageException(resume.getUuid());
            }
        } else if (nameOfOperation.equals(nameOfOperation.SAVE)) {
            insertNewResume(resume, searchKey);
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    protected abstract Object findSearchKey(String uuid);

    protected abstract void updateResume(Object searchKey, Resume resume);

    protected abstract void deleteResume(Object searchKey);

    protected abstract Resume getResume(Object searchKey);

    protected abstract void insertNewResume(Resume resume, Object searchKey);

    protected abstract boolean isExist(Object searchKey);

    protected abstract List<Resume> getListResume();
}
