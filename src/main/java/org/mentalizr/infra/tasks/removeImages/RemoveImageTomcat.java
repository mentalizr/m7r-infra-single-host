package org.mentalizr.infra.tasks.removeImages;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.docker.m7r.M7rImageTomcat;

public class RemoveImageTomcat {

    public static final String NAME = "remove-image-tomcat";

    public static Task create() {
        return new TaskBuilder()
                .withName(NAME)
//                .withDependencies(CreateBackupTagTomcat.NAME)
                .isUpToDate(() -> !M7rImageTomcat.exists())
                .execute(M7rImageTomcat::remove)
                .build();
    }

}
