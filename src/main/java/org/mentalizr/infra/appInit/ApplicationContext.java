package org.mentalizr.infra.appInit;

import org.mentalizr.backend.config.infraUser.InfraUserConfiguration;
import org.mentalizr.infra.Timeout;

public class ApplicationContext {

    private static InfraUserConfiguration infraUserConfiguration;
    private static Timeout timeout = Timeout.getDefaultTimeout();
    private static boolean isInitialized = false;

    public static void initialize() {
        infraUserConfiguration = new InfraUserConfiguration();
        isInitialized = true;
    }

    public static InfraUserConfiguration getInfraUserConfiguration() {
        assertIsInitialized();
        return infraUserConfiguration;
    }

    public static Timeout getTimeout() {
        return timeout;
    }

    public static void setTimeout(Timeout timeout) {
        ApplicationContext.timeout = timeout;
    }

    private static void assertIsInitialized() {
        if (!isInitialized) throw new IllegalStateException("ApplicationContext not yet initialized.");
    }


}
