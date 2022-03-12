package org.mentalizr.infra.docker;

import org.mentalizr.infra.DockerExecutionException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.file.Path;

public class DockerCopy {

    public static void copyFile(Path sourceFile, String containerName, String destinationDir) throws DockerExecutionException {
        String source = sourceFile.toAbsolutePath().toString();
        String target = containerName + ":" + destinationDir;
        Docker.call("docker", "cp", source, target);
    }

    public static void copyStringToFile(String string, String containerName, String destinationDir) throws DockerExecutionException {
        InputStream inputStream = new ByteArrayInputStream(string.getBytes());
        String target = containerName + ":" + destinationDir;
        Docker.call(inputStream, "docker", "cp", "-", target);
    }

}
