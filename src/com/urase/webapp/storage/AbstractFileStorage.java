package com.urase.webapp.storage;

import com.urase.webapp.exception.StorageException;
import com.urase.webapp.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private File directory;

    protected AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
    }

    @Override
    protected File findSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected void updateResume(File file, Resume resume) {
        try {
            doWrite(resume, new BufferedOutputStream(Files.newOutputStream(file.toPath())));
        } catch (IOException e) {
            throw new StorageException("File write error", resume.getUuid(), e);
        }
    }

    @Override
    protected void deleteResume(File file) {
        if (!file.delete()) {
            throw new StorageException("File delete error", file.getName());
        }
    }

    @Override
    protected Resume getResume(File file) {
        try {
            return doRead(new BufferedInputStream(Files.newInputStream(file.toPath())));
        } catch (IOException e) {
            throw new StorageException("File read error", file.getName(), e);
        }
    }

    @Override
    protected void insertNewResume(Resume resume, File file) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new StorageException("Couldn't create file " + file.getAbsolutePath(), file.getName(), e);
        }
        updateResume(file, resume);
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected List<Resume> getListResume() {
        File[] filesResume = checkDirectory();
        List<Resume> resumes = new ArrayList<>(filesResume.length);
        for (File file : filesResume) {
            resumes.add(getResume(file));
        }
        return resumes;
    }

    @Override
    public void clear() {
        File[] filesResume = checkDirectory();
        for (File file : filesResume) {
            deleteResume(file);
        }
    }

    @Override
    public int size() {
        File[] filesArray = checkDirectory();
        return filesArray.length;
    }

    private File[] checkDirectory() {
        File[] filesResume = directory.listFiles();
        if (filesResume == null) {
            throw new StorageException("Directory read error", null);
        }
        return filesResume;
    }

    public abstract void doWrite(Resume resume, OutputStream os) throws IOException;

    public abstract Resume doRead(InputStream is) throws IOException;
}
