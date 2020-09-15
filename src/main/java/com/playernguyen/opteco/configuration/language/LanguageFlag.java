package com.playernguyen.opteco.configuration.language;

import com.playernguyen.opteco.configuration.Flagable;

public enum  LanguageFlag implements Flagable {

    PREFIX("Prefix", "&7[&cOptEco&7] ")
    ;


    private final String path;
    private final Object define;

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public Object getDefine() {
        return define;
    }

    LanguageFlag(String path, Object define) {
        this.path = path;
        this.define = define;
    }
}
