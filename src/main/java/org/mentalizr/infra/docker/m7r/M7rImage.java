package org.mentalizr.infra.docker.m7r;

import org.mentalizr.infra.DockerExecutionException;
import org.mentalizr.infra.ExecutionContext;
import org.mentalizr.infra.InfraRuntimeException;
import org.mentalizr.infra.docker.DockerExecutionContext;
import org.mentalizr.infra.docker.Image;

public class M7rImage {

    public static boolean exists(String taggedImageName) {
        DockerExecutionContext context = ExecutionContext.getDockerExecutionContext();
        try {
            return Image.exists(context, taggedImageName);
        } catch (DockerExecutionException e) {
            throw new InfraRuntimeException(e);
        }
    }

    public static boolean existsAnyIncludingBackups(String taggedImageName) {
        DockerExecutionContext context = ExecutionContext.getDockerExecutionContext();
        try {
            return Image.existsAny(context, taggedImageName);
        } catch (DockerExecutionException e) {
            throw new InfraRuntimeException(e);
        }
    }

    public static void pull(String taggedImageName) {
        DockerExecutionContext context = ExecutionContext.getDockerExecutionContext();
        try {
            Image.pull(context, taggedImageName);
        } catch (DockerExecutionException e) {
            throw new InfraRuntimeException(e);
        }
    }

    public static void build(String taggedImageName, String gitUrl) {
        DockerExecutionContext context = ExecutionContext.getDockerExecutionContext();
        try {
            Image.build(context, taggedImageName, gitUrl);
        } catch (DockerExecutionException e) {
            throw new InfraRuntimeException(e);
        }
    }

    public static void buildLatest(String taggedImageName, String gitUrl) {
        DockerExecutionContext context = ExecutionContext.getDockerExecutionContext();
        try {
            Image.buildPullNoCache(context, taggedImageName, gitUrl);
        } catch (DockerExecutionException e) {
            throw new InfraRuntimeException(e);
        }
    }

    public static void remove(String taggedImageName) {
        DockerExecutionContext context = ExecutionContext.getDockerExecutionContext();
        try {
            Image.remove(context, taggedImageName);
        } catch (DockerExecutionException e) {
            throw new InfraRuntimeException(e);
        }
    }

    public static void removeIncludingBackups(String taggedImageName) {
        DockerExecutionContext context = ExecutionContext.getDockerExecutionContext();
        try {
            Image.removeIncludingBackups(context, taggedImageName);
        } catch (DockerExecutionException e) {
            throw new InfraRuntimeException(e);
        }
    }

    public static void tag(String sourceTaggedImageName, String destTaggedImageName) {
        DockerExecutionContext context = ExecutionContext.getDockerExecutionContext();
        try {
            Image.tag(context, sourceTaggedImageName, destTaggedImageName);
        } catch (DockerExecutionException e) {
            throw new InfraRuntimeException(e);
        }
    }

}
