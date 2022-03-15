package org.mentalizr.infra;

import de.arthurpicht.cli.CliCall;
import org.mentalizr.infra.docker.DockerExecutionContext;
import org.mentalizr.infra.utils.LoggerUtils;
import org.slf4j.LoggerFactory;

public class ApplicationContext {

    private static DockerExecutionContext dockerExecutionContext = null;

    public static void initialize(CliCall cliCall) {
        ApplicationContext.dockerExecutionContext = new DockerExecutionContext.Builder()
                .beVerbose(cliCall.getOptionParserResultGlobal().hasOption(InfraCli.OPTION_VERBOSE))
                .withLogger(LoggerFactory.getLogger(LoggerUtils.DOCKER_LOGGER))
                .build();
    }

    public static DockerExecutionContext getDockerExecutionContext() {
        if (ApplicationContext.dockerExecutionContext == null)
            throw new IllegalStateException(ApplicationContext.class.getSimpleName() + " not initialized yet.");
        return ApplicationContext.dockerExecutionContext;
    }

}
