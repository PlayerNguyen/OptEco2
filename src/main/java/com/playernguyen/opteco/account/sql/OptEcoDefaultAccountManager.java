package com.playernguyen.opteco.account.sql;

import com.playernguyen.opteco.account.OptEcoAccount;
import com.playernguyen.opteco.debugger.DebuggerProfiler;
import com.playernguyen.opteco.sql.ResultFilter;
import com.playernguyen.opteco.sql.SQLEstablishment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class OptEcoDefaultAccountManager implements OptEcoAccountManager {

    private final SQLEstablishment establishment;
    private final String accountTable;
    private final DebuggerProfiler debuggerProfiler;

    public OptEcoDefaultAccountManager(String accountTable,
                                       DebuggerProfiler debuggerProfiler,
                                       SQLEstablishment establishment) {
        this.accountTable = accountTable;
        this.debuggerProfiler = debuggerProfiler;
        this.establishment = establishment;
    }

    @Override
    public SQLEstablishment getEstablishment() {
        return establishment;
    }

    @Override
    public boolean hasAccount(UUID uuid) {
        try (Connection connection = getEstablishment().openConnection()) {
            // Prepare the connect
            String query = String.format(
                    "SELECT * FROM `%s` WHERE `uuid`=?",
                    getEstablishment().getPrefixedTable(accountTable)
            );
            debuggerProfiler.info("Trying to execute query: " + query);
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            // Set parameter
            preparedStatement.setString(1, uuid.toString());
            // Execute
            return new ResultFilter(preparedStatement.executeQuery()).size() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public OptEcoAccount getAccountFromUUID() {
        return null;
    }
}
