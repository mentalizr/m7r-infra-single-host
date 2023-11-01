package org.mentalizr.infra.tasks.removeImages;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.docker.m7r.M7rImageTomcat;

public class CreateBackupTagTomcat {

    public static final String NAME = "create-backup-tag-tomcat";

    public static Task create() {
        return new TaskBuilder()
                .withName(NAME)
                .skip(() -> !M7rImageTomcat.exists())
                .execute(M7rImageTomcat::createBackupTag)
                .build();
    }

}
