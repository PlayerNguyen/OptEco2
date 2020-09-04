package com.playernguyen.opteco.configuration.config;

import com.playernguyen.opteco.OptEco;
import com.playernguyen.opteco.OptEcoConstants;
import com.playernguyen.opteco.configuration.Yamlist;

import java.io.IOException;

public class OptEcoSettingConfiguration extends Yamlist<OptEcoSettingFlag> {

    public OptEcoSettingConfiguration(OptEco optEco) throws IOException {
        super(optEco, OptEcoConstants.CONFIGURATION_FILE_NAME, "");
    }
}
