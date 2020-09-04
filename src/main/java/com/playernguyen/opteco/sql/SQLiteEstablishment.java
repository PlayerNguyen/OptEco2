package com.playernguyen.opteco.sql;

import com.playernguyen.opteco.OptEco;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteEstablishment implements SQLEstablishment {

    private final File sqliteFile;
    private final String prefix;

    public SQLiteEstablishment(OptEco optEco, String name, String prefix) throws ClassNotFoundException, IOException {
        this.prefix = prefix;

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
        return DriverManager.getConnection("jdbc:sqlite:".concat(getSQLiteFile().getPath()));
    }

    @Override
    public String getPrefixedTable(String table) {
        return prefix.concat(table);
    }

    public File getSQLiteFile() {
        return sqliteFile;
    }
}
