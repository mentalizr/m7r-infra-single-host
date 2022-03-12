package org.mentalizr.infra.docker;

import de.arthurpicht.utils.core.strings.Strings;
import org.mentalizr.infra.DockerExecutionException;
import org.mentalizr.infra.process.collect.ProcessCollect;
import org.mentalizr.infra.process.collect.ProcessCollectBuilder;
import org.mentalizr.infra.process.collect.ProcessCollectResult;
import org.mentalizr.infra.utils.LoggerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class Docker {

//    private static final Logger logger
//            = LoggerUtils.programmaticallyConfiguredLogger("docker", "docker.log");

    private static final Logger logger = LoggerFactory.getLogger(LoggerUtils.DOCKER_LOGGER);

    public static ProcessCollectResult call(String... command) throws DockerExecutionException {
        logger.info("execute >>> " + Strings.listing(Arrays.asList(command), " "));

        ProcessCollectBuilder builder = new ProcessCollectBuilder(command);
        ProcessCollect process = builder.build();
        ProcessCollectResult result;
        try {
            result = process.call();
        } catch (IOException | InterruptedException e) {
            throw new DockerExecutionException(e);
        }

        result.getStandardOut().forEach(logger::info);
        result.getErrorOut().forEach(logger::error);

        return result;
    }

    public static ProcessCollectResult call(InputStream inputStream, String... command) throws DockerExecutionException {
        logger.info("execute >>> " + Strings.listing(Arrays.asList(command), " "));

        ProcessCollectBuilder builder = new ProcessCollectBuilder(command);
        builder.withInputStream(inputStream);
        ProcessCollect process = builder.build();
        ProcessCollectResult result;
        try {
            result = process.call();
        } catch (IOException | InterruptedException e) {
            throw new DockerExecutionException(e);
        }

        result.getStandardOut().forEach(logger::info);
        result.getErrorOut().forEach(logger::error);

        return result;
    }

}
