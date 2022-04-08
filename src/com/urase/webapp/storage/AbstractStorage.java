package com.urase.webapp.storage;

import com.urase.webapp.exception.ExistStorageException;
import com.urase.webapp.exception.NotExistStorageException;
import com.urase.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {
    private static final String UPDATE = "update";
    private static final String DELETE = "delete";
    private static final String GET = "get";
    private static final String SAVE = "save";
    private Resume foundResume;

    public final void update(Resume resume) {
        crudStorage(UPDATE, resume);
    }

    public final void delete(String uuid) {
        crudStorage(DELETE, new Resume(uuid));
    }

    public final Resume get(String uuid) {
        crudStorage(GET, new Resume(uuid));
        return foundResume;
    }

    public final void save(Resume resume) {
        crudStorage(SAVE, resume);
    }

    private void crudStorage(String nameOfOperation, Resume resume) {
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
        } else if (nameOfOperation.equals(SAVE)) {
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

}
