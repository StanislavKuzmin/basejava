package com.urase.webapp.storage;

import com.urase.webapp.Config;

public class SqlStorageTest extends AbstractStorageTest {

    public SqlStorageTest() {
        super(new SqlStorage(Config.get().getDbUrl(),
                Config.get().getDbUser(), Config.get().getDbPassword()));
    }
}