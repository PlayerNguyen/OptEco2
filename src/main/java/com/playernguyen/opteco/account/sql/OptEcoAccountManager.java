package com.playernguyen.opteco.account.sql;

import com.playernguyen.opteco.OptEco;
import com.playernguyen.opteco.account.OptEcoAccount;
import com.playernguyen.opteco.sql.SQLEstablishment;

import java.util.UUID;

public interface OptEcoAccountManager {

    SQLEstablishment getEstablishment();

    boolean hasAccount(UUID uuid);

    OptEcoAccount getAccountFromUUID();

}
