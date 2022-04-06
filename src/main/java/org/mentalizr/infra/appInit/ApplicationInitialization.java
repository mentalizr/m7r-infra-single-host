package org.mentalizr.infra;

import org.mentalizr.commons.paths.host.hostDir.M7rHostLogDir;
import org.mentalizr.commons.paths.host.hostDir.M7rInfraConfigDir;
import org.mentalizr.commons.paths.host.hostDir.M7rInfraUserConfigFile;
import org.mentalizr.infra.utils.LoggerUtils;

import java.io.IOException;

public class ApplicationInitialization {

    public static void execute() throws ApplicationInitializationException {
        assertHostConfigDir();
        assertHostUserConfigFile();
        createLogDir();
        LoggerUtils.initialize();
        setConfigSystemProperty();
    }

    private static void assertHostConfigDir() throws ApplicationInitializationException {
        M7rInfraConfigDir m7rInfraConfigDir = M7rInfraConfigDir.createInstance();
        System.out.println("Check existence of host config directory ["
                + m7rInfraConfigDir.asPath().toAbsolutePath() + "].");
        if (!m7rInfraConfigDir.exists())
            throw new ApplicationInitializationException("Host config directory not found: ["
                    + m7rInfraConfigDir.asPath().toAbsolutePath() + "].");
    }

    private static void assertHostUserConfigFile() throws ApplicationInitializationException {
        M7rInfraUserConfigFile m7RInfraUserConfigFile = M7rInfraUserConfigFile.createInstance();
        System.out.println("Check existence of host user config file ["
                + m7RInfraUserConfigFile.asPath().toAbsolutePath() + "].");
        if (!m7RInfraUserConfigFile.exists())
            throw new ApplicationInitializationException("Infra config file not found: ["
                    + m7RInfraUserConfigFile.asPath().toAbsolutePath() + "].");
    }

    private static void createLogDir() throws ApplicationInitializationException {
        M7rHostLogDir m7rHostLogDir = M7rHostLogDir.createInstance();
        if (!m7rHostLogDir.exists()) {
            try {
                m7rHostLogDir.create();
            } catch (IOException e) {
                throw new ApplicationInitializationException("Application initialization failed. Could not create directory ["
                        + m7rHostLogDir.toAbsolutePathString() + "]");
            }
        }
    }

    private static void setConfigSystemProperty() {
        System.setProperty(
                "m7r.config",
                M7rInfraUserConfigFile.createInstance().toAbsolutePathString());
    }

}
