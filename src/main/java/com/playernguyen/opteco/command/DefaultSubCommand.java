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
import com.playernguyen.opteco.permission.PermissionManager;
import com.playernguyen.opteco.permission.PermissionsEnum;

public abstract class DefaultSubCommand extends OptEcoImplementation implements SubCommand {

    private final String command;
    private final String description;
    private final String parameter;
    private final Command previousCommand;
    private final PermissionManager permissionManager = new PermissionManager();

    public DefaultSubCommand(String command, String description, String parameter, Command previousCommand, PermissionsEnum mainPermission) {
        this.command = command;
        this.description = description;
        this.parameter = parameter;
        this.previousCommand = previousCommand;
        // Add default permission
        this.permissionManager.add(PermissionsEnum.ALL);
        this.permissionManager.add(PermissionsEnum.POINT_ALL);
        this.permissionManager.add(mainPermission);
    }

    @Override
    public Command getPreviousCommand() {
        return previousCommand;
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
}
