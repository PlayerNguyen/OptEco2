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

import com.playernguyen.opteco.manager.ManagerSet;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;

public class OptEcoHubCommandManager extends ManagerSet<HubCommand> {

    @Override
    public void add(HubCommand item) {
        super.add(item);
        // Register with Bukkit
        PluginCommand pluginCommand = Bukkit.getPluginCommand(item.getCommand());
        if (pluginCommand == null) {
            throw new NullPointerException("Registering command not registered in plugin.yml: " + item.getCommand());
        }
        // Register to bukkit
        pluginCommand.setExecutor(item);
    }
}
