package org.mentalizr.infra.executors;

import de.arthurpicht.cli.CliCall;
import de.arthurpicht.cli.CommandExecutor;
import de.arthurpicht.cli.CommandExecutorException;
import de.arthurpicht.processExecutor.ProcessExecutorBuilder;
import org.mentalizr.infra.ExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DaemonStartExecutor implements CommandExecutor {

    Logger logger = LoggerFactory.getLogger(DaemonStartExecutor.class);

    @Override
    public void execute(CliCall cliCall) throws CommandExecutorException {
        ExecutionContext.initialize(cliCall);

        System.out.println("daemon started");

        logger.info("daemon started");

        ProcessExecutorBuilder
    }

}
