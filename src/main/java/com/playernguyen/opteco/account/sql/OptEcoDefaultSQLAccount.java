package com.playernguyen.opteco.account.sql;

import com.playernguyen.opteco.OptEco;
import com.playernguyen.opteco.account.OptEcoAccount;
import com.playernguyen.opteco.account.OptEcoAccountStorage;
import com.playernguyen.opteco.debugger.DebuggerProfiler;
import com.playernguyen.opteco.sql.ResultFilter;
import com.playernguyen.opteco.sql.SQLEstablishment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

public class OptEcoDefaultSQLAccount implements OptEcoSQLAccount {

    private final SQLEstablishment establishment;
    private final String accountTable;
    private final DebuggerProfiler debuggerProfiler;

    public OptEcoDefaultSQLAccount(OptEco optEco) {
        this.accountTable = optEco.getAccountTableName();
        this.debuggerProfiler = optEco.getDebuggerProfiler();
        this.establishment = optEco.getEstablishment();
    }

    @Override
    public String getAccountTable() {
        return accountTable;
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
                    "SELECT * FROM %s WHERE uuid=?",
                    getAccountTable()
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
    public OptEcoAccount getAccountFromUUID(UUID uniqueId) {

        try (Connection connection = establishment.openConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(String.format(
                    "SELECT * FROM %s WHERE uuid=?",
                    getAccountTable()
            ));

            // Set parameter
            preparedStatement.setString(1, uniqueId.toString());

            // Getter
            HashMap<String, Object> item =
                    new ResultFilter(preparedStatement.executeQuery()).first();
            // Return the getter
            return new OptEcoAccountStorage(
                    UUID.fromString((String) item.get("uuid")),
                    (String) item.get("player"),
                    (double) item.get("balance")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void update(OptEcoAccount account) {

    }
}
