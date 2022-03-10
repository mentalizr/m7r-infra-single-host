package org.mentalizr.infra.executors;

import org.mentalizr.commons.M7rDirs;
import org.mentalizr.infra.InfraConfigFile;

public class ExecutionPreconditions {

    public static void check(boolean verbose) throws ExecutionPreconditionFailedException {

        System.out.println("Check preconditions ...");
        M7rDirs m7rDirs = new M7rDirs();

        System.out.println("Check existence of infra config directory ["
                + m7rDirs.getInfraConfigDir().toAbsolutePath() + "].");
        if (!m7rDirs.existsInfraConfigDir())
            throw new ExecutionPreconditionFailedException("Infra config directory not found: ["
                    + m7rDirs.getInfraConfigDir().toAbsolutePath() + "].");

        InfraConfigFile infraConfigFile = new InfraConfigFile(m7rDirs);
        System.out.println("Check existence of infra config file ["
                + infraConfigFile.getInfraConfigFile().toAbsolutePath() + "].");
        if (!infraConfigFile.exists())
            throw new ExecutionPreconditionFailedException("Infra config file not found: ["
                    + infraConfigFile.getInfraConfigFile().toAbsolutePath() + "].");

    }

}
