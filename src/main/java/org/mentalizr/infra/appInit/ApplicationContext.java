package org.mentalizr.infra.appInit;

import org.mentalizr.backend.config.infraUser.InfraUserConfiguration;
import org.mentalizr.commons.paths.host.hostDir.M7rInfraUserConfigFile;

public class ApplicationContext {

    private static InfraUserConfiguration infraUserConfiguration;
    private static boolean isInitialized = false;

    public static void initialize() {
        infraUserConfiguration = new InfraUserConfiguration(M7rInfraUserConfigFile.createInstance());
        isInitialized = true;
    }

    public static InfraUserConfiguration getInfraUserConfiguration() {
        assertIsInitialized();
        return infraUserConfiguration;
    }

    private static void assertIsInitialized() {
        if (!isInitialized) throw new IllegalStateException("ApplicationContext not yet initialized.");
    }


}
