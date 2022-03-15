package org.mentalizr.infra.docker;

import de.arthurpicht.utils.core.strings.Strings;
import org.mentalizr.infra.DockerExecutionException;
import org.mentalizr.infra.processExecutor.*;
import org.mentalizr.infra.utils.LoggerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Arrays;

public class Docker {

    public static ProcessResultCollection call(DockerExecutionContext dockerExecutionContext, String... commands) throws DockerExecutionException {
        output(dockerExecutionContext, commands);

        try {
            return ProcessExecution.execute(
                    dockerExecutionContext.getLogger(),
                    dockerExecutionContext.isVerbose(),
                    commands);
        } catch (ProcessExecutionException e) {
            throw new DockerExecutionException("Execution of docker command failed: " + e.getMessage(), e);
        }
    }

    public static ProcessResultCollection call(DockerExecutionContext dockerExecutionContext, InputStream inputStream, String... commands) throws DockerExecutionException {
        output(dockerExecutionContext, commands);

        try {
            return ProcessExecution.execute(
                    dockerExecutionContext.getLogger(),
                    dockerExecutionContext.isVerbose(),
                    inputStream,
                    commands);
        } catch (ProcessExecutionException e) {
            throw new DockerExecutionException("Execution of docker command failed: " + e.getMessage(), e);
        }
    }

    private static void output(DockerExecutionContext dockerExecutionContext, String... command) {
        String commandString = "> " + Strings.listing(Arrays.asList(command), " ");
        dockerExecutionContext.getLogger().info(commandString);
        if (dockerExecutionContext.isVerbose()) System.out.println(commandString);
    }

}
