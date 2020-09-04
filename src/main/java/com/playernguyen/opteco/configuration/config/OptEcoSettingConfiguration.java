package com.playernguyen.opteco.configuration.config;

import com.playernguyen.opteco.OptEco;
import com.playernguyen.opteco.OptEcoConstants;
import com.playernguyen.opteco.configuration.Yamlist;
import com.playernguyen.opteco.storage.OptEcoStorage;

import java.io.IOException;

public class OptEcoSettingConfiguration extends Yamlist<OptEcoSettingFlag> {

    public OptEcoSettingConfiguration(OptEco optEco) throws IOException {
        super(optEco, OptEcoConstants.CONFIGURATION_FILE_NAME, "");

    }

    public OptEcoStorage getStorage() {
        String _storage = getString(OptEcoSettingFlag.STORAGE_STORAGE_TYPE);

        if (_storage.equalsIgnoreCase("sqlite")) {
            return OptEcoStorage.SQLITE;
        }
        if (_storage.equalsIgnoreCase("mysql")) {
            return OptEcoStorage.MYSQL;
        }

        return null;
    }


}
