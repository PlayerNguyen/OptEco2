package com.playernguyen.opteco.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class SQLUtil {

    public static boolean hasTable(Connection connection, String table) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SHOW TABLES;");
        ResultSet resultSet = preparedStatement.executeQuery();

        return new ResultFilter(resultSet).search(table) != -1;
    }

    public static class SQLite {

        public static List<String> getTables(SQLEstablishment establishment) {
            String s = "SELECT " +
                    "    name " +
                    "FROM " +
                    "    sqlite_master " +
                    "WHERE " +
                    "    type ='table' AND " +
                    "    name NOT LIKE 'sqlite_%';";
            List<String> ls = new ArrayList<>();
            try (Connection connection = establishment.openConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement(s);
                ResultFilter resultFilter = new ResultFilter(preparedStatement.executeQuery());

                for (HashMap<String, Object> next : resultFilter) {
                    for (Object value : next.values()) {
                        ls.add((String) value);
                    }
                }

                return ls;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return ls;
        }

        public static boolean hasTable(SQLEstablishment establishment, String name) {
            return getTables(establishment).contains(name);
        }

    }

}
