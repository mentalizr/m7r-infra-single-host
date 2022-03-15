package org.mentalizr.infra.docker;

import org.mentalizr.infra.DockerExecutionException;
import org.mentalizr.infra.IllegalInfraStateException;
import org.mentalizr.infra.processExecutor.ProcessResultCollection;

import static org.mentalizr.infra.docker.Docker.call;

public class Container {

    public static boolean exists(DockerExecutionContext dockerExecutionContext, String name) throws DockerExecutionException {
        // docker container ls -a --format "{{.Names}}"
        ProcessResultCollection result = call(dockerExecutionContext, "docker", "container", "ls", "-a", "--format", "{{.Names}}");
        return result.getStandardOut().contains(name);
    }

    public static boolean isRunning(DockerExecutionContext dockerExecutionContext, String name) throws DockerExecutionException {
        // docker container ls -a --format "{{.Names}}"
        ProcessResultCollection result = call(dockerExecutionContext, "docker", "container", "ls", "--format", "{{.Names}}");
        return result.getStandardOut().contains(name);
    }

    public static void start(DockerExecutionContext dockerExecutionContext, String name) throws DockerExecutionException, IllegalInfraStateException {
        if (!exists(dockerExecutionContext, name)) throw new IllegalInfraStateException("Cannot start container [" + name + "]. Not existing.");
        call(dockerExecutionContext, "docker", "start", name);
    }

    public static void stop(DockerExecutionContext dockerExecutionContext, String name) throws DockerExecutionException, IllegalInfraStateException {
        if (!exists(dockerExecutionContext, name)) throw new IllegalInfraStateException("Cannot stop container [" + name + "]. Not existing.");
        call(dockerExecutionContext, "docker", "stop", name);
    }

}
