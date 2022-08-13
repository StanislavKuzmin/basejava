package com.urase.webapp.sql;

import com.urase.webapp.exception.ExistStorageException;
import com.urase.webapp.exception.StorageException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {
    private final ConnectionFactory connectionFactory;

    public SqlHelper(String dbUrl, String dbUser, String dbPassword) {
        connectionFactory = () -> DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    public <T> T queryExecute(BlockOfCode<T> blockOfCode, String query, String uuid) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            return blockOfCode.execute(ps);
        } catch (SQLException e) {
            final String ss = e.getSQLState();
            if (ss.equals("23505")) {
                throw new ExistStorageException(uuid);
            }
            throw new StorageException(e);
        }
    }
}
