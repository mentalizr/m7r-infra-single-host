package org.mentalizr.infra.executors;

import org.mentalizr.commons.dirs.host.hostDir.M7rInfraConfigDir;
import org.mentalizr.commons.files.host.M7rInfraUserConfigFile;

public class ExecutionPreconditions {

    public static void check(boolean verbose) throws ExecutionPreconditionFailedException {
        System.out.println("Check preconditions ...");

        assertHostConfigDir();
        assertHostUserConfigFile();
    }

    private static void assertHostConfigDir() throws ExecutionPreconditionFailedException {
        M7rInfraConfigDir m7rInfraConfigDir = M7rInfraConfigDir.createInstance();
        System.out.println("Check existence of host config directory ["
                + m7rInfraConfigDir.asPath().toAbsolutePath() + "].");
        if (!m7rInfraConfigDir.exists())
            throw new ExecutionPreconditionFailedException("Host config directory not found: ["
                    + m7rInfraConfigDir.asPath().toAbsolutePath() + "].");
    }

    private static void assertHostUserConfigFile() throws ExecutionPreconditionFailedException {
        M7rInfraUserConfigFile m7RInfraUserConfigFile = M7rInfraUserConfigFile.createInstance();
        System.out.println("Check existence of host user config file ["
                + m7RInfraUserConfigFile.asPath().toAbsolutePath() + "].");
        if (!m7RInfraUserConfigFile.exists())
            throw new ExecutionPreconditionFailedException("Infra config file not found: ["
                    + m7RInfraUserConfigFile.asPath().toAbsolutePath() + "].");
    }

}
