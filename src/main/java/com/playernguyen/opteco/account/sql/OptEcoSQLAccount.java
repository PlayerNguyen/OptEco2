package com.playernguyen.opteco.account.sql;

import com.playernguyen.opteco.account.OptEcoAccount;
import com.playernguyen.opteco.sql.SQLEstablishment;

import java.util.UUID;

public interface OptEcoSQLAccount {

    SQLEstablishment getEstablishment();

    boolean hasAccount(UUID uuid);

    OptEcoAccount getAccountFromUUID(UUID uniqueId);

    String getAccountTable();

    void update(OptEcoAccount account);


}
