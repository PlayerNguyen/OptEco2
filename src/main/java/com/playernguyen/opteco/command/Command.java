package com.playernguyen.opteco.command;

import com.playernguyen.opteco.permission.PermissionManager;
import org.bukkit.command.CommandSender;

import java.util.List;

public interface Command {

    String getCommand();

    String getDescription();

    String getParameter();

    PermissionManager getPermissions();

    CommandResult onExecute(CommandSender sender, List<String> args);

    List<String> onTab(CommandSender sender, List<String> args);

}
