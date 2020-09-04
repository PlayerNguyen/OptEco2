package com.playernguyen.opteco.update;

import com.playernguyen.opteco.OptEco;
import com.playernguyen.opteco.OptEcoConstants;
import com.playernguyen.opteco.configuration.config.OptEcoSettingFlag;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

public class SpigotUpdater {

    private final OptEco optEco;

    public SpigotUpdater(OptEco optEco) {
        this.optEco = optEco;

        if (!optEco.getSettingConfiguration().getBoolean(OptEcoSettingFlag.UPDATER_UPDATER)) {
            return;
        }

        // Run task async
        Bukkit.getScheduler().runTaskAsynchronously(optEco, () -> {
            getOptEco().getLogger().info("Checking for new update...");
            try {
                // Combine raw url
                String rawUrl = OptEcoConstants.SPIGOT_RESOURCE_URL.concat(OptEcoConstants.SPIGOT_ID);
                getOptEco().getDebuggerProfiler().info("Raw update url: " + rawUrl);
                // Parse url
                URL url = new URL(rawUrl);
                InputStreamReader reader = new InputStreamReader(url.openStream());
                // Scan the text
                Scanner scanner = new Scanner(reader);
                StringBuilder result = new StringBuilder();
                while (scanner.hasNextLine()) {
                    String nl = scanner.nextLine();
                    getOptEco().getDebuggerProfiler().info("Next Line: ".concat(nl));
                    result.append(nl);
                }

                // Check whether different version
                if (getOptEco().getDescription().getVersion().equalsIgnoreCase(result.toString())) {
                    // Not differ
                    Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "[OptEco] Nothing new to update...");
                    return;
                }

                Bukkit.getConsoleSender()
                        .sendMessage(ChatColor.GOLD + "[OptEco]" +
                                " Found new update ("+result+"), please download at " +
                                "https://www.spigotmc.org/resources/76179/...");

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private OptEco getOptEco() {
        return optEco;
    }
}
