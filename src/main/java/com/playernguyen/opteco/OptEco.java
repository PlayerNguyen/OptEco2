package com.playernguyen.opteco;

import com.playernguyen.opteco.configuration.config.OptEcoSettingConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

/**
 * OptEco main class
 */
public class OptEco extends JavaPlugin {

    /**
     * Instance of OptEco
     */
    private static OptEco instance;

    protected OptEcoSettingConfiguration settingConfiguration;

    @Override
    public void onEnable() {
        // Load instance
        setupInstance();

        // Load configuration
        setupConfiguration();
    }

    private void setupConfiguration() {
        getLogger().info("Loading configurations...");
        try {
            this.settingConfiguration = new OptEcoSettingConfiguration(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Set up the instance plugin
     */
    private void setupInstance() {
        // Loading instance plugin
        getLogger().info("Loading plugin...");
        instance = this;
    }

    /**
     * Get OptEco plugin instance
     * @return OptEco current class
     */
    public static OptEco getInstance() {
        return instance;
    }
}
