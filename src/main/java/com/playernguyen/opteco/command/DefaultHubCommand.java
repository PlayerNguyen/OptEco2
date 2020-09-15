/*
 * Copyright 2020 Player_Nguyen
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.playernguyen.opteco.command;

import com.playernguyen.opteco.OptEcoImplementation;
import com.playernguyen.opteco.configuration.language.LanguageFlag;
import com.playernguyen.opteco.permission.PermissionManager;
import com.playernguyen.opteco.permission.PermissionsEnum;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public abstract class DefaultHubCommand extends OptEcoImplementation implements HubCommand {

    private final String command;
    private final String description;
    private final String parameter;
    private final SubCommandManager subCommands = new SubCommandManager();
    private final PermissionManager permissionManager = new PermissionManager();

    public DefaultHubCommand(String command,
                             String description,
                             String parameter,
                             PermissionsEnum permission) {
        this.command = command;
        this.description = description;
        this.parameter = parameter;
        // Add default permissions
        getPermissions().add(PermissionsEnum.ALL);
        getPermissions().add(PermissionsEnum.POINT_ALL);
        getPermissions().add(PermissionsEnum.ADMIN);
        getPermissions().add(permission);
    }

    @Override
    public SubCommandManager getSubCommands() {
        return subCommands;
    }

    @Override
    public String getCommand() {
        return command;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getParameter() {
        return parameter;
    }

    @Override
    public PermissionManager getPermissions() {
        return permissionManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender,
                             @NotNull Command command,
                             @NotNull String label,
                             @NotNull String[] args) {
        // Permission check
        CommandResult result = onExecute(sender, Arrays.asList(args));
        switch (result) {
            case NO_PERMISSION: {
                sender.sendMessage(
                        languages().getLanguagePrefix(LanguageFlag.COMMAND_NO_PERMISSION)
                );
                break;
            }
            case MISSING_ARGUMENT: {
                sender.sendMessage(
                        languages().getLanguagePrefix(LanguageFlag.COMMAND_MISSING_ARGUMENT)
                );
                break;
            }
            case NOT_FOUND: {
                sender.sendMessage(
                        languages().getLanguagePrefix(LanguageFlag.COMMAND_NOT_FOUND)
                );
                break;
            }
            case INVALID_SENDER: {
                sender.sendMessage(
                        languages().getLanguagePrefix(LanguageFlag.COMMAND_INVALID_SENDER)
                );
                break;
            }
            case NULL:
            case NOTHING: {
                break;
            }
            default:
                throw new IllegalArgumentException("The command result cannot be null or something else");
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender,
                                                @NotNull Command command,
                                                @NotNull String alias,
                                                @NotNull String[] args) {
        return this.onTab(sender, Arrays.asList(args));
    }
}
