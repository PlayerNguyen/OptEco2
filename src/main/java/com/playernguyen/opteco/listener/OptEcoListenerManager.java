package com.playernguyen.opteco.listener;

import com.playernguyen.opteco.OptEco;
import com.playernguyen.opteco.manager.ManagerSet;
import org.bukkit.Bukkit;

import java.util.HashSet;

public class OptEcoListenerManager extends ManagerSet<OptEcoListener> {

    private final OptEco optEco;

    public OptEcoListenerManager(OptEco optEco) {
        super(new HashSet<>());
        this.optEco = optEco;
    }

    @Override
    public void add(OptEcoListener item) {
        super.add(item);

        // Register the event
        Bukkit.getServer().getPluginManager().registerEvents(item, getOptEco());
    }

    private OptEco getOptEco() {
        return optEco;
    }
}
