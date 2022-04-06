package org.mentalizr.infra.appInit;

import de.arthurpicht.processExecutor.ProcessExecution;
import de.arthurpicht.processExecutor.ProcessExecutionException;
import de.arthurpicht.processExecutor.ProcessResultCollection;
import org.mentalizr.commons.paths.M7rDir;
import org.mentalizr.commons.paths.M7rFile;
import org.mentalizr.commons.paths.client.M7rClientCliConfigFile;
import org.mentalizr.commons.paths.client.M7rClientCredentialsFile;
import org.mentalizr.commons.paths.host.hostDir.*;
import org.mentalizr.infra.utils.LoggerUtils;

import java.io.IOException;

public class ApplicationInitialization {

    public static void execute() throws ApplicationInitializationException {
        assertExistsM7rFile(M7rInfraUserConfigFile.createInstance());
        assertExistsM7rFile(M7rSslCertFile.createInstance());
        assertExistsM7rFile(M7rPrivateKeyFile.createInstance());

        assertExistsM7rFile(M7rClientCliConfigFile.createInstance());
        assertExistsM7rFile(M7rClientCredentialsFile.createInstance());

        assertCommand("docker");
        assertCommand("git");

        createLogDir();
        LoggerUtils.initialize();
        setConfigSystemProperty();
    }

    private static void assertExistsM7rDir(M7rDir m7rDir) throws ApplicationInitializationException {
        if (!m7rDir.exists())
            throw new ApplicationInitializationException(
                    m7rDir.getDescription()  + " not found: [" + m7rDir.toAbsolutePathString() + "].");
    }

    private static void assertExistsM7rFile(M7rFile m7rFile) throws ApplicationInitializationException {
        if (!m7rFile.exists())
            throw new ApplicationInitializationException(
                    m7rFile.getDescription()  + " not found: [" + m7rFile.toAbsolutePathString() + "].");
    }

    private static void assertCommand(String command) throws ApplicationInitializationException {
        try {
            ProcessResultCollection result = ProcessExecution.execute("which", command);
            if (result.getExitCode() > 0 || result.getStandardOut().size() == 0)
                throw new ApplicationInitializationException(
                        "Command not installed: [" + command + "].");
        } catch (ProcessExecutionException e) {
            throw new ApplicationInitializationException(
                    "Could not check existence of command [" + command + "]: " + e.getMessage(), e);
        }
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
