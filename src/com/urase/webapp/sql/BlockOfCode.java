package com.urase.webapp.sql;

import java.sql.PreparedStatement;

public interface BlockOfCode<T> {
    T execute(PreparedStatement ps);
}
