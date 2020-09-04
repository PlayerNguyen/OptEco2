package com.playernguyen.opteco.debugger;

import com.playernguyen.opteco.OptEco;
import com.playernguyen.opteco.OptEcoImplementation;
import com.playernguyen.opteco.configuration.config.OptEcoSettingFlag;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class DebuggerProfiler extends OptEcoImplementation {

    private final boolean enable;

    public DebuggerProfiler(OptEco optEco) {
        this.enable = optEco.getSettingConfiguration().getBoolean(OptEcoSettingFlag.DEBUG_DEBUG);
        // Test the debugger profiler
        if (enable) {
            warn("Debug mode are on...Please switch off `Debug` if use want to use this plugin in normal way..");

            info("Info test");
            warn("Warn test");
            error("Error test");


        }
    }

    protected boolean isEnable() {
        return enable;
    }

    protected void log(String s, Object... objects) {
        // Check whether enable
        if (isEnable()) {
            Bukkit.getConsoleSender().sendMessage(String.format(
                    "['OptEco::Debugger]" + s, objects
            ));
        }
    }

    public void info(String s, Object ...objects) {
        log(ChatColor.DARK_GRAY + "[INFO]: " + s, objects);
    }

    public void warn(String s, Object ...objects) {
        log(ChatColor.YELLOW + "[WARN]: " + s, objects);
    }

    public void error(String s, Object ...objects) {
        log(ChatColor.RED + "[ERROR]: " + s, objects);
    }

}
