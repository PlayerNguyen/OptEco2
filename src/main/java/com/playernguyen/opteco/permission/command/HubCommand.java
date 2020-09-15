package com.playernguyen.opteco.permission.command;

public interface HubCommand extends Command {

    SubCommandManager getSubCommands();

}
