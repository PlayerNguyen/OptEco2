package com.playernguyen.opteco.permission.command;

public interface Command {

    String getCommand();

    String getDescription();

    String getParameter();

    String getPermissions();

}
