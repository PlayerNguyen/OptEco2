package com.playernguyen.opteco.configuration.config;

import com.playernguyen.opteco.configuration.Flagable;

public enum  OptEcoSettingFlag implements Flagable {

    STORAGE_STORAGE_TYPE ("StorageType", "SQLite"),

    SQL_MYSQL_HOST("SQL.MySQL.Host", "localhost"),
    SQL_MYSQL_USERNAME("SQL.MySQL.Username", "root"),
    SQL_MYSQL_PASSWORD("SQL.MySQL.Password", ""),
    SQL_MYSQL_PORT("SQL.MySQL.Port", "3306"),
    SQL_MYSQL_DATABASE("SQL.MySQL.Database", "OptEco"),

    SQL_TABLE_PREFIX("SQL.Table.Prefix", "opteco_"),
    SQL_TABLE_ACCOUNT("SQL.Table.Account", "account"),

    SQL_SQLITE_FILENAME("SQL.SQLite.File", "database.sqlite"),

    DEBUG_DEBUG("Debug", false),
    ;

    private final String path;
    private final Object define;

    OptEcoSettingFlag(String path, Object define) {
        this.path = path;
        this.define = define;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public Object getDefine() {
        return define;
    }
}
