package org.mentalizr.infra.tasks.removeImages;

import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.taskRunner.task.TaskBuilder;
import org.mentalizr.infra.docker.m7r.M7rImageTomcat;

public class RemoveImageTomcat {

    public static Task create() {
        return new TaskBuilder()
                .name("remove-image-tomcat")
                .dependencies("create-backup-tag-tomcat")
                .isUpToDate(() -> !M7rImageTomcat.exists())
                .execute(M7rImageTomcat::remove)
                .build();
    }

}
