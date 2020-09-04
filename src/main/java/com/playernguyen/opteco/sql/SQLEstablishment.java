package com.playernguyen.opteco.sql;

import java.sql.Connection;
import java.sql.SQLException;

public interface SQLEstablishment  {

    Connection openConnection() throws SQLException;

    String getPrefixedTable(String table);

}
