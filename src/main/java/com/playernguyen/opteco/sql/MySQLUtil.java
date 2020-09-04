package com.playernguyen.opteco.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLUtil {

    public static boolean hasTable(Connection connection, String table) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SHOW TABLES;");
        ResultSet resultSet = preparedStatement.executeQuery();

        return new ResultFilter(resultSet).search(table) != -1;
    }

}
