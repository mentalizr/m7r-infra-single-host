package org.mentalizr.infra.docker;

import de.arthurpicht.utils.core.collection.Lists;
import de.arthurpicht.utils.core.strings.Strings;
import org.mentalizr.infra.DockerExecutionException;
import org.mentalizr.infra.IllegalInfraStateException;
import org.mentalizr.infra.process.passThrough.ProcessPassThrough;
import org.mentalizr.infra.process.passThrough.ProcessPassThroughBuilder;
import org.mentalizr.infra.processExecutor.ProcessExecution;
import org.mentalizr.infra.processExecutor.ProcessExecutionException;
import org.mentalizr.infra.utils.LoggerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Shell {

    private static final Logger logger = LoggerFactory.getLogger(LoggerUtils.DOCKER_LOGGER);

    public static void openBashShell(DockerExecutionContext context, String containerName) throws DockerExecutionException, IllegalInfraStateException {
        open(context, containerName, Lists.newArrayList("bash"));
    }

    public static void open(DockerExecutionContext context, String containerName, List<String> executionCommands) throws DockerExecutionException, IllegalInfraStateException {
        if (!Container.exists(context, containerName))
            throw new IllegalInfraStateException("Cannot open shell on container [" + containerName + "]." +
                    " Not existing.");
        if (!Container.isRunning(context, containerName))
            throw new IllegalInfraStateException("Cannot open shell on container [" + containerName + "]." +
                    " Not running.");

        List<String> commands = Lists.newArrayList("docker", "exec", "-it", containerName);
        commands.addAll(executionCommands);
        logger.info("execute >>> " + Strings.listing(commands, " "));
        try {
            ProcessExecution.executeInteractive(commands);
        } catch (ProcessExecutionException e) {
            throw new DockerExecutionException(e);
        }
    }

}
