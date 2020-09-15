package com.playernguyen.opteco.permission.command;

public class DefaultHubCommand implements HubCommand {

    private String command;
    private String description;
    private String parameter;

    private final com.playernguyen.opteco.permission.command.PermissionManager permissionManager = new com.playernguyen.opteco.permission.command.PermissionManager();


}
