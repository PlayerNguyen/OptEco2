package com.playernguyen.opteco.permission.command;

import com.playernguyen.opteco.manager.ManagerSet;

import java.util.List;
import java.util.stream.Collectors;

public class SubCommandManager extends ManagerSet<SubCommand> {

    public SubCommand findCommand(String command) {
        return getContainer().stream().filter(e -> e.getCommand().equalsIgnoreCase(command))
                .findAny().orElse(null);
    }

    public List<String> asList() {
        return getContainer().stream().map(Command::getCommand).collect(Collectors.toList());
    }

}
