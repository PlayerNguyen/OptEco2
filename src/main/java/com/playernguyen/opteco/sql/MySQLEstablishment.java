package com.playernguyen.opteco.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLEstablishment implements SQLEstablishment {

    private final String host;
    private final String username;
    private final String password;
    private final String database;
    private final String port;
    private final String prefix;

    public MySQLEstablishment(String host,
                              String username,
                              String password,
                              String database,
                              String port,
                              String prefix) throws ClassNotFoundException {
        this.host = host;
        this.username = username;
        this.password = password;
        this.database = database;
        this.port = port;
        this.prefix = prefix;

        // Validation of class
        Class.forName("com.mysql.jdbc.Driver");
    }


    public Connection openConnection() throws SQLException {
        // Open new connection
        return DriverManager.getConnection(String.format(
                "jdbc:mysql://%s:%s/%s?useSSL=false",
                this.host,
                this.port,
                this.database
        ), username, password);
    }

    @Override
    public String getPrefixedTable(String table) {
        return prefix.concat(table);
    }
}
