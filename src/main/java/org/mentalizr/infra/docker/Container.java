package org.mentalizr.infra.docker;

import org.mentalizr.infra.DockerExecutionException;
import org.mentalizr.infra.process.collect.ProcessCollectResult;

import static org.mentalizr.infra.docker.Docker.call;

public class Container {

    public static boolean exists(String name) throws DockerExecutionException {
        // docker container ls -a --format "{{.Names}}"
        ProcessCollectResult result = call("docker", "container", "ls", "-a", "--format", "{{.Names}}");
        return result.getStandardOut().contains(name);
    }

    public static boolean isRunning(String name) throws DockerExecutionException {
        // docker container ls -a --format "{{.Names}}"
        ProcessCollectResult result = call("docker", "container", "ls", "--format", "{{.Names}}");
        return result.getStandardOut().contains(name);
    }


}
