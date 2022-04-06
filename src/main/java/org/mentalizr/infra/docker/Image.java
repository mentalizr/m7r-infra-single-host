package org.mentalizr.infra.docker;

import de.arthurpicht.processExecutor.ProcessResultCollection;
import de.arthurpicht.utils.core.assertion.MethodPreconditions;
import org.mentalizr.infra.DockerExecutionException;

import static org.mentalizr.infra.docker.Docker.call;

public class Image {

    public static boolean exists(DockerExecutionContext context, String taggedImageName) throws DockerExecutionException {
        ProcessResultCollection result = call(context, "docker", "image", "ls", "-q", taggedImageName);
        return (result.isSuccess() && result.getStandardOut().size() > 0);
    }

    public static void pull(DockerExecutionContext context, String imageName) throws DockerExecutionException {
        MethodPreconditions.assertArgumentNotNull("imageName", imageName);
        Docker.call(context, "docker", "pull", imageName);
    }

    public static void build(DockerExecutionContext context, String taggedImageName, String gitUrl) throws DockerExecutionException {
        MethodPreconditions.assertArgumentNotNull("taggedImageName", taggedImageName);
        MethodPreconditions.assertArgumentNotNull("gitUrl", gitUrl);
        Docker.call(context, "docker", "build", "-t", taggedImageName, gitUrl);
    }

    public static void remove(DockerExecutionContext context, String taggedImageName) throws DockerExecutionException {
        MethodPreconditions.assertArgumentNotNull("taggedImageName", taggedImageName);
        Docker.call(context, "docker", "image", "rm", taggedImageName);
    }

    public static void tag(DockerExecutionContext context, String sourceTaggedImageName, String destTaggedImageName) throws DockerExecutionException {
        MethodPreconditions.assertArgumentNotNull("sourceTaggedImageName", sourceTaggedImageName);
        MethodPreconditions.assertArgumentNotNull("destinationTaggedImageName", destTaggedImageName);
        Docker.call(context, "docker", "image", "tag", sourceTaggedImageName, destTaggedImageName);
    }

}
