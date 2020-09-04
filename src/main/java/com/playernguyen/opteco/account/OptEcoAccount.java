package com.playernguyen.opteco.account;

import org.bukkit.OfflinePlayer;

import java.util.UUID;

public interface OptEcoAccount {

    UUID getUniqueId();

    double getCredit();

    String getName();

    OfflinePlayer getOfflinePlayer();

}
