package com.playernguyen.opteco.account;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.UUID;

public class OptEcoAccountStorage implements OptEcoAccount {

    private final UUID uniqueId;
    private final String name;
    private final double credit;

    public OptEcoAccountStorage(UUID uniqueId, String name, double credit) {
        this.uniqueId = uniqueId;
        this.name = name;
        this.credit = credit;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getCredit() {
        return credit;
    }

    @Override
    public UUID getUniqueId() {
        return uniqueId;
    }

    @Override
    public OfflinePlayer getOfflinePlayer() {
        return Bukkit.getOfflinePlayer(getUniqueId());
    }

    @Override
    public String toString() {
        return "OptEcoAccountStorage{" +
                "uniqueId=" + uniqueId +
                ", name='" + name + '\'' +
                ", credit=" + credit +
                '}';
    }
}
