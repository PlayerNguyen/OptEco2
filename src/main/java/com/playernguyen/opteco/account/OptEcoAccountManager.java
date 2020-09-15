package com.playernguyen.opteco.account;

import com.playernguyen.opteco.manager.ManagerSet;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class OptEcoAccountManager extends ManagerSet<OptEcoAccount> {

    /**
     * Find account in storage
     * @param uniqueId the unique id of account
     * @return the {@link OptEcoAccount} which contain all info about Point
     */
    @Nullable
    public OptEcoAccount findAccount(UUID uniqueId) {
        return getContainer()
                .stream()
                .filter(e -> e.getUniqueId().equals(uniqueId))
                .findAny()
                .orElse(null);
    }


}
