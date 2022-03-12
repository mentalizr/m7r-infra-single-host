package org.mentalizr.infra.tasks.create;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import de.arthurpicht.utils.io.file.SingleValueFile;
import de.arthurpicht.utils.io.tempDir.TempDir;
import de.arthurpicht.utils.io.tempDir.TempDirs;
import org.mentalizr.commons.M7rDirs;
import org.mentalizr.infra.Const;
import org.mentalizr.infra.DockerExecutionException;
import org.mentalizr.infra.InfraRuntimeException;
import org.mentalizr.infra.buildEntities.ConfigFileInitMongoJs;
import org.mentalizr.infra.docker.DockerCopy;
import org.mentalizr.infra.docker.m7r.M7rContainerMongo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class InitializeContainerMongo {

    public static Task create() {
        return new TaskBuilder()
                .name("initialize-container-mongo")
                .description("initialize container mongo")
                .dependencies("create-container-mongo")
                .execute(() -> {
//                    try {
//                        M7rDirs m7rDirs = new M7rDirs();
//                        if (!m7rDirs.existsTempDir()) Files.createDirectories(m7rDirs.getTempDir());
//                        TempDir tempDir = TempDirs.createUniqueTempDirAutoRemove(m7rDirs.getTempDir());
//                        Path initMongoFile = ConfigFileInitMongoJs.writeToFile(tempDir.asPath());
//                        DockerCopy.copyFile(initMongoFile, Const.CONTAINER_MONGO, "/docker-entrypoint-initdb.d/");
//                    } catch (DockerExecutionException | IOException e) {
//                        throw new InfraRuntimeException(e.getMessage(), e);
//                    }
                })
                .build();
    }

}
