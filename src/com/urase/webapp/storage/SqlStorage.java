package com.urase.webapp.storage;

import com.urase.webapp.exception.NotExistStorageException;
import com.urase.webapp.model.Resume;
import com.urase.webapp.sql.SqlHelper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SqlStorage implements Storage {

    private final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SqlHelper(dbUrl, dbUser, dbPassword);
    }

    @Override
    public void clear() {
        String query = "DELETE FROM resume";
        sqlHelper.queryExecute(ps -> {
            ps.execute();
            return null;
        }, query, null);
    }

    @Override
    public void update(Resume r) {
        String query = "UPDATE resume SET full_name = ? WHERE uuid = ?";
        sqlHelper.queryExecute(ps -> {
            ps.setString(1, r.getFullName());
            ps.setString(2, r.getUuid());
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(r.getUuid());
            }
            return null;
        }, query, null);
    }

    @Override
    public void save(Resume r) {
        String query = "INSERT INTO resume (uuid, full_name) VALUES (?,?)";
        sqlHelper.queryExecute(ps -> {
            ps.setString(1, r.getUuid());
            ps.setString(2, r.getFullName());
            ps.execute();
            return null;
        }, query, r.getUuid());
    }

    @Override
    public Resume get(String uuid) {
        String query = "SELECT * FROM resume r WHERE r.uuid = ?";
        return sqlHelper.queryExecute(ps -> {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException(uuid);
            }
            return new Resume(uuid, rs.getString("full_name"));
        }, query, null);
    }

    @Override
    public void delete(String uuid) {
        String query = "DELETE FROM resume WHERE uuid = ?";
        sqlHelper.queryExecute(ps -> {
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        }, query, null);
    }

    @Override
    public List<Resume> getAllSorted() {
        String query = "SELECT uuid, full_name FROM resume ORDER BY uuid DESC";
        ArrayList<Resume> resumes = new ArrayList<>();
        return sqlHelper.queryExecute(ps -> {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                resumes.add(new Resume(rs.getString("uuid"), rs.getString("full_name")));
            }
            return resumes;
        }, query, null);
    }

    @Override
    public int size() {
        String query = "SELECT COUNT(uuid) AS size FROM resume";
        return sqlHelper.queryExecute(ps -> {
            ResultSet rs = ps.executeQuery();
            return !rs.next() ? 0 : rs.getInt("size");
        }, query, null);
    }
}
