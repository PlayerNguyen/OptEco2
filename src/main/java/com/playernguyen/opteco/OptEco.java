package com.playernguyen.opteco;

import com.playernguyen.opteco.configuration.config.OptEcoSettingConfiguration;
import com.playernguyen.opteco.configuration.config.OptEcoSettingFlag;
import com.playernguyen.opteco.debugger.DebuggerProfiler;
import com.playernguyen.opteco.sql.MySQLEstablishment;
import com.playernguyen.opteco.sql.SQLEstablishment;
import com.playernguyen.opteco.sql.SQLUtil;
import com.playernguyen.opteco.sql.SQLiteEstablishment;
import com.playernguyen.opteco.storage.OptEcoStorage;
import com.playernguyen.opteco.update.SpigotUpdater;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * OptEco main class
 */
public class OptEco extends JavaPlugin {

    protected OptEcoSettingConfiguration settingConfiguration;
    protected OptEcoStorage storage;
    protected SQLEstablishment establishment;
    protected DebuggerProfiler debuggerProfiler;

    @Override
    public void onEnable() {
        try {
            // Load instance
            setupInstance();

            // Load configuration
            setupConfiguration();

            // Load storage and database
            setupStorageAndDatabase();

            // Load update
            setupUpdater();

        } catch (Exception e) {
            e.printStackTrace();
            // Announce disable
            getLogger().info("Disable plugin because plugin failed to setup...");
            // Disable
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }

    private void setupUpdater() {
        new SpigotUpdater(this);
    }

    /**
     * Setting up the database and storage system
     * @throws IOException Cannot save the file (SQLite file)
     * @throws ClassNotFoundException Not found Driver of SQL
     */
    private void setupStorageAndDatabase() throws IOException, ClassNotFoundException, SQLException {
        // Load storage form configuration
        this.storage = getSettingConfiguration().getStorage();

        // Handle the storage
        switch (this.storage) {
            case SQLITE: {
                this.establishment = new SQLiteEstablishment(
                        this,
                        getSettingConfiguration().getString(OptEcoSettingFlag.SQL_SQLITE_FILENAME),
                        getSettingConfiguration().getString(OptEcoSettingFlag.SQL_TABLE_PREFIX)
                );

                // Account setup
                getLogger().info("[SQLite] Checking account table...");
                if (!SQLUtil.SQLite.hasTable(establishment, getAccountTableName())) {
                    getLogger().info("Trying to create new account table...");
                    try (Connection connection = establishment.openConnection()) {
                        PreparedStatement preparedStatement = connection.prepareStatement(String.format(
                            "CREATE TABLE %s (" +
                                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                    "player VARCHAR NOT NULL," +
                                    "balance VARCHAR NOT NULL," +
                                    "uuid VARCHAR NOT NULL)",
                                getAccountTableName()
                        ));

                        // Create new table
                        preparedStatement.executeUpdate();
                    }
                }

                // Transaction setup
                getLogger().info("[SQLite] Checking transaction table...");
                if (!SQLUtil.SQLite.hasTable(establishment, getTransactionTableName())) {
                    getLogger().info("Trying to create new transaction table...");
                    try (Connection connection = establishment.openConnection()) {
                        PreparedStatement preparedStatement = connection.prepareStatement(String.format(
                                "CREATE TABLE %s (" +
                                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                        "from_uuid VARCHAR NOT NULL," +
                                        "amount VARCHAR NOT NULL," +
                                        "to_uuid VARCHAR NOT NULL)",
                                getTransactionTableName()
                        ));

                        // Create new table
                        preparedStatement.executeUpdate();
                    }
                }

                break;
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
                break;
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
            this.debuggerProfiler = new DebuggerProfiler(this);

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
        // Add instance here
        ArrayList<String> waterMarks = new ArrayList<>();
        waterMarks.add("                        ");
        waterMarks.add(" ___     ___   _______  ");
        waterMarks.add("|   |   |    )    |     " + ChatColor.DARK_GRAY + "Support Bukkit - Spigot - PaperMC");
        waterMarks.add("|   |   |---/     |     " + ChatColor.DARK_GRAY + "__________ ");
        waterMarks.add("|___/   |         |     Eco"+ ChatColor.RED + " v" + getDescription().getVersion());
        waterMarks.add("                     ");
        waterMarks.forEach(e -> Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + e));
    }

    /**
     * Setting config class which represent to config.yml
     * @return The current setting configuration class which initiate in {@link OptEco}
     */
    public OptEcoSettingConfiguration getSettingConfiguration() {
        return settingConfiguration;
    }

    /**
     * Debugger profiler of OptEco
     * @return DebuggerProfiler provide more API to conduct error
     */
    public DebuggerProfiler getDebuggerProfiler() {
        return debuggerProfiler;
    }

    /**
     * Get the account table name of SQL
     * @return The account table name
     */
    public String getAccountTableName() {
        return establishment.getPrefixedTable(
                getSettingConfiguration().getString(OptEcoSettingFlag.SQL_TABLE_ACCOUNT)
        );
    }

    /**
     * Get the transaction table name of SQL
     * @return The transaction table name
     */
    public String getTransactionTableName() {
        return establishment.getPrefixedTable(
                getSettingConfiguration().getString(OptEcoSettingFlag.SQL_TABLE_TRANSACTION)
        );
    }

    /**
     * Establishment getter
     * @return the Establishment which compatible with current storage type
     */
    public SQLEstablishment getEstablishment() {
        return establishment;
    }
}
