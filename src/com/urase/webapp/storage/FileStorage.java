package com.urase.webapp.storage;

import com.urase.webapp.exception.StorageException;
import com.urase.webapp.model.Resume;
import com.urase.webapp.storage.serializer.SerializationStrategy;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class FileStorage extends AbstractStorage<File> {
    private SerializationStrategy serializationStrategy;
    private File directory;

    public FileStorage(File directory, SerializationStrategy serializationStrategy) {
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.serializationStrategy = serializationStrategy;
        this.directory = directory;
    }


    @Override
    protected File findSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected void updateResume(File file, Resume resume) {
        try {
            serializationStrategy.serializeResume(resume, new BufferedOutputStream(Files.newOutputStream(file.toPath())));
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
            return serializationStrategy.deserializeResume(new BufferedInputStream(Files.newInputStream(file.toPath())));
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
        for (File file : checkDirectory()) {
            deleteResume(file);
        }
    }

    @Override
    public int size() {
        return checkDirectory().length;
    }

    private File[] checkDirectory() {
        File[] filesResume = directory.listFiles();
        if (filesResume == null) {
            throw new StorageException("Directory read error", null);
        }
        return filesResume;
    }
}
