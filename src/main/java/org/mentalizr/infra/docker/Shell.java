package org.mentalizr.infra.docker;

import de.arthurpicht.utils.core.strings.Strings;
import org.mentalizr.infra.DockerExecutionException;
import org.mentalizr.infra.IllegalInfraStateException;
import org.mentalizr.infra.process.passThrough.ProcessPassThrough;
import org.mentalizr.infra.process.passThrough.ProcessPassThroughBuilder;
import org.mentalizr.infra.utils.LoggerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Shell {

    private static final Logger logger = LoggerFactory.getLogger(LoggerUtils.DOCKER_LOGGER);

    public static void open(String containerName) throws DockerExecutionException, IllegalInfraStateException {

        if (!Container.exists(containerName))
            throw new IllegalInfraStateException("Cannot open shell on container [" + containerName + "]." +
                    " Not existing.");
        if (!Container.isRunning(containerName))
            throw new IllegalInfraStateException("Cannot open shell on container [" + containerName + "]." +
                    " Not running.");

        List<String> commands = Arrays.asList("docker", "exec", "-it", containerName, "bash");
        logger.info("execute >>> " + Strings.listing(commands, " "));
        ProcessPassThroughBuilder processPassThroughBuilder = new ProcessPassThroughBuilder(commands);
        ProcessPassThrough processPassThrough = processPassThroughBuilder.build();
        try {
            processPassThrough.call();
        } catch (IOException | InterruptedException e) {
            throw new DockerExecutionException(e);
        }
    }

}
