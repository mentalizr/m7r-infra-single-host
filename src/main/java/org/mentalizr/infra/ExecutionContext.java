package org.mentalizr.infra;

import de.arthurpicht.cli.CliCall;
import org.mentalizr.infra.docker.DockerExecutionContext;
import org.slf4j.LoggerFactory;

import java.time.Instant;

public class ExecutionContext {

    private static Instant callTimestamp;
    private static GlobalOptions globalOptions;
    private static DockerExecutionContext dockerExecutionContext = null;

    public static void initialize(CliCall cliCall) {
        callTimestamp = Instant.now();
        globalOptions = new GlobalOptions(cliCall);
        dockerExecutionContext = createDockerExecutionContext(globalOptions.isVerbose());
    }

    public static void initialize(GlobalOptions globalOptions) {
        callTimestamp = Instant.now();
        ExecutionContext.globalOptions = globalOptions;
        dockerExecutionContext = createDockerExecutionContext(globalOptions.isVerbose());
    }

    private static DockerExecutionContext createDockerExecutionContext(boolean verbose) {
        return new DockerExecutionContext.Builder()
                .beVerbose(verbose)
                .withLogger(LoggerFactory.getLogger(Const.DOCKER_LOGGER))
                .build();
    }

    public static Instant getCallTimestamp() {
        return callTimestamp;
    }

    public static DockerExecutionContext getDockerExecutionContext() {
        if (ExecutionContext.dockerExecutionContext == null)
            throw new IllegalStateException(ExecutionContext.class.getSimpleName() + " not initialized yet.");
        return ExecutionContext.dockerExecutionContext;
    }

    public static boolean isVerbose() {
        return globalOptions.isVerbose();
    }

    public static boolean showStacktrace() {
        return globalOptions.showStacktrace();
    }

    public static boolean isNotify() {
        return globalOptions.isNotify();
    }

}
