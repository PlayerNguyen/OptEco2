package com.playernguyen.opteco.sql;

import com.playernguyen.opteco.OptEco;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class SQLiteEstablishment implements SQLEstablishment {

    private final File sqliteFile;

    public SQLiteEstablishment(OptEco optEco, String name) throws ClassNotFoundException, IOException {

        // SQLite file
        this.sqliteFile = new File(optEco.getDataFolder(), name);
        // Initial the file
        if (!this.sqliteFile.exists() && !this.sqliteFile.createNewFile())
            throw new IllegalStateException("Cannot found or create SQLite file. Report to OptEco Dev for the help");

        // Class depend
        Class.forName("org.sqlite.JDBC");
    }

    @Override
    public Connection openConnection() throws SQLException {
        return null;
    }

    @Override
    public String getPrefixedTable(String table) {
        return null;
    }

    public File getSQLiteFile() {
        return sqliteFile;
    }
}
