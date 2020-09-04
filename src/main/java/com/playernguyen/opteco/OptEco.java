package com.playernguyen.opteco;

import com.playernguyen.opteco.configuration.config.OptEcoSettingConfiguration;
import com.playernguyen.opteco.configuration.config.OptEcoSettingFlag;
import com.playernguyen.opteco.sql.MySQLEstablishment;
import com.playernguyen.opteco.sql.SQLEstablishment;
import com.playernguyen.opteco.sql.SQLiteEstablishment;
import com.playernguyen.opteco.storage.OptEcoStorage;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
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
    protected OptEcoStorage storage;
    protected SQLEstablishment establishment;

    @Override
    public void onEnable() {
        try {
            // Load instance
            setupInstance();

            // Load configuration
            setupConfiguration();

            // Load storage and database
            setupStorageAndDatabase();
        } catch (Exception e) {
            e.printStackTrace();
            // Announce disable
            getLogger().info("Disable plugin because plugin failed to setup...");
            // Disable
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }

    /**
     * Setting up the database and storage system
     * @throws IOException Cannot save the file (SQLite file)
     * @throws ClassNotFoundException Not found Driver of SQL
     */
    private void setupStorageAndDatabase() throws IOException, ClassNotFoundException {
        // Load storage form configuration
        this.storage = getSettingConfiguration().getStorage();

        // Handle the storage
        switch (this.storage) {
            case SQLITE: {
                this.establishment = new SQLiteEstablishment(
                        this,
                        getSettingConfiguration().getString(OptEcoSettingFlag.SQL_SQLITE_FILENAME)
                );
            }
            case MYSQL: {
                this.establishment = new MySQLEstablishment(
                        getSettingConfiguration().getString(OptEcoSettingFlag.SQL_MYSQL_HOST),
                        getSettingConfiguration().getString(OptEcoSettingFlag.SQL_MYSQL_USERNAME),
                        getSettingConfiguration().getString(OptEcoSettingFlag.SQL_MYSQL_PASSWORD),
                        getSettingConfiguration().getString(OptEcoSettingFlag.SQL_MYSQL_DATABASE),
                        getSettingConfiguration().getString(OptEcoSettingFlag.SQL_MYSQL_PORT),
                        getSettingConfiguration().getString(OptEcoSettingFlag.SQL_TABLE_PREFIX)
                );
            }
            default: throw new UnsupportedOperationException("Storage type not support! Please re-config the plugin.yml");
        }
    }

    /**
     * Set up configuration setting
     */
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

    /**
     * Setting config class which represent to config.yml
     * @return The current setting configuration class which initiate in {@link OptEco}
     */
    protected OptEcoSettingConfiguration getSettingConfiguration() {
        return settingConfiguration;
    }


}
