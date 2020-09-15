package com.playernguyen.opteco.listener;

import com.playernguyen.opteco.OptEco;
import com.playernguyen.opteco.account.OptEcoAccount;
import com.playernguyen.opteco.account.OptEcoAccountPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class PlayerJoinListener extends OptEcoListener {

    public PlayerJoinListener(OptEco optEco) {
        super(optEco);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        Bukkit.getScheduler().runTaskAsynchronously(getOptEco(), () -> {
            OptEcoAccount account;

            // Whether not
            if (!getOptEco().getSQLAccount().hasAccount(uuid)) {
                // Create new account
                account = new OptEcoAccountPlayer(uuid, player.getName(), 0.0d);

            } else {
                account = getOptEco().getSQLAccount().getAccountFromUUID(uuid);
            }

            // Insert account
            getOptEco().getAccountManager().add(account);
        });

    }

}
