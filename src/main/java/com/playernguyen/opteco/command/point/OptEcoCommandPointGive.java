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
import com.playernguyen.opteco.account.OptEcoAccount;
import com.playernguyen.opteco.command.Command;
import com.playernguyen.opteco.command.CommandResult;
import com.playernguyen.opteco.command.DefaultSubCommand;
import com.playernguyen.opteco.configuration.language.LanguageFlag;
import com.playernguyen.opteco.permission.PermissionsEnum;
import com.playernguyen.opteco.util.Placeholder;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class OptEcoCommandPointGive extends DefaultSubCommand {

    public OptEcoCommandPointGive(Command previousCommand) {
        super("give",
                OptEco.getInstance().getLanguageConfiguration().getLanguage(LanguageFlag.COMMAND_POINT_GIVE_DESCRIPTION),
                OptEco.getInstance().getLanguageConfiguration().getLanguage(LanguageFlag.COMMAND_POINT_GIVE_PARAMETER),
                previousCommand,
                PermissionsEnum.POINT_GIVE);
    }

    @Override
    public CommandResult onExecute(CommandSender sender, List<String> args) {

        return CommandResult.NULL;
    }

    @Override
    public List<String> onTab(CommandSender sender, List<String> args) {
        return null;
    }
}
