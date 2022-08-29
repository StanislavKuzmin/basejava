package com.urase.webapp.storage;

import com.urase.webapp.exception.NotExistStorageException;
import com.urase.webapp.model.ContactType;
import com.urase.webapp.model.Resume;
import com.urase.webapp.sql.SqlHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
        }, query);
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
        }, query);
    }

    @Override
    public void save(Resume r) {
        sqlHelper.transactionalExecute(conn -> {
                    PreparedStatement ps1 = createPreparedStatement(conn, "INSERT INTO resume (uuid, full_name) VALUES (?,?)");
                    PreparedStatement ps2 = createPreparedStatement(conn, "INSERT INTO contact (resume_uuid, type, value) VALUES (?,?,?)");
                    ps1.setString(1, r.getUuid());
                    ps1.setString(2, r.getFullName());
                    ps1.execute();
                    for (Map.Entry<ContactType, String> e : r.getContacts().entrySet()) {
                        ps2.setString(1, r.getUuid());
                        ps2.setString(2, e.getKey().name());
                        ps2.setString(3, e.getValue());
                        ps2.addBatch();
                    }
                    ps2.executeBatch();
                    return null;
                }
        );
    }

    @Override
    public Resume get(String uuid) {
        String query = "SELECT * FROM resume r" +
                "    LEFT JOIN contact c" +
                "    ON r.uuid = c.resume_uuid" +
                "    WHERE r.uuid = ?";
        return sqlHelper.queryExecute(ps -> {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException(uuid);
            }
            Resume r = new Resume(uuid, rs.getString("full_name"));
            do {
                ContactType type = ContactType.valueOf(rs.getString("type"));
                r.saveContact(type, rs.getString("value"));
            } while (rs.next());
            return r;
        }, query);
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
        }, query);
    }

    @Override
    public List<Resume> getAllSorted() {
        String query = "SELECT r.uuid, r.full_name, c.type, c.value" +
                " FROM resume r" +
                " LEFT JOIN contact c" +
                " ON r.uuid = c.resume_uuid" +
                " ORDER BY uuid DESC";
        Map<String, Resume> resumesById = new LinkedHashMap<>();
        return sqlHelper.queryExecute(ps -> {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String uuid = rs.getString("uuid");
                String fullName = rs.getString("full_name");
                ContactType type = ContactType.valueOf(rs.getString("type"));
                String value = rs.getString("value");
                Resume r = resumesById.get(uuid);
                if (r == null) {
                    r = new Resume(uuid, fullName);
                    resumesById.put(uuid, r);
                }
                r.saveContact(type, value);
            }
            return new ArrayList<>(resumesById.values());
        }, query);
    }

    @Override
    public int size() {
        String query = "SELECT COUNT(uuid) AS size FROM resume";
        return sqlHelper.queryExecute(ps -> {
            ResultSet rs = ps.executeQuery();
            return !rs.next() ? 0 : rs.getInt("size");
        }, query);
    }

    private PreparedStatement createPreparedStatement(Connection con, String sql) throws SQLException {
        return con.prepareStatement(sql);
    }
}
