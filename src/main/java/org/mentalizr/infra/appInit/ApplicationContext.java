package org.mentalizr.infra.appInit;

import org.mentalizr.backend.config.infraUser.InfraUserConfiguration;

public class ApplicationContext {

    private static InfraUserConfiguration infraUserConfiguration;
    private static boolean isInitialized = false;

    public static void initialize() {
        infraUserConfiguration = new InfraUserConfiguration();
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
