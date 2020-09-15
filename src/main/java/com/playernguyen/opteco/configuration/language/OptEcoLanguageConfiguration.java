package com.playernguyen.opteco.configuration.language;

import com.playernguyen.opteco.OptEco;
import com.playernguyen.opteco.configuration.Yamlist;
import org.bukkit.ChatColor;

public class OptEcoLanguageConfiguration extends Yamlist<LanguageFlag> {

    public OptEcoLanguageConfiguration(OptEco optEco) {
        super(optEco, "language.yml", LanguageFlag.values(), true, "");
    }

    public String getLanguage(LanguageFlag languageFlag) {
        return ChatColor.translateAlternateColorCodes('&',
                getString(languageFlag));
    }

    public String getLanguagePrefix(LanguageFlag languageFlag) {
        return getLanguage(LanguageFlag.PREFIX).concat(ChatColor.translateAlternateColorCodes('&',
                getString(languageFlag)));
    }
}
