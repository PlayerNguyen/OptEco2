package com.playernguyen.opteco.configuration.language;

import com.playernguyen.opteco.configuration.Flagable;

public enum LanguageFlag implements Flagable {

    PREFIX("Prefix", "&7[&cOptEco&7] "),
    POINT_UNIT("PointUnit", "points"),

    COMMAND_NO_PERMISSION("Command.NoPermission", "&cNo permission to perform this command"),
    COMMAND_MISSING_ARGUMENT("Command.MissingArgument", "&cMissing arguments"),
    COMMAND_NOT_FOUND("Command.NotFound", "&cCommand not found!"),
    COMMAND_INVALID_SENDER("Command.InvalidSender", "&cInvalid sender, you cannot using command on this sender"),

    COMMAND_POINT_DESCRIPTION("Command.Point.Hub.Description", "Command of OptEco Plugin"),
    COMMAND_POINT_PARAMETER("Command.Point.Hub.Paramter", "<parameter>"),

    COMMAND_POINT_GIVE_DESCRIPTION("Command.Point.Give.Description", "Give point to player"),
    COMMAND_POINT_GIVE_PARAMETER("Command.Point.Give.Parameter", "<player> <amount>"),

    COMMAND_POINT_ME_DESCRIPTION("Command.Point.Me.Description", "Get info about my point"),
    COMMAND_POINT_ME_PARAMETER("Command.Point.Me.Parameter", ""),
    COMMAND_POINT_ME_RESULT("Command.Point.Me.Result", "&cYour point now &6%points%&c.")
    ;


    private final String path;
    private final Object define;

    LanguageFlag(String path, Object define) {
        this.path = path;
        this.define = define;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public Object getDefine() {
        return define;
    }
}
