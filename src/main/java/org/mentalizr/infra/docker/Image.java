package org.mentalizr.infra.docker;

import de.arthurpicht.processExecutor.ProcessResultCollection;
import de.arthurpicht.utils.core.assertion.MethodPreconditions;
import de.arthurpicht.utils.core.strings.Strings;
import org.mentalizr.infra.DockerExecutionException;

import java.util.List;

public class Image {

    public static boolean exists(DockerExecutionContext context, String taggedImageName) throws DockerExecutionException {
        MethodPreconditions.assertArgumentNotNull("taggedImageName", taggedImageName);
        ProcessResultCollection result = Docker.call(context, "docker", "image", "ls", "-q", taggedImageName);
        return !result.getStandardOut().isEmpty();
    }

    public static boolean existsAny(DockerExecutionContext context, String taggedImageName) throws DockerExecutionException {
        MethodPreconditions.assertArgumentNotNull("taggedImageName", taggedImageName);
        ProcessResultCollection processResultCollection
                = Docker.call(context, "docker", "images",
                "--filter=reference=" + taggedImageName + "*", "-q");
        return !processResultCollection.getStandardOut().isEmpty();
    }

    public static void pull(DockerExecutionContext context, String taggedImageName) throws DockerExecutionException {
        MethodPreconditions.assertArgumentNotNull("taggedImageName", taggedImageName);
        Docker.call(context, "docker", "pull", taggedImageName);
    }

    public static void build(DockerExecutionContext context, String taggedImageName, String gitUrl) throws DockerExecutionException {
        MethodPreconditions.assertArgumentNotNull("taggedImageName", taggedImageName);
        MethodPreconditions.assertArgumentNotNull("gitUrl", gitUrl);
        Docker.call(context, "docker", "build", "-t", taggedImageName, gitUrl);
    }

    public static void buildPullNoCache(DockerExecutionContext context, String taggedImageName, String gitUrl) throws DockerExecutionException {
        MethodPreconditions.assertArgumentNotNull("taggedImageName", taggedImageName);
        MethodPreconditions.assertArgumentNotNull("gitUrl", gitUrl);
        Docker.call(context, "docker", "build", "--pull", "--no-cache", "-t", taggedImageName, gitUrl);
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

    public static void removeIncludingBackups(DockerExecutionContext context, String taggedImageName) throws DockerExecutionException {
        MethodPreconditions.assertArgumentNotNull("taggedImageName", taggedImageName);
        // see https://stackoverflow.com/questions/32490229/how-can-i-delete-docker-images-by-tag-preferably-with-wildcarding
        ProcessResultCollection processResultCollection
                = Docker.call(context, "docker", "images",
                "--filter=reference=" + taggedImageName + "*", "-q");

        List<String> imageIds = processResultCollection.getStandardOut();
        if (imageIds.isEmpty()) {
            context.getLogger().info("No images found for removing.");
        } else {
            context.getLogger().info("Found for removing: " + Strings.listing(processResultCollection.getStandardOut(), ", ", "[", "]", "", ""));
        }

        for (String imageId : processResultCollection.getStandardOut()) {
            Docker.call(context, "docker", "rmi", "-f", imageId);
        }
    }

}
