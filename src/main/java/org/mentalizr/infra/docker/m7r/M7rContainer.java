package org.mentalizr.infra.docker.m7r;

import org.mentalizr.infra.*;
import org.mentalizr.infra.docker.Container;
import org.mentalizr.infra.docker.DockerExecutionContext;
import org.mentalizr.infra.processExecutor.ProcessResultCollection;

public class M7rContainer {

    public static boolean exists(String name) {
        DockerExecutionContext context = ApplicationContext.getDockerExecutionContext();
        try {
            return Container.exists(context, name);
        } catch (DockerExecutionException e) {
            throw new InfraRuntimeException(e);
        }
    }

    public static boolean isRunning(String name) {
        DockerExecutionContext context = ApplicationContext.getDockerExecutionContext();
        try {
            return Container.isRunning(context, name);
        } catch (DockerExecutionException e) {
            throw new InfraRuntimeException(e);
        }
    }

    public static boolean isStopped(String name) {
        DockerExecutionContext context = ApplicationContext.getDockerExecutionContext();
        try {
            return !Container.isRunning(context, name);
        } catch (DockerExecutionException e) {
            throw new InfraRuntimeException(e);
        }
    }

    public static void start(String name) {
        DockerExecutionContext context = ApplicationContext.getDockerExecutionContext();
        try {
            Container.start(context, name);
        } catch (DockerExecutionException | IllegalInfraStateException e) {
            throw new InfraRuntimeException(e);
        }
    }

    public static void stop(String name) {
        DockerExecutionContext context = ApplicationContext.getDockerExecutionContext();
        try {
            Container.stop(context, name);
        } catch (DockerExecutionException | IllegalInfraStateException e) {
            throw new InfraRuntimeException(e);
        }
    }

    public static void remove(String name) {
        DockerExecutionContext context = ApplicationContext.getDockerExecutionContext();
        try {
            Container.remove(context, name);
        } catch (DockerExecutionException | IllegalInfraStateException e) {
            throw new InfraRuntimeException(e);
        }
    }

    public static InfraRuntimeException createInfraRuntimeException(ProcessResultCollection result) {
        if (result.isSuccess()) throw new IllegalStateException("Result is no fail.");
        String message = "Docker execution finished with error.";
        if (!result.getErrorOut().isEmpty()) message += " " + result.getErrorOut().get(0);
        return new InfraRuntimeException(message);
    }


}
