package com.urase.webapp.storage;

import com.urase.webapp.exception.ExistStorageException;
import com.urase.webapp.exception.NotExistStorageException;
import com.urase.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage<SK> implements Storage {

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
        SK searchKey = findSearchKey(resume.getUuid());
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
        } else if (nameOfOperation.equals(NameOfOperation.SAVE)) {
            insertNewResume(resume, searchKey);
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    protected abstract SK findSearchKey(String uuid);

    protected abstract void updateResume(SK searchKey, Resume resume);

    protected abstract void deleteResume(SK searchKey);

    protected abstract Resume getResume(SK searchKey);

    protected abstract void insertNewResume(Resume resume, SK searchKey);

    protected abstract boolean isExist(SK searchKey);

    protected abstract List<Resume> getListResume();
}
