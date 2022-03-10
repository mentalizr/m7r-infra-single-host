package org.mentalizr.infra.executors;

import ch.qos.logback.classic.LoggerContext;
import de.arthurpicht.cli.CliCall;
import de.arthurpicht.cli.CommandExecutor;
import de.arthurpicht.cli.CommandExecutorException;
import org.mentalizr.infra.utils.LoggerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class TestExecutor implements CommandExecutor {

    @Override
    public void execute(CliCall cliCall) throws CommandExecutorException {

        System.out.println("test called.");

        Logger logger = LoggerFactory.getLogger(LoggerUtils.DOCKER_LOGGER);
        logger.info("test!");

        Logger anotherLogger = LoggerFactory.getLogger("some.other");
        anotherLogger.info("some other log statement.");

    }


}
