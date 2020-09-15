package com.playernguyen.opteco.listener;

import com.playernguyen.opteco.OptEco;
import org.bukkit.event.Listener;

public abstract class OptEcoListener implements Listener {

    private final OptEco optEco;

    public OptEcoListener(OptEco optEco) {
        this.optEco = optEco;
    }

    protected OptEco getOptEco() {
        return optEco;
    }
}
