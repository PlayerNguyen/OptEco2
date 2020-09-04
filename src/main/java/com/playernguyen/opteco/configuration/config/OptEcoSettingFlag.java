package com.playernguyen.opteco.configuration.config;

import com.playernguyen.opteco.configuration.Flagable;

public enum  OptEcoSettingFlag implements Flagable {

    ;

    private final String path;
    private final String define;

    OptEcoSettingFlag(String path, String define) {
        this.path = path;
        this.define = define;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public String getDefine() {
        return define;
    }
}
