/*
 * Copyright 2020 Player_Nguyen
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.playernguyen.opteco.command.point;

import com.playernguyen.opteco.OptEco;
import com.playernguyen.opteco.command.Command;
import com.playernguyen.opteco.command.CommandResult;
import com.playernguyen.opteco.command.DefaultHubCommand;
import com.playernguyen.opteco.command.SubCommand;
import com.playernguyen.opteco.configuration.language.LanguageFlag;
import com.playernguyen.opteco.permission.PermissionsEnum;
import org.bukkit.command.CommandSender;

import java.util.List;
import java.util.stream.Collectors;

public class OptEcoCommandPoint extends DefaultHubCommand {

    public OptEcoCommandPoint() {
        super("point",
                OptEco.getInstance().getLanguageConfiguration().getLanguage(LanguageFlag.COMMAND_POINT_DESCRIPTION),
                OptEco.getInstance().getLanguageConfiguration().getLanguage(LanguageFlag.COMMAND_POINT_PARAMETER),
                PermissionsEnum.POINT);
        // Append more command here
        getSubCommands().add(new OptEcoCommandPointGive(this));
        getSubCommands().add(new OptEcoCommandMe(this));
    }

    @Override
    public CommandResult onExecute(CommandSender sender, List<String> args) {
        // No permissions to access
        if (!getPermissions().allow(sender)) {
            return CommandResult.NO_PERMISSION;
        }
        // Missing arguments
        if (args.size() <= 0) {
            return CommandResult.MISSING_ARGUMENT;
        }
        // Find command
        SubCommand subCommand = getSubCommands().findCommand(args.get(0));
        if (subCommand == null) {
            return CommandResult.NOT_FOUND;
        }
        // Not permission access to the sub-command
        if (!subCommand.getPermissions().allow(sender)) {
            return CommandResult.NO_PERMISSION;
        }
        // Return
        return subCommand.onExecute(sender, args);
    }

    @Override
    public List<String> onTab(CommandSender sender, List<String> args) {
        System.out.println(args.size());
        if (args.size() == 1) {
            return getSubCommands()
                    .stream()
                    .filter(e -> e.getPermissions().allow(sender))
                    .map(Command::getCommand)
                    .collect(Collectors.toList());
        }
        return null;
    }
}
